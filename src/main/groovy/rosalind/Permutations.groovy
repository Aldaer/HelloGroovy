package rosalind

int n = new Scanner(System.in).nextInt()
def pieces = (1..n).collect { it }
def permutations = pieces.permutations()
println permutations.size()
permutations.each { println it.join(' ')}

