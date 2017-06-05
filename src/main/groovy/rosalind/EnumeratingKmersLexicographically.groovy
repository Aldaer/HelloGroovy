package rosalind

def sc = new Scanner(System.in)
def chars = sc.nextLine().tokenize(' ').sort()
def length = sc.nextInt()

nMer = { int n -> n == 1 ? chars : nMer(n - 1).collectMany { nMinus1 -> chars.collect { nMinus1 + it } } }.memoize()
nMer(length).each { println it }