package algorithms.implementations

Scanner sc = new Scanner(System.in)
def n = sc.nextInt()
sum = 0     // Object field, not script variable

int liked(int n) {
    int x = n == 1 ? 2 : (liked(n - 1) * 3).intdiv(2)
    sum +=x
    x
}

liked(n)
println sum