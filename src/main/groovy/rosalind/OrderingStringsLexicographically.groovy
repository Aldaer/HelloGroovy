package rosalind

def sc = new Scanner(System.in)
def alternateAlphabet = sc.nextLine().tokenize(' ')
def alphabet = 'ABCDEFGHIJKL'.substring(0, alternateAlphabet.size()).toList()
int n = sc.nextInt()
permutations = { k ->
    k == 1 ? alphabet : permutations(k - 1).collectMany { perm ->
        alphabet.collect { it + perm }
    }
}.memoize()
List<String> allStrings = []
for (i in 1..n) {
    allStrings += permutations(i)
}
allStrings.sort(true)
byte offsetOfA = 'A'.bytes[0]
for (int i in 0..<allStrings.size()) {
    byte[] bs = allStrings[i].bytes
    for (int k in 0..<bs.size())
        bs[k] = alternateAlphabet[bs[k] - offsetOfA].bytes[0]
    allStrings[i] = new String(bs)
}
allStrings.each { println it }