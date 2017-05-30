package rosalind

Scanner sc = new Scanner(System.in)
String dna = sc.nextLine()
def nucs = [('A' as char): 0,
            ('C' as char): 0,
            ('G' as char): 0,
            ('T' as char): 0]
dna.chars.each { nucs[it]++ }
nucs.values().each { print "${it} " }