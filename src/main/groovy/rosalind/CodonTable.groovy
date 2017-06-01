package rosalind

class CodonTable {
    static final TABLE = [UUU: 'F', CUU: 'L', AUU: 'I', GUU: 'V',
                          UUC: 'F', CUC: 'L', AUC: 'I', GUC: 'V',
                          UUA: 'L', CUA: 'L', AUA: 'I', GUA: 'V',
                          UUG: 'L', CUG: 'L', AUG: 'M', GUG: 'V',
                          UCU: 'S', CCU: 'P', ACU: 'T', GCU: 'A',
                          UCC: 'S', CCC: 'P', ACC: 'T', GCC: 'A',
                          UCA: 'S', CCA: 'P', ACA: 'T', GCA: 'A',
                          UCG: 'S', CCG: 'P', ACG: 'T', GCG: 'A',
                          UAU: 'Y', CAU: 'H', AAU: 'N', GAU: 'D',
                          UAC: 'Y', CAC: 'H', AAC: 'N', GAC: 'D',
                          UAA: '.', CAA: 'Q', AAA: 'K', GAA: 'E',
                          UAG: '.', CAG: 'Q', AAG: 'K', GAG: 'E',
                          UGU: 'C', CGU: 'R', AGU: 'S', GGU: 'G',
                          UGC: 'C', CGC: 'R', AGC: 'S', GGC: 'G',
                          UGA: '.', CGA: 'R', AGA: 'R', GGA: 'G',
                          UGG: 'W', CGG: 'R', AGG: 'R', GGG: 'G']

    static final COMPLEMENT = [('A' as char): 'T' as char,
                               ('T' as char): 'A' as char,
                               ('G' as char): 'C' as char,
                               ('C' as char): 'G' as char]

    static String transcribe(String dna) {
        dna.replaceAll('T', 'U')
    }

    static String reverseComplement(String dna) {
        int l = dna.length()
        char[] revCompl = new char[l]
        (0..<dna.length()).each { i -> revCompl[i] = COMPLEMENT[dna.chars[l - i - 1]] }
        new String(revCompl)
    }
}
