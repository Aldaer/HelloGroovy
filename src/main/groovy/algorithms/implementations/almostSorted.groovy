package algorithms.implementations

class SortableList<T extends Comparable<T>> {
    List<T> a

    private boolean isSorted() {
        for (int i in 0..a.size() - 2)
            if (a[i + 1] < a[i]) return false
        true
    }

    String almostSortable() {
        if (isSorted()) return 'yes'
        def b = a.toSorted().toArray(new T[a.size()])
        Map<Integer, Integer> delta = [:].withDefault { x -> 0 }
        a.eachWithIndex { ai, i ->
            def di = Arrays.binarySearch(b, ai) - i
            if (di != 0) delta.put(i, di)
        }
        if (delta.size() == 2) {
            def (i, j) = delta.keySet()
            return "yes\nswap ${i+1} ${j+1}"
        }
        int minI = Integer.MAX_VALUE, maxI = -1
        delta.keySet().each {
            if (it > maxI) maxI = it
            if (it < minI) minI = it
        }
        def invariant = minI * 2 + delta[minI]
        for (i in minI..maxI) {
            if (i * 2 + delta[i] != invariant) return 'no'
        }
        "yes\nreverse ${minI+1} ${maxI+1}"
    }
}

Scanner sc = new Scanner(System.in)
n = sc.nextInt()
assert n > 1
a = (0..n - 1).collect { sc.nextInt() as int }
println new SortableList<>(a: a).almostSortable()
