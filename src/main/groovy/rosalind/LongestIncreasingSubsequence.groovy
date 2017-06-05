package rosalind

def sc = new Scanner(System.in)
int n = sc.nextLine() as int
int[] seq = sc.nextLine().tokenize(' ').collect { it as int }.toArray()
def increasingSubs = []
def decreasingSubs = []
for (i in 0..<n) {
    int ai = seq[i]
    def moreIncreasing = increasingSubs.findAll { it[-1] < ai }.max { it.size() }?.with { it + ai } ?: [ai]
    def oldIndex = increasingSubs.findIndexOf { it.size() == moreIncreasing.size() }
    if (oldIndex > 0) {
        if (increasingSubs[oldIndex][-1] > ai) increasingSubs[oldIndex] = moreIncreasing
    } else {
        increasingSubs << moreIncreasing
    }
    def moreDecreasing = decreasingSubs.findAll { it[-1] > ai }.max { it.size() }?.with { it + ai } ?: [ai]
    oldIndex = decreasingSubs.findIndexOf { it.size() == moreDecreasing.size() }
    if (oldIndex > 0) {
        if (decreasingSubs[oldIndex][-1] < ai) decreasingSubs[oldIndex] = moreDecreasing
    } else {
        decreasingSubs << moreDecreasing
    }
}
println increasingSubs.max { it.size() }.join(' ')
println decreasingSubs.max { it.size() }.join(' ')