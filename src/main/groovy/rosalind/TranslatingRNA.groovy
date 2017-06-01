package rosalind

def mRna = new Scanner(System.in).nextLine()
int orf = 0
def protein = new StringBuilder()
while (orf < mRna.length() - 2) {
    String amino = GeneticData.CODONS[mRna[orf..orf + 2]]
    if (amino != '.')
        protein << amino
    else
        break
    orf += 3
}
println protein.toString()
