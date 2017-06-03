package rosalind

import static rosalind.CommonMath.combinations

static double probabilityOfK(int k, int n) {
    3**(n - k) / 4**n * combinations(n, k)
}

def (generations, N) = new Scanner(System.in).nextLine().tokenize(' ').collect { it as int }
int n = 2**generations
double prob = (N..n).sum { probabilityOfK(it, n) }
//(0..n).each { k -> println "$k -> ${probabilityOfK(k, n)}" }
println "Probability: $prob"