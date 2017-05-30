package rosalind

Scanner sc = new Scanner(System.in)
(n, k) = sc.nextLine().tokenize(' ').collect { it as int }

long psum = 0
long sum = 1
(2..n).each { i ->
    def newsum = sum + psum * k
    psum = sum
    sum = newsum
}
println sum