package algorithms.graph.theory

class ShoppingCenter {
    int id
    int fishTypes  // (n-1)th bit = nth type of fish
    List<Tuple2<ShoppingCenter, Integer>> roads = []
    Map<Integer, Integer> fishingMap = [:] // fish types : time
}

class ShoppingCenterVisit {
    ShoppingCenter sc
    int cameWithFish
    int cameInTime
}

def scnr = new Scanner(System.in)
def (N, M, K) = scnr.nextLine().tokenize(' ').collect { it as int }
def shoppingCenters = (1..N).collect {
    scnr.nextInt()      // Number of fish types here -- ignore
    int fTypes = scnr.nextLine().tokenize(' ').collect { it as int }.inject 0, { int acc, int ft -> acc | (1 << ft >> 1) }
    new ShoppingCenter(id: it, fishTypes: fTypes)
}

(1..M).each {
    def (int x, int y, int t) = scnr.nextLine().tokenize(' ').collect { it as int }
    shoppingCenters[x - 1].roads << new Tuple2<>(shoppingCenters[y - 1], t)
    shoppingCenters[y - 1].roads << new Tuple2<>(shoppingCenters[x - 1], t)
}

final int allFish = (1 << K) - 1

def q = new ArrayDeque<ShoppingCenterVisit>()
q.add(new ShoppingCenterVisit(sc: shoppingCenters[0], cameWithFish: 0, cameInTime: 0))
while (!q.isEmpty()) {
    def nowVisiting = q.pop()
    def center = nowVisiting.sc
    int nowHaveFish = nowVisiting.cameWithFish | center.fishTypes
    def bestTimeWithSameFish = center.fishingMap.keySet()
            .findAll { key -> (key | nowHaveFish) == key }
            .collect { key -> center.fishingMap[key] }
            .min()

    if (bestTimeWithSameFish == null || bestTimeWithSameFish > nowVisiting.cameInTime) {
//        println "Found path to Center $center.id, fish ${String.format('%6s', Integer.toBinaryString(nowHaveFish)).replace(' ', '0')}, time $nowVisiting.cameInTime"
        center.fishingMap[nowHaveFish] = nowVisiting.cameInTime
        if (nowHaveFish != allFish || ! center.is(shoppingCenters[N - 1]))      // Optimization: won't go anywhere from endpoint with all fish
            center.roads.collect { rd -> new ShoppingCenterVisit(sc: rd.first, cameWithFish: nowHaveFish, cameInTime: nowVisiting.cameInTime + rd.second) }
                .each { q << it }
    }
}

final endMap = shoppingCenters.get(N - 1).fishingMap
final fishSets = endMap.keySet()
final int last = fishSets.size() - 1
def catWays = []
for (int i = 0; i < last - 1; i++)
    for (int j = i + 1; j < last; j++)
        catWays << new Tuple2<Integer, Integer>(fishSets[i], fishSets[j])

println catWays.findAll { cws -> (cws.first | cws.second) == allFish }  \
    .collect { cws -> Math.max(endMap[cws.first], endMap[cws.second]) }  \
    .min()
