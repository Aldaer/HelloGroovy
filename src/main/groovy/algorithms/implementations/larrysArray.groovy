package algorithms.implementations

static int mergeSortInPlaceCountInversions(int[] a) {
    if (a.length < 2) return 0
    int split = a.length >> 1
    def left = Arrays.copyOfRange(a, 0, split)
    def right = Arrays.copyOfRange(a, split, a.length)
    def invLeft = mergeSortInPlaceCountInversions(left)
    def invRight = mergeSortInPlaceCountInversions(right)
    int i = 0, j = 0, numInversions = 0
    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            a[i++ + j] = left[i]
            numInversions += j
        } else {
            a[i + j++] = right[j]
        }
    }
    while (i < left.length) {
        a[i++ + j] = left[i]
        numInversions += j
    }
    while (j < right.length) {
        a[i + j++] = right[j]
    }
    invLeft + invRight + numInversions
}

Scanner sc = new Scanner(System.in)
def nQueries = sc.nextInt()
def parseLine = { sc.nextLine().tokenize(' ').collect { it as int } as int[] }
def results = []
(1..nQueries).each {
    sc.nextInt()
    sc.nextLine()
    def a = parseLine()
    def inv = mergeSortInPlaceCountInversions(a)
    results << (inv % 2 ? 'NO' : 'YES')
}

results.each { println it }
