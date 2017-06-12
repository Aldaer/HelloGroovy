package rosalind

import java.util.regex.Matcher

(dna1, dna2) = FASTA.readFromConsole().collect { it.seq }
List<Tuple2<String, Integer>> commonSubsequences = [new Tuple2<String, Integer>('', 0)]
Map<String, Matcher> dnaMatchers = ['A', 'T', 'C', 'G'].collectEntries { [it, dna1 =~ it] }
dna2.toList().each { nuc ->
    def matcher = dnaMatchers[nuc]
    List<Tuple2<String, Integer>> newSubsequences = commonSubsequences.collect { subseq ->
        int offset = subseq.second
        matcher.find(offset) ? new Tuple2((subseq.first + nuc), matcher.start() + 1) : null
    }.findAll()
    newSubsequences.each { newSubseq ->
        int len = newSubseq.first.length()
        if (commonSubsequences.size() < len + 1) {
            commonSubsequences << newSubseq
        } else {
            def oldSubseq = commonSubsequences[len]
            if (oldSubseq.second > newSubseq.second) commonSubsequences[len] = newSubseq
        }
    }
}
println commonSubsequences[-1].first


