Scanner sc = new Scanner(System.in)
Scanner.metaClass.readInts = { i -> (1..i).collect({ nextInt() }) }
def (n, m) = sc.readInts(2)
setA = sc.readInts(n)
setB = sc.readInts(m)
def xs = (setA.max()..setB.min()).findAll({ x -> !(setA.any { x % it } || setB.any { it % x }) })
println xs.size()
