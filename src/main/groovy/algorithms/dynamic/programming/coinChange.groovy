package algorithms.dynamic.programming

def sc = new Scanner(System.in)
def readAllInts = { -> sc.nextLine().tokenize(' ').collect { it as int } }
def (n, m) = readAllInts()
coins = readAllInts().sort()
cache = [:]
println waysToChange(n, m - 1)

long waysToChange(int amnt, int maxCoinIndex) {
    if (amnt < 0) return 0
    if (amnt == 0) return 1
    if (maxCoinIndex == 0) return amnt % coins[maxCoinIndex] ? 0 : 1
    int hash = amnt * 64 + maxCoinIndex
    cache[hash] ?: (cache[hash] = (0..maxCoinIndex).collect { i -> waysToChange(amnt - coins[i], i) }.sum())
}

