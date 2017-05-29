substrings = [] as List<String>

static <T> List<T> forEachSetBit(long x, Closure<T> action) {
    long dx = x
    def result = []
    int i = 0
    while (dx > 0) {
        if ((dx & 1) > 0) result << action.call(i)
        dx >>= 1
        i++
    }
    result
}

Closure<List<Tuple2<Long, Integer>>> withoutOneBit = { long x ->
    forEachSetBit x, { i -> new Tuple2(x & ~(1 << i), i) }
}

static String minimumCombo2(String s1, String s2) {
    if (s2.contains(s1)) return s2

    for (int i = s2.length(); i > 0; i--)
        if (s1.endsWith(s2.substring(0, i))) return s1 + s2.substring(i)

    s1 + s2
}

superstring = { long multiIndex ->
    def subindices = withoutOneBit.call(multiIndex)
    subindices.collectMany { subindex ->
        subindex.first == 0L ? [substrings[subindex.second]] :
                superstring.call(subindex.first).collect { minimumCombo2(it, substrings[subindex.second]) }
    }
}.memoize()


println "Input: number of strings, then one string per line\nExample:\n2\naabc\nbcaa"
Scanner sc = new Scanner(System.in)
int N = sc.nextLine() as int
assert N < 63
for (i in 1..N) substrings << sc.nextLine()

int allStrings = (1 << N) - 1

def time = System.currentTimeMillis()
println superstring.call(allStrings).sort { s1, s2 -> s1.length() - s2.length() }.get(0)
println "Calculation time: ${System.currentTimeMillis() - time} ms"
