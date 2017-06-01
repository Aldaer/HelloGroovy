package rosalind

import groovy.transform.Field

@Field Closure<BigInteger> factor = { BigInteger n -> n > 0 ? n * factor(n - 1) : BigInteger.valueOf(1) }.memoize()

@Field Closure<BigInteger> combinations = { int n, int k -> // n!/(k!(n-k)!)
    factor(n) / (factor(n - k) * factor(k)) as BigInteger
}.memoize()

double probabilityOfK(int k, int n) {
    3**(n - k) / 4**n * combinations(n, k)
}

def (generations, N) = new Scanner(System.in).nextLine().tokenize(' ').collect { it as int }
int n = 2**generations
double prob = (N..n).sum { probabilityOfK(it, n) }
//(0..n).each { k -> println "$k -> ${probabilityOfK(k, n)}" }
println "Probability: $prob"