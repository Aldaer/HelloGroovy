package algorithms.implementations

Scanner sc = new Scanner(System.in)
Scanner.metaClass.readInts = { int i -> (1..i).collect({ nextInt() }) }
def (n, k) = sc.readInts(2)
def setA = sc.readInts(n)
def pairs = (0..n - 2).collectMany({ i -> (i + 1..n - 1).collect({ j -> setA[i] + setA[j] }) }) \
    .findAll({ it % k == 0})
println pairs.size()