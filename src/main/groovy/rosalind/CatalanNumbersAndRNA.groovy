package rosalind

import groovy.transform.Field

@Field String seq = FASTA.readFromConsole().get(0).seq
@Field int len = seq.length()
@Field Map<String, int[]> mapAUCG = [A: new int[len + 1], U: new int[len + 1], C: new int[len + 1], G: new int[len + 1]]
@Field Map<String, Integer> numAUCG = [A: 0, U: 0, C: 0, G: 0]
seq.toList().eachWithIndex { nuc, i ->
    numAUCG[nuc] += 1
    ['A', 'U', 'C', 'G'].each { mapAUCG[it][i + 1] = numAUCG[it] }
}
complementTable = [A: 'U', U: 'A', C: 'G', G: 'C']

numPerfectMatchings = { int minIndex, int maxIndex ->
    if (maxIndex < minIndex) return 1G
    int numA = mapAUCG['A'][maxIndex + 1] - mapAUCG['A'][minIndex]
    int numU = mapAUCG['U'][maxIndex + 1] - mapAUCG['U'][minIndex]
    int numC = mapAUCG['C'][maxIndex + 1] - mapAUCG['C'][minIndex]
    int numG = mapAUCG['G'][maxIndex + 1] - mapAUCG['G'][minIndex]
    if (numA != numU || numC != numG) return 0G
    if (maxIndex == minIndex + 1) return 1G
    BigInteger numFound = 0
    String match = complementTable[seq[minIndex]]
    for (int pos = minIndex + 1; pos <= maxIndex; pos++)
        if (seq[pos] == match) {
            BigInteger numFoundInside = numPerfectMatchings(minIndex + 1, pos - 1)
            BigInteger numFoundOutside = numPerfectMatchings(pos + 1, maxIndex)
            numFound += numFoundInside * numFoundOutside
        }
    numFound
}.memoize()

println numPerfectMatchings(0, len - 1)