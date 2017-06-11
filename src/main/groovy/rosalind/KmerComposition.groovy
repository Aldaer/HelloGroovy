package rosalind

import groovy.transform.Field

@Field String dna = FASTA.readFromConsole().get(0).seq
nucleotides = ['A', 'C', 'G', 'T']
@Field int kMerLength = 4
kMers = { int k -> k == 1 ? nucleotides : nucleotides.collectMany { nuc -> kMers(k - 1).collect { nuc + it } } }.memoize()

Map<String, Integer> content = [:].withDefault { i -> 0 }
(0..dna.length() - kMerLength).each { i -> content[dna.substring(i, i + kMerLength)]++ }

println kMers(kMerLength).collect { content[it] }.join(' ')