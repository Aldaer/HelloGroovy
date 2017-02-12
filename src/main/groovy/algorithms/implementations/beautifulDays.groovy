package algorithms.implementations

static int reversed(int n) {
    int result = 0
    while (n > 0) {
        result = result * 10 + n % 10
        n = n.intdiv(10)
    }
    return result
}

Scanner sc = new Scanner(System.in)
def (i, j, k) = (1..3).collect { sc.nextInt() }

Closure<Boolean> isBeautiful = { int x -> (x - reversed(x)) % k == 0 }
println "${(i..j).findAll(isBeautiful).size()}"
