package algorithms.implementations

Scanner sc = new Scanner(System.in)
def n = sc.nextInt()
def ints = (1..n).collect({ sc.nextInt() })
ints.each {
    int found = 0
    int x = it
    while (x > 0) {
        int d = x % 10
        if (d > 0 && it % d == 0) found++
        x /= 10
    }
    println found
}