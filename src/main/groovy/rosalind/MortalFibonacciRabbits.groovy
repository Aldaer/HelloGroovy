package rosalind
//TODO

Scanner sc = new Scanner(System.in)
(n, m) = sc.nextLine().tokenize(' ').collect { it as int }

def sums = new long[n + m + 1]
sums[1] = 1
(1..n - 1).each { int i ->
    (1..m - 1).each { int j -> sums[i + j] += sums[i] }
}
println sums[n]