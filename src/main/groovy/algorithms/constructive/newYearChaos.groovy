package algorithms.constructive

Scanner sc = new Scanner(System.in)
def parseLine = { sc.nextLine().tokenize().collect { it as int } }
def nQueries = sc.nextInt()
def results = new int[nQueries]
(0..nQueries - 1).each { q ->
    def n = sc.nextInt()
    sc.nextLine()
    def line = parseLine()
    def delta = (0..n - 1).collect { i -> line[i] - 1 - i } // +1: briber, -1: bribed

    def bribes = 0
    for (int i = 0; i < n; i++) {
        def dx = delta[i]
        if (dx > 2) {
            bribes = -1
            break
        }
        def x = line[i]
        def minJ = i + dx - 1
        if (minJ < 0) minJ = 0
        for (int j = minJ; j < i; j++)
            if (line[j] > x) bribes++
    }
    results[q] = bribes
}
results.each { println it > 0 ? it : 'Too chaotic' }