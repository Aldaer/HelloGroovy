package rosalind


final CODON_TABLE = [UUU: 'F', CUU: 'L', AUU: 'I', GUU: 'V',
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

def mRna = new Scanner(System.in).nextLine()
int orf = 0
def protein = new StringBuilder()
while (orf < mRna.length() - 2) {
    String amino = CODON_TABLE[mRna[orf..orf + 2]]
    if (amino != '.')
        protein << amino
    else
        break
    orf += 3
}
println protein.toString()
