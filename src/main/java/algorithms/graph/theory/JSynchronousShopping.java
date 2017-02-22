package algorithms.graph.theory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class JSynchronousShopping {
    private static final int ROAD_OPTIMIZATION_THRESHOLD = 2;
    private static final int USEFUL_OPTIMIZATION_FACTOR = 4;
    private static Queue<ShoppingCenter.ShoppingVisit> fishingQ = new ArrayDeque<>();

    static class ShopWalker implements Runnable {
        private final int id;
        private final Queue<ShoppingCenter.Visit> q = new ArrayDeque<>();

        ShopWalker(int id, ShoppingCenter start) {
            this.id = id;
            q.add(start.new Visit(0));
        }

        @Override
        public void run() {
            while (!q.isEmpty()) {
                q.addAll(q.remove().doVisit(id));
            }
        }
    }

    private static class Road {
        final ShoppingCenter dest;
        int time;

        Road(ShoppingCenter dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }

    private static class ShoppingCenter {
        int fishSold;
        int[] fishingMap;

        final List<Road> roads = new ArrayList<>();
        Map<ShoppingCenter, Integer> travelTimes;

        int[] parallelTravelTime;

        ShoppingCenter(int fishSold) {
            this.fishSold = fishSold;
        }

        void initFishingMaps() {
            fishingMap = new int[2048];
            Arrays.fill(fishingMap, Integer.MAX_VALUE);
        }

        void initWalkerTimes(int nWalkers) {
            parallelTravelTime = new int[nWalkers];
            Arrays.fill(parallelTravelTime, Integer.MAX_VALUE);
        }

        private List<Visit> visit(int time, int walkerId) {
            if (parallelTravelTime[walkerId] <= time) return Collections.emptyList();
            parallelTravelTime[walkerId] = time;

            return roads.stream()
                    .map(rd -> rd.dest.new Visit(time + rd.time))
                    .collect(Collectors.toList());
        }

        private void visitWithFish(int time, int withFish) {
            int nowHaveFish = fishSold | withFish;
            if (fishingMap[nowHaveFish] > time) {
                fishingMap[nowHaveFish] = time;
                for (Road rd : roads) {
                    fishingQ.add(rd.dest.new ShoppingVisit(time + rd.time, nowHaveFish));
                }
            }
        }

        boolean isUseful() {
            return fishSold > 0;
        }

        int findRoadIndex(ShoppingCenter dest) {
            for (int i = 0; i < roads.size(); i++) {
                if (roads.get(i).dest == dest) return i;

            }
            return -1;
        }

        class Visit {
            final int time;

            Visit(int time) {
                this.time = time;
            }

            List<Visit> doVisit(int walkerId) {
                return visit(time, walkerId);
            }
        }

        class ShoppingVisit extends Visit {
            final int withFish;

            ShoppingVisit(int time, int withFish) {
                super(time);
                this.withFish = withFish;
            }

            void doVisit() {
                visitWithFish(time, withFish);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();   // # of shopping centers
        final int M = scanner.nextInt();   // # of roads
        final int K = scanner.nextInt();   // # of fish types

        final int ALL_FISH = (1 << K) - 1;
        List<ShoppingCenter> centers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int t = scanner.nextInt();
            int fishSold = 0;
            for (int fish = 0; fish < t; fish++) {
                fishSold |= 1 << scanner.nextInt() >> 1;
            }
            centers.add(new ShoppingCenter(fishSold));
        }
        for (int i = 0; i < M; i++) {
            ShoppingCenter c1 = centers.get(scanner.nextInt() - 1);     // Our S.C. indexes are zero-based, input is 1-based
            ShoppingCenter c2 = centers.get(scanner.nextInt() - 1);
            int t = scanner.nextInt();
            c1.roads.add(new Road(c2, t));
            c2.roads.add(new Road(c1, t));
        }
//        ==========================================
        final long time1 = System.currentTimeMillis();

        final int FREE_FISH = centers.get(0).fishSold | centers.get(centers.size() - 1).fishSold;
        for (ShoppingCenter center : centers) {
            center.fishSold &= ~FREE_FISH;
        }
        centers.get(0).fishSold = FREE_FISH;

        List<ShoppingCenter> usefulCenters = new ArrayList<>(N);
        usefulCenters.add(centers.get(0));
        for (int i = 1; i < N - 1; i++) {
            final ShoppingCenter currCenter = centers.get(i);
            if (currCenter.isUseful()) usefulCenters.add(currCenter);
            else if (currCenter.roads.size() <= ROAD_OPTIMIZATION_THRESHOLD)
                for (Road rd : currCenter.roads) {
                    rd.dest.roads.removeIf(road -> road.dest == currCenter);
                    for (Road otherRd : currCenter.roads) {
                        if (otherRd != rd) {
                            final int roadIndex = rd.dest.findRoadIndex(otherRd.dest);
                            if (roadIndex < 0)
                                rd.dest.roads.add(new Road(otherRd.dest, rd.time + otherRd.time));
                            else {
                                final Road direct = rd.dest.roads.get(roadIndex);
                                direct.time = Math.min(direct.time, rd.time + otherRd.time);
                            }
                        }
                    }
                }
        }

        usefulCenters.add(centers.get(N - 1));
        final int uN = usefulCenters.size();
        if (uN < N / USEFUL_OPTIMIZATION_FACTOR) {
            for (ShoppingCenter center : centers)
                center.initWalkerTimes(uN);

            final ExecutorService executorService = Executors.newCachedThreadPool();

            IntStream.range(0, uN)
                    .mapToObj(i -> new ShopWalker(i, usefulCenters.get(i)))
                    .forEach(executorService::execute);

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);

            for (int i = 0; i < uN; i++) {
                final int index = i;
                usefulCenters.get(index).travelTimes = usefulCenters.stream().collect(Collectors.toMap(
                        Function.identity(), shoppingCenter -> shoppingCenter.parallelTravelTime[index]));
            }

            for (ShoppingCenter uc : usefulCenters) {
                uc.roads.clear();
                uc.travelTimes.entrySet().stream()
                        .filter(e -> e.getKey() != uc)
                        .map(e -> new Road(e.getKey(), e.getValue()))
                        .forEach(uc.roads::add);
            }

            centers = usefulCenters;
        }
        centers.forEach(ShoppingCenter::initFishingMaps);

        fishingQ.add(centers.get(0).new ShoppingVisit(0, 0));
        while (!fishingQ.isEmpty())
            fishingQ.remove().doVisit();

        final int[] fMap = centers.get(centers.size() - 1).fishingMap;

        final int[] fishOptions = IntStream.range(0, fMap.length)
                .filter(i -> fMap[i] < Integer.MAX_VALUE)
                .toArray();

        final int maxI = fishOptions.length - 1;

        final int bestTime = IntStream.rangeClosed(0, maxI)
                .flatMap(i -> {
                    final int thisFish = fishOptions[i];
                    final int thisTime = fMap[thisFish];
                    return thisFish == ALL_FISH ? IntStream.of(thisTime) :
                            IntStream.rangeClosed(i + 1, maxI)
                                    .filter(j -> (thisFish | fishOptions[j]) == ALL_FISH)
                                    .map(j -> Math.max(thisTime, fMap[fishOptions[j]]));
                })
                .min().orElse(-1);

        System.out.println(bestTime);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - time1));
    }

}
