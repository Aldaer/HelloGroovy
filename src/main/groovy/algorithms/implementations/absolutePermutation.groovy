package algorithms.implementations

Scanner sc = new Scanner(System.in)
def nQueries = sc.nextInt()
def results = []
(1..nQueries).each {
    def (N, K) = [sc.nextInt(), sc.nextInt()]
    if (K == 0) {
        results << (1..N).collect { it }.join(' ')
    } else {
        def permPossible = N % (2 * K) == 0
        if (permPossible) {
            int nBlocks = N / (2 * K)
            def perm = (0..nBlocks - 1).collectMany { b ->
                (1..K).collect { it + K + 2 * b * K } + (1..K).collect { it + 2 * b * K }
            }
            results << perm.join(' ')
        } else
            results << '-1'
    }
}
results.each { println it }