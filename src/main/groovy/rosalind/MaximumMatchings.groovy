package rosalind

import static java.lang.Math.abs
import static java.lang.Math.max
import static rosalind.CommonMath.factor

def seq = FASTA.readFromConsole().get(0).seq.toList()
Map<String, Integer> num = 'AUCG'.toList().collectEntries { nuc -> [(nuc): seq.count { it == nuc }] }
BigInteger prodAT = factor(max(num['A'], num['U'])) / factor(abs(num['U'] - num['A']))
BigInteger prodCG = factor(max(num['C'], num['G'])) / factor(abs(num['C'] - num['G']))

println prodAT * prodCG