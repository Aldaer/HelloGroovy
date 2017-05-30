package rosalind

Scanner sc = new Scanner(System.in)
String dna = sc.nextLine()
int sz = dna.size() - 1
def compl = new char[sz + 1]
def map = [('A' as char): 'T'as char, ('C'as char): ('G'as char), ('T'as char): 'A'as char, ('G'as char): 'C'as char]
dna.chars.eachWithIndex { char chr, int i ->
    compl[sz - i] = map[chr] }
println String.valueOf(compl)