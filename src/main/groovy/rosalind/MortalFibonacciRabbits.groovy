package rosalind

Scanner sc = new Scanner(System.in)
(n, m) = sc.nextLine().tokenize(' ').collect { it as int }

newborns = null
adults = { t ->
    (1..m - 1).collect { j -> newborns(t - j) }.inject(0L, { x, a -> x + a })
}.memoize()

newborns = { t ->
    t < 1 ? 0 : t == 1 ? 1L : adults(t - 1)
}.memoize()

(1..n).each { println adults(it) + newborns(it) }