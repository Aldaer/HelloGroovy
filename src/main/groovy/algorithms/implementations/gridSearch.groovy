package algorithms.implementations

class TestCase {
    String[] grid
    String[] pattern

    boolean found() {
        def patternCols = pattern[0].length()
        for (int r = 0; r <= grid.length - pattern.length; r++) {
            grid[r].findAll()
            String s = grid[r]
            int i = s.indexOf(pattern[0])
            while (i >= 0) {
                def block = (r..r + pattern.length - 1).collect { j -> grid[j].substring(i, i + patternCols) }
                if (block.withIndex().every { row, index -> row == pattern[index] }) return true
                i = s.indexOf(pattern[0], i + 1)
            }
        }
        false
    }
}

Scanner sc = new Scanner(System.in)
def t = sc.nextLine() as int
def testCases = (1..t).collect {
    def (r, c) = sc.nextLine().tokenize(' ').collect { it as int }
    def grid = (1..r).collect({ sc.nextLine() }).toArray(new String[r])
    def (rp, cp) = sc.nextLine().tokenize(' ').collect { it as int }
    def pattern = (1..rp).collect({ sc.nextLine() }).toArray(new String[rp])
    new TestCase(grid: grid, pattern: pattern)
}
testCases.each { println(it.found() ? 'YES' : 'NO') }
