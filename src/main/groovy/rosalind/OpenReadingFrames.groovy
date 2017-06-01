package rosalind

import groovy.transform.Field

import static GeneticData.reverseComplement
import static GeneticData.transcribe

@Field List<String> STOP_CODONS = ['UAA', 'UAG', 'UGA']

static List<Integer> startCodonPositions(String mRna) {
    def m = (mRna =~ 'AUG')
    def result = new ArrayList<Integer>()
    while (m.find()) result << m.start()
    result
}

List<String> orfCodons(String mRna, int startCodonPosition) {
    def s = mRna.substring(startCodonPosition)
    def codons = s.toList().collate(3)*.join('')
    int x = codons.findIndexOf { STOP_CODONS.contains(it) }
    x > 0 ? codons.subList(0, x) : Collections.emptyList()
}

List<String> generateProteins(String mRna) {
    startCodonPositions(mRna).collect { pos ->
        orfCodons(mRna, pos).collect { GeneticData.CODONS[it] }.join('')
    }.findAll()
}

def seq = FASTA.readFromConsole().get(0).seq

def proteins = generateProteins(transcribe(seq))
proteins += generateProteins(transcribe(reverseComplement(seq)))
proteins.unique().each { println it }

