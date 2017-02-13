package algorithms.implementations;

def sc = new Scanner(System.in)
int n = sc.nextLine() as int
def strings = new String[n]
(0..n-1).each { strings[it] = sc.nextLine() }
strings.each { println rearrange(it) }

static String rearrange(String s) {
    boolean cannotDo = true
    int i
    char[] sc = s.toCharArray()
    for (i = s.length() - 1; cannotDo && i > 0; i--) {
        cannotDo = sc[i] <= sc[i - 1]
    }
    if (cannotDo) return 'no answer'

    char toReplace = sc[i]
    char[] tailChars = s.substring(i + 1).toCharArray()
    Arrays.sort(tailChars)
    int ix = Arrays.binarySearch(tailChars, toReplace.next())
    if (ix < 0) ix = -ix - 1
    char replacement = tailChars[ix]
    tailChars[ix] = toReplace

    def result = new StringBuilder(s.length())
    result << s.substring(0, i) << replacement
    tailChars.each { result.append(it) }
    result
}