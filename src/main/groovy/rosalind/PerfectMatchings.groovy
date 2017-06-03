package rosalind

import static rosalind.CommonMath.factor

String seq = FASTA.readFromConsole().get(0).seq
int numAU = seq.toList().count { it == 'A' }
int numCG = seq.toList().count { it == 'C' }
def numPerfectMatches = factor(numAU) * factor(numCG)
println numPerfectMatches