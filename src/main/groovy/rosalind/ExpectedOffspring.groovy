package rosalind

// Values denote a number of offspring with dominant phenotype (per 2 children)
def genotypes = ['AA-AA': 2.0, 'AA-Aa': 2.0, 'AA-aa': 2.0, 'Aa-Aa': 1.5, 'Aa-aa': 1.0, 'aa-aa': 0.0]

def quantities = new Scanner(System.in).nextLine().tokenize(' ').collect { it as int }
double offs = 0
genotypes.eachWithIndex { genotype, int i -> offs += quantities[i] * genotype.value }
println offs
