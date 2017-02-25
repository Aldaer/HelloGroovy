package algorithms.implementations

class Bomb {
    int R
    int C
    int[][] field

    void explode(int[][] field, int row, int col) {
        field[row][col] = 0
        if (row > 0) field[row - 1][col] = 0
        if (row < R - 1) field[row + 1][col] = 0
        if (col > 0) field[row][col - 1] = 0
        if (col < C - 1) field[row][col + 1] = 0
    }

    int[][] fullField() {
        def fullField = new int[R][C]
        fullField.each { row -> Arrays.fill(row, 1) }
        fullField
    }

    void nextStep() {
        def newField = fullField()
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                if (field[r][c] == 1)
                    explode(newField, r, c)
        field = newField
    }

    int hashCode() {
        int hc = 0
        field.each { row -> row.each { hc = hc * 37 + it } }
        hc
    }

    void nicePrint() {
        field.collect { row -> row.collect { it -> it == 1 ? 'O' : '.' }.join('') }.each { println it }
    }
}

def scanner = new Scanner(System.in)
def (R, C, N) = scanner.nextLine().tokenize().collect { it as int }
def startingField = new int[R][]
(0..R - 1).each { r -> startingField[r] = scanner.nextLine().chars.collect { c -> c == 'O' as char ? 1 : 0 } }
def bomb = new Bomb(R: R, C: C, field: startingField)
Map<Integer, Integer> hashCodes = [:]
if (N % 2 == 0) {
    def unexploded = new Bomb(R: R, C: C, field: bomb.fullField())
    unexploded.nicePrint()
} else {
    def nSteps = N >> 1
    def shortCircuit = -1
    for (int i = 0; i < nSteps; i++) {
        def hc = bomb.hashCode()
        if (hashCodes.containsKey(hc)) {
            int seen = hashCodes[hc]        // hc(i) == hc(seen)
            int targetStep = (nSteps - seen) % (i - seen) + seen
            if (targetStep < N - i) {
                shortCircuit = targetStep
                break
            }
        } else
            hashCodes[hc] = i
        bomb.nextStep()
    }
    if (shortCircuit>= 0) {
        bomb = new Bomb(R: R, C: C, field: startingField)
        for (int i = 0; i < shortCircuit; i++)
            bomb.nextStep()
    }
    bomb.nicePrint()
}


