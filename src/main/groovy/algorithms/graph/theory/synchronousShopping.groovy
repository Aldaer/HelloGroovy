package algorithms.graph.theory

class Road {
    ShoppingCenter dest
    int time
}

class ShoppingCenter {
    int id
    int fishTypes  // (n-1)th bit = nth type of fish
    List<Road> roads = []
    int[] fishingMap = (0..2047).collect { Integer.MAX_VALUE }

    // Walking around without fish
    int justTime
    Map<ShoppingCenter, Integer> bestWays = [:]
}

class Visit {
    ShoppingCenter sc
    int cameWithFish
    int cameInTime
}

class Solver {
    List<ShoppingCenter> centers
    int ALL_FISH

    final q = new ArrayDeque<Visit>()

    void walkAround() {
        while (!q.isEmpty()) {
            def nowVisiting = q.pop()
            def center = nowVisiting.sc
            int nowHaveFish = nowVisiting.cameWithFish | center.fishTypes
            def bestTimeWithSameFish = center.fishingMap[nowHaveFish]

            if (bestTimeWithSameFish > nowVisiting.cameInTime) {
                center.fishingMap[nowHaveFish] = nowVisiting.cameInTime
                center.roads.collect { rd -> new Visit(sc: rd.dest, cameWithFish: nowHaveFish, cameInTime: nowVisiting.cameInTime + rd.time) }
                        .each { q << it }
            }
        }
    }

    void walkAroundWithoutFish() {
        while (!q.isEmpty()) {
            def nowVisiting = q.pop()
            def center = nowVisiting.sc
            if (center.justTime > nowVisiting.cameInTime) {
                center.justTime = nowVisiting.cameInTime
                center.roads.collect { rd -> new Visit(sc: rd.dest, cameWithFish: 0, cameInTime: nowVisiting.cameInTime + rd.time) }
                        .each { q << it }
            }
        }
    }

    int getFishingTime() {
        final endMap = centers[-1].fishingMap
        final fishSets = (0..endMap.size() - 1).findAll { i -> endMap[i] < Integer.MAX_VALUE }
        final int last = fishSets.size()

        def catways = new ArrayList<Integer>()
        for (int i = 0; i < last; i++) {
            def set1 = fishSets[i]
            if (set1 == ALL_FISH)
                catways << endMap[set1]
            else
                for (int j = i + 1; j < last; j++) {
                    def set2 = fishSets[j]
                    if ((set1 | set2) == ALL_FISH) catways << Math.max(endMap[set1], endMap[set2])
                }
        }
        catways.min()
    }
}

def scnr = new Scanner(System.in)
def (N, M, K) = scnr.nextLine().tokenize(' ').collect { it as int }
def shoppingCenters = (1..N).collect {
    scnr.nextInt()      // Number of fish types here -- ignore
    int fTypes = scnr.nextLine().tokenize(' ').collect {
        it as int
    }.inject 0, { int acc, int ft -> acc | (1 << ft >> 1) }
    new ShoppingCenter(id: it, fishTypes: fTypes)
}

(1..M).each {
    def (int x, int y, int t) = scnr.nextLine().tokenize(' ').collect { it as int }
    shoppingCenters[x - 1].roads << new Road(dest: shoppingCenters[y - 1], time: t)
    shoppingCenters[y - 1].roads << new Road(dest: shoppingCenters[x - 1], time: t)
}

def solver = new Solver(centers: shoppingCenters, ALL_FISH: (1 << K) - 1)

final int FREE_FISH = shoppingCenters[0].fishTypes | shoppingCenters[-1].fishTypes
shoppingCenters.each { sc -> sc.fishTypes &= ~FREE_FISH }
shoppingCenters[0].fishTypes = FREE_FISH

def placesToBuy = shoppingCenters.findAll { sc -> sc.fishTypes > 0 } << shoppingCenters[-1]

if (placesToBuy.size()**2 < M * 2) {            // Optimize for lots of useless centers
//    println "Optimizing roads: $M -> ${placesToBuy.size() * (placesToBuy.size() -1)}"
    for (int i = 0; i < placesToBuy.size() - 1; i++) {
        shoppingCenters.each { sc -> sc.justTime = Integer.MAX_VALUE }
        solver.q.add(new Visit(sc: placesToBuy[i], cameWithFish: 0, cameInTime: 0))
        solver.walkAroundWithoutFish()
        for (int j = 0; j < placesToBuy.size(); j++) {
            placesToBuy[j].bestWays[placesToBuy[i]] = placesToBuy[j].justTime
            placesToBuy[i].bestWays[placesToBuy[j]] = placesToBuy[j].justTime
        }
    }
    placesToBuy.each { sc ->
        sc.roads.clear()
        sc.bestWays.entrySet().findAll { entry -> entry.getKey() != sc }
                .each { entry -> sc.roads << new Road(dest: entry.getKey(), time: entry.getValue()) }
    }
    solver = new Solver(centers: placesToBuy, ALL_FISH: solver.ALL_FISH)
}

//println "Walking around"

solver.q.add(new Visit(sc: shoppingCenters[0], cameWithFish: 0, cameInTime: 0))
solver.walkAround()

println solver.getFishingTime()
