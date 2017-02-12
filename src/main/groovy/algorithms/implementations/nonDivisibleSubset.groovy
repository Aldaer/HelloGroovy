package algorithms.implementations

static <T> List<List<T>> subsetsOf(int k, List<T> list) {
    if (k == 0 || k > list.size()) return []
    if (k == list.size()) return [list]
    if (k == 1) return list.collect({ [it] })
    def result = []
    def listNoLast = list.getAt(0..list.size() - 2)
    result.addAll subsetsOf(k, listNoLast)
    def last = list.getAt(-1)
    subsetsOf(k - 1, listNoLast).each { result.add(it + last) }
    result
}

Scanner sc = new Scanner(System.in)
sc.class.metaClass.readInts = { int n -> (1..n).collect({ owner.nextInt() }) }

def (n, k) = sc.readInts(2)
def a = sc.readInts(n)
int maxS = 2;
while (subsetsOf(maxS, a).every())
