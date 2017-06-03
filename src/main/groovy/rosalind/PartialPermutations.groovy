package rosalind

def (n, k) = new Scanner(System.in).nextLine().tokenize(' ').collect { it as int }

// Cnk * k! == n! / (n-k)! == П i (i=n-k+1..n)
long result = 1
(n - k + 1..n).each { result = result * it % 1_000_000 }
println result

