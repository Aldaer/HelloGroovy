package rosalind

static String normalizePair(List<String> source, List<String> dest) {
    dest.collect { source.indexOf(it) }.join('')
}

static String mutate(String s, int x1, int x2) {
    int sz = s.size()
    byte[] bs = s.bytes
    byte[] mutS = new byte[sz]
    System.arraycopy(bs, 0, mutS, 0, x1)
    if (x2 < sz - 1) System.arraycopy(bs, x2 + 1, mutS, x2 + 1, sz - 1 - x2)
    for (int i = x1; i <= x2; i++) {
        mutS[i] = bs[x1 + x2 - i]
    }
    new String(mutS)
}

getPermutations = { List<String> q ->
    q.size() == 1 ? q : q.collectMany { s ->
        def smallQ = new ArrayList(q)
        smallQ.remove(s)
        getPermutations(smallQ).collect { s + it }
    }
}

long startTime = System.currentTimeMillis()
Set<String> allPermutations = new HashSet<>(getPermutations('0123456789'.toList()))
println "Combination count time: ${System.currentTimeMillis() - startTime} ms"
String zeroOrder = '0123456789'
Map<String, Integer> permutationOrder = [(zeroOrder): 0]
List<String> prevOrder = [zeroOrder]
allPermutations.remove(zeroOrder)
for (int order = 1; order <= 9; order++) {
    if (prevOrder.size() < allPermutations.size()) {
        println "Order: $order, mutating known strings [${prevOrder.size()}]"
        List<String> newPerms = []
        prevOrder.each { perm ->
            for (int i = 0; i < 9; i++) {
                for (int j = i + 1; j <= 9; j++) {
                    permutationOrder.computeIfAbsent(mutate(perm, i, j), { s ->
                        allPermutations.remove(s)
                        newPerms << s
                        order
                    })
                }
            }
        }
        prevOrder = newPerms
    } else {
        println "Order: $order, finding parent strings [${allPermutations.size()}]"
        List<String> keys = new ArrayList(allPermutations)
        List<String> parentsFound = []
        keys.each { perm ->
            for (int i = 0; i < 9; i++) {
                for (int j = i + 1; j <= 9; j++) {
                    String mutatedFrom = mutate(perm, i, j)
                    if (permutationOrder.containsKey(mutatedFrom)) {
                        allPermutations.remove(perm)
                        parentsFound << perm
                    }
                }
            }
        }
        parentsFound.each { permutationOrder.put(it, order) }
    }
}
println permutationOrder.size()
println "Overall calculation time: ${System.currentTimeMillis() - startTime} ms"

println 'Enter sampe dataset:'

def sc = new Scanner(System.in)
def result = []
while (true) {
    def set1 = sc.nextLine().tokenize(' ')
    if (!set1) break
    def set2 = sc.nextLine().tokenize(' ')
    sc.nextLine()
    def normalizedRearrangement = normalizePair(set1, set2)
    result << permutationOrder[normalizedRearrangement]
}
println result.join(' ')






