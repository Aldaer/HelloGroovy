import static java.lang.StrictMath.sqrt

//TODO: make it right

static def boolean isPrimeOrPowerOf2(int n) {
    (n & (n - 1)) == 0 || !(2..sqrt(n)).any { n % it == 0 }
}

Scanner sc = new Scanner(System.in)
int T = sc.nextLine() as int
def games = new int[T][]
for (i in 0..T - 1) {
    games[i] = sc.nextLine().tokenize(" ").collect { it -> it as int }
}

for (game in games) {
    def noOnes = game.findAll { it -> (it != 1) }
    def (primes, nonprimes) = noOnes.split { isPrimeOrPowerOf2(it) }
    def nPrimes = primes.size()
    def nOthers = nonprimes.size()
    def result = nOthers > 0? nOthers % 2 == 1? 1 : 2 : nPrimes % 2 == 1? 1 : 2
    println "Primes and 2^n: $nPrimes"
    println "Others: ${nonprimes.size}"
    println result
}
