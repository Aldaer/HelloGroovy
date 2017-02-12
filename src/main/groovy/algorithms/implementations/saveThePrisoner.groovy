package algorithms.implementations

Scanner sc = new Scanner(System.in)
int t = sc.nextInt()

def ids = (1..t).collect {
    def (n, m, s) = (0..2).collect({ sc.nextInt() })
    (s + m - 1) % n ?: n
}
ids.each { println it }
