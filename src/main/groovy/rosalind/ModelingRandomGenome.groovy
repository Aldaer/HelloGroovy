package rosalind

def sc = new Scanner(System.in)
String seq = sc.nextLine()
def gcContent = sc.nextLine().tokenize(' ').collect { it as double }
def randomOccurenceProbability = gcContent.collect { double gc ->
    double logProbG = Math.log10(gc / 2)
    double logProbA = Math.log10((1 - gc) / 2)
    def logProbMap = ['A': logProbA, 'T': logProbA, 'G': logProbG, 'C': logProbG]
    seq.toList().collect { logProbMap[it] }.sum() as double
}
println randomOccurenceProbability.collect { String.format(Locale.ENGLISH, '%.3f', it) }.join(' ')