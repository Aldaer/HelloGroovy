package algorithms.implementations

class Field {
    static int R
    static int C
    int plusArea
    int[][] field

    Field copyOf() {
        new Field(field: field.collect { row -> Arrays.copyOf(row, row.length) })
    }

    int getAt(int coords) {
        field[coords >> 16][coords & 0xFFFF]
    }

    void setAt(int coords, int value) {
        field[coords >> 16][coords & 0xFFFF] = value
    }

    static int[] plusCoords(int r, int c, int size) {
        if (size == 0) return [(r << 16) + c]
        def p1 = (r - size << 16) + c
        def p2 = (r + size << 16) + c
        def p3 = (r << 16) + c - size
        def p4 = (r << 16) + c + size
        [p1, p2, p3, p4]
    }


    List<Field> withPlus(int r, int c) {
        if (field[r][c]) return Collections.emptyList()
        int limit = Math.min(Math.min(r, c), Math.min(R - r - 1, C - c - 1))
        def result = []
        def newField = copyOf()
        for (int i = 0; i <= limit; i++) {

            boolean cannotIncrease = false
            plusCoords(r, c, i).each { xy ->
                cannotIncrease |= newField.getAt(xy) > 0
                newField.setAt(xy, 1)
            }
            if (cannotIncrease) break
            newField.plusArea = i * 4 + 1
            result << newField
            newField = newField.copyOf()
        }
        result
    }

}

def scanner = new Scanner(System.in)
def (R, C) = scanner.nextLine().tokenize(' ').collect { it as int }
Field.R = R
Field.C = C
def field = new int[R][]
(0..R - 1).each { r -> field[r] = scanner.nextLine().chars.collect { c -> c == 'G' as char ? 0 : 1 } }    // BAD = 1
def basicField = new Field(field: field)
def allPlusses = { Field f -> (0..R - 1).collectMany { r -> (0..C - 1).collectMany { c -> f.withPlus(r, c) } } }
def fields = allPlusses(basicField)
println fields.collect { Field fld ->
    def fldPlus = allPlusses(fld)
    def maxArea = fldPlus.collect { it.plusArea }.max() ?: 0
    maxArea * fld.plusArea
}.max()



