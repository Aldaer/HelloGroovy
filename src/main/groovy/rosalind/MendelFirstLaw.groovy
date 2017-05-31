package rosalind

def sc = new Scanner(System.in)
def (k, m, n) = sc.nextLine().tokenize(' ').collect { it as int }

def pop = k + m + n
//double p = (k * (k - 1) + k * m + k * n + m * k + m * (m - 1) * 3 / 4 + m * n / 2 + n * k + n * m / 2) / (pop * (pop - 1))
double p = (k * (k - 1) + 2 * k * (m + n) + m * (m - 1) * 3 / 4 + m * n) / (pop * (pop - 1))

println String.format(Locale.ENGLISH, '%.5f', p)