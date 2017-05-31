package rosalind

class Solver {
    private List<String> substrings
    private int N
    private int[][] extraLength
    private int[] bestNextMatch
    private int[] bestBefore

    private List<Integer> sequence

    // How long is the piece of s2 AFTER the prefix which is equal to s1's suffix
    static int findExtraLength(String s1, String s2) {
        def minL = Math.min(s1.length(), s2.length())
        for (int x = minL; x > 0; x--)
            if (s1[-x..-1] == s2[0..x - 1]) return s2.length() - x
        s2.length()
    }

    Solver(List<FASTA> sequences) {
        N = sequences.size()
        substrings = sequences.collect { it.seq }

        extraLength = new int[N][N]
        bestNextMatch = new int[N]
        bestBefore = new int[N]
        Arrays.fill(bestNextMatch, -1)
        Arrays.fill(bestBefore, -1)

        (0..N - 1).each { int i ->
            (0..N - 1).findAll { it != i }.each { int j ->
                int el = findExtraLength(substrings[i], substrings[j])
                extraLength[i][j] = el
                if (el < substrings[j].length() / 2) {
                    bestNextMatch[i] = j
                    bestBefore[j] = i
                }
            }
        }
    }

    String assemblePieces() {
        def result = new StringBuilder(substrings[sequence[0]])
        (1..N - 1).each { i ->
            String nextPiece = substrings[sequence[i]]
            int nextOffset = extraLength[sequence[i - 1]][sequence[i]]
            result << nextPiece[-nextOffset..-1]
        }
        result.toString()
    }

    String solve() {
        int firstOne = bestBefore.findIndexOf { it == -1 }
        sequence = (0..N - 1).collect {
            def current = firstOne
            firstOne = bestNextMatch[current]
            return current
        }
        assemblePieces()
    }
}

def sequences = FASTA.readFromConsole()

def time = System.currentTimeMillis()
def solver = new Solver(sequences)
def sequence = solver.solve()
println "Processing time: ${System.currentTimeMillis() - time} ms"
println "Resulting length: ${sequence.length()}"
println sequence
