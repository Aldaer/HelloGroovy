package algorithms.implementations

Scanner sc = new Scanner(System.in)
Scanner.metaClass.readInts = { int m -> (1..m).collect({ owner.nextInt() }) }
def t = sc.nextInt()
def results = (1..t).collect {
    def (n, k) = sc.readInts(2)
    def times = sc.readInts(n)
    times.findAll({ it <= 0 }).size() < k ? 'YES' : 'NO'
}
results.each { println it }

