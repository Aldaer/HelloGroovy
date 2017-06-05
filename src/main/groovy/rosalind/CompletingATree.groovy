package rosalind

void addToComponent(int k) {
    adjMatrix[k][k] = 2
    for (int c = 0; c < n; c++)
        if (adjMatrix[k][c] == 1) {
            adjMatrix[k][c] = 2
            adjMatrix[c][k] = 2
            addToComponent(c)
        }
}

def sc = new Scanner(System.in)
n = sc.nextLine() as int
adjMatrix = new int[n][n]
String ln = sc.nextLine()
while (ln) {
    def (a, b) = ln.tokenize(' ').collect { (it as int) - 1 }
    adjMatrix[a][b] = 1
    adjMatrix[b][a] = 1
    ln = sc.nextLine()
}

for (int i in (0..<n)) adjMatrix[i][i] = 1

def nComponents = 0
for (int i in (0..n - 1)) {
    if (adjMatrix[i][i] == 1) {
        nComponents++
        addToComponent(i)
    }
}
println nComponents - 1