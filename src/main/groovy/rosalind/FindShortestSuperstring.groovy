package rosalind

import groovy.transform.Field

@Field List<String> substrings = []

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


def sequences = FASTA.readFromConsole()
def N = sequences.size()
assert N < 63

int allStrings = (1 << N) - 1
substrings = sequences.collect { it.seq }

def time = System.currentTimeMillis()
println superstring.call(allStrings).sort { s1, s2 -> s1.length() - s2.length() }.get(0)
println "Calculation time: ${System.currentTimeMillis() - time} ms"
