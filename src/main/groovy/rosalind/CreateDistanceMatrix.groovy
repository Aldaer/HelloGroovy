package rosalind

def dataset = FASTA.readFromConsole().collect { it.seq }
def n = dataset.size()
def l = dataset.get(0).length()
def matrix = new double[n][n]
for (int i in 0..<n - 1) {
    for (int j in i + 1..<n) {
        int d = 0
        for (int k in 0..<l) if (dataset[i][k] != dataset[j][k]) d++
        def dist = d / l
        matrix[i][j] = dist
        matrix[j][i] = dist
    }
}
for (int i in 0..<n)
    println matrix[i].join(' ')

