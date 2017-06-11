package rosalind

def sc = new Scanner(System.in)
int n = sc.nextInt()
double gcContent = Double.parseDouble(sc.nextLine())
String dna = sc.nextLine()
def probGC = gcContent / 2
def probAT = (1 - gcContent) / 2
Map prob = ['A': probAT, 'T': probAT, 'G': probGC, 'C': probGC]
double probMatch = 1
dna.toList().each { probMatch *= prob[it] }
double xn = -1
double coef = 1.0
double finalProb = 0
for (int i in 1..n) {
    xn = -xn * probMatch
    coef = coef * (n + 1 - i) / i
    double term = xn * coef
    finalProb += term
    if (Math.abs(term) < 0.000001) break
}
println finalProb


