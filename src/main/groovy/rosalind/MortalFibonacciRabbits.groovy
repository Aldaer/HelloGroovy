package rosalind

Scanner sc = new Scanner(System.in)
(n, m) = sc.nextLine().tokenize(' ').collect { it as int }

newborns = { t ->
    t < 1 ? 0L : t == 1 ? 1L :
            (2..m).collect { j -> newborns(t - j) }.sum()
}.memoize()

//adults(t)=newborns(t+1)
(1..n).each { println newborns(it + 1) + newborns(it) }