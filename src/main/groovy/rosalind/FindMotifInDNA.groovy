package rosalind

import java.util.regex.Matcher

Scanner sc = new Scanner(System.in)
(str, substr) = (1..2).collect { sc.nextLine() }
Matcher matcher = (~substr).matcher(str)
def result = []
def pos = 0
while (matcher.find(pos)) {
    result << (pos = matcher.start() + 1)
}
println result.join(' ')