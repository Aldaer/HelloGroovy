package algorithms.constructive

def sc = new Scanner(System.in)
def t = sc.nextInt()
def results = []
(1..t).each {
    def (n, k, b) = (1..3).collect { sc.nextBigInteger() }
    def minPossible = b * (b + 1) / 2
    def maxPossible = k * b - b * (b - 1) / 2
    if (n < minPossible || n > maxPossible)
        results << '-1'
    else {
        def solution = new BigInteger[b]
        def minSize = n / b - b / 2 as BigInteger
        def approxSum = minSize * b + b * (b - 1) / 2 as BigInteger
        def deficit = n - approxSum
        if (deficit > b) {
            minSize++
            deficit -= b
        }
        for (int i = 0; i < b - deficit; i++)
            solution[i] = i + minSize
        for (int i = b - deficit; i < b; i++)
            solution[i] = i + minSize + 1
        results << solution.join(' ')
    }
}
results.each { println it }