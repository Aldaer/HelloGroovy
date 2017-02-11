Scanner sc = new Scanner(System.in)
def t = sc.nextInt()
def queries = (1..t).collect { sc.nextInt() }
queries.each { int n ->
    int nYears = 1 + (n >> 1)
    long spring = 2**nYears - 1
    println n % 2 ? spring * 2 : spring
}