package algorithms.implementations

class Coord {
    int x
    int y

    Coord plus(Coord other) {
        new Coord(x: x + other.x, y: y + other.y)
    }

    String toString() {
        "<$x,$y>"
    }
}

static Coord offsetOfUpperLeftCorner(int M, int N, int R) {
    def r = R
    if (r < M) return new Coord(x: 0, y: r)
    r -= M - 1
    if (r < N) return new Coord(x: r, y: M - 1)
    r -= N - 1
    if (r < M) return new Coord(x: N - 1, y: M - 1 - r)
    r -= M - 1
    return new Coord(x: N - 1 - r, y: 0)
}

def scanner = new Scanner(System.in)
def parseLine = { -> scanner.nextLine().tokenize(' ').collect { it as int } }
def (M, N, R) = parseLine()
def matrix = new int[M][]
(0..M - 1).each { row -> matrix[row] = parseLine() }

def rotMatrix = new Coord[M][N]       // x = ring, y = offset
def cornerPaths = []
for (int i = 0; i < Math.min(M.intdiv(2), N.intdiv(2)); i++) {
    def (m, n) = [M - 2 * i, N - 2 * i]
    def circleSize = 2 * (m + n) - 4
    def corner = new Coord(x: i, y: i)
    def cornerPath = new Coord[circleSize]
    for (int k = 0; k < circleSize; k++) {
        cornerPath[k] = offsetOfUpperLeftCorner(m, n, k)
    }
    cornerPaths << cornerPath
    for (int k = 0; k < circleSize; k++) {
        def point = cornerPath[k] + corner
        rotMatrix[point.y][point.x] = new Coord(x: i, y: k)
    }
}
def rotatedMatrix = new int[M][N]
(0..M - 1).each { y ->
    (0..N - 1).each { x ->
        def circle = rotMatrix[y][x]
        def (circleNum, circleOffset) = [circle.x, circle.y]
        def activePath = cornerPaths[circleNum]
        def r = (R + circleOffset) % activePath.length
        rotatedMatrix[circleNum + activePath[r].y][circleNum + activePath[r].x] = matrix[y][x]
    }
}
rotatedMatrix*.join(' ').each { println it }