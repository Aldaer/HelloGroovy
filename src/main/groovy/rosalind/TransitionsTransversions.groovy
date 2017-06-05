package rosalind

def transitions = ['AG', 'GA', 'CT', 'TC']

(dna1, dna2) = FASTA.readFromConsole().collect { it.seq.toList().toArray() }
def mutations = (0..<dna1.size()).findAll { i -> dna1[i] != dna2[i] }.collect { i -> dna1[i] + dna2[i] }
int nTransitions = mutations.count { transitions.contains(it) }
int nTransversions = mutations.size() - nTransitions
println nTransitions/nTransversions
