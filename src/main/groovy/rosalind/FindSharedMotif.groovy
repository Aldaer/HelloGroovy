package rosalind

import static SuffixNode.numeric

class SuffixNode {
    private static final CHAR_TO_INT = ['A': 0, 'T': 1, 'G': 2, 'C': 3]
    private static final INT_TO_CHAR = [0: 'A', 1: 'T', 2: 'G', 3: 'C']

    boolean flagged

    SuffixNode[] next = new SuffixNode[4]

    int setFlagAndCount(boolean to) {
        int result = 1
        flagged = to
        for (int i = 0; i < 4; i++)
            if (next[i]) result += next[i].setFlagAndCount(to)
        result
    }

    void pruneUnflagged() {
        (0..3).each { i -> if (!next[i]?.flagged) next[i] = null else next[i].pruneUnflagged() }
    }

    void fillSubstrings(int[] source, int index) {
        if (index >= source.size()) return
        def nxt = next[source[index]] ?: new SuffixNode()
        next[source[index]] = nxt
        nxt.fillSubstrings(source, index + 1)
    }

    int flagMatches(int[] source, int index) {
        int result = 0
        if (!flagged) {
            flagged = true
            result++
        }
        if (index < source.size() && next[source[index]]) result += next[source[index]].flagMatches(source, index + 1)
        result
    }

    List<String> reconstructSuffixes() {
        def result = []
        for (int i = 0; i < 4; i++) {
            if (next[i])
                result += next[i].reconstructSuffixes().collect { INT_TO_CHAR[i] + it }
        }
        result ? result : Collections.singletonList('')
    }

    static int[] numeric(String s) {
        def result = new int[s.length()]
        s.toList().eachWithIndex { String nuc, int i -> result[i] = CHAR_TO_INT[nuc] }
        result
    }
}

def sequences = FASTA.readFromConsole().collect { numeric(it.seq) }.sort { a1, a2 -> a2.size() - a1.size() }
def root = new SuffixNode()

def first = sequences[0]
(0..<first.size()).each {
    root.fillSubstrings(first, it)
}
for (int i = 1; i < sequences.size(); i++) {
    int unmarkedNodes = root.setFlagAndCount(false)
    int[] seq = sequences[i]
    for (int j = 0; j < seq.size() && unmarkedNodes > 0; j++) {
        unmarkedNodes -= root.flagMatches(seq, j)
    }
    if (unmarkedNodes > 0) root.pruneUnflagged()
}

println root.reconstructSuffixes().max { a, b -> a.length() - b.length() }
