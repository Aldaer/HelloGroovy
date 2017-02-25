package algorithms.implementations

static boolean canSort(List<Integer> a) {
    def n = a.size()
    def nSwap = 0
    for (int i = 0; i < n - 1; i++) {
        def ai = a[i]
        for (int j = i + 1; j < n; j++)
            if (a[j] < ai) nSwap++
    }
    nSwap % 2 == 0
}

Scanner sc = new Scanner(System.in)
def nQueries = sc.nextInt()
def parseLine = { sc.nextLine().tokenize(' ').collect { it as int } }
def results = []
(1..nQueries).each {
    sc.nextInt()
    sc.nextLine()
    def a = parseLine()
    results << (canSort(a) ? 'YES' : 'NO')
}

results.each { println it }
