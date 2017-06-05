package rosalind

// TODO: rewrite in a safe way, mod 1_000_000
int n = new Scanner(System.in).nextInt()
def all = (1..n-1).collect {CommonMath.combinations(n, it)}.sum() + 2
println all
