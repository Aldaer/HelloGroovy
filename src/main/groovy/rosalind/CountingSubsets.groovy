package rosalind

// 2^n mod 1_000_000
int n = new Scanner(System.in).nextInt()
int subsets = 1
for (int i in 1..n) subsets = subsets*2 % 1_000_000
println subsets