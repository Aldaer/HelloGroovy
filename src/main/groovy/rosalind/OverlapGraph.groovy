package rosalind

def sequences = FASTA.readFromConsole()
def results = []
sequences.each { FASTA entry1 ->
    sequences.each { FASTA entry2 ->
        if (entry1.name != entry2.name) {
            def seq1 = entry1.seq
            def seq2 = entry2.seq
//            int maxK = Math.min(seq1.length(), seq2.length())     // for any K
//            if ((1..maxK).any { k -> seq1[-k..-1] == seq2[0..k - 1] })
            if (seq1[-3..-1] == seq2[0..2])
                results << "${entry1.name} ${entry2.name}"
        }
    }
}
results.each { println it }
