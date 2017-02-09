n = 5 //new Scanner(System.in).nextInt()
for (i in 1..n) {
    if (i < n) print ' ' * (n - i)
    println '#' * i
}