package algorithms.constructive

Scanner sc = new Scanner(System.in)
def parseLine = { sc.nextLine().tokenize().collect { it as int } }
def nQueries = sc.nextInt()
def results = new int[nQueries]
(0..nQueries - 1).each { q ->
    def n = sc.nextInt()
    sc.nextLine()
    def matrix = (0..2 * n - 1).collect { parseLine() }
    def sum = 0
    def lastN = 2 * n - 1
    (0..n * n - 1).each { t ->
        int x = t % n
        int y = t.intdiv(n)
        sum += [matrix[x][y], matrix[lastN - x][y], matrix[x][lastN - y], matrix[lastN - x][lastN - y]].max()
    }
    results[q] = sum
}
results.each { println it }