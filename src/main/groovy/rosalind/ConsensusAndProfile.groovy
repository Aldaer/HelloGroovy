package rosalind

def records = FASTA.readFromConsole()
int len = records[0].seq.length()
Map<String, int[]> profile = [A: new int[len], C: new int[len], G: new int[len], T: new int[len]]
records.each { rec -> rec.seq.eachWithIndex { String nucl, int i -> profile[nucl][i]++ } }
String consensus = (0..<len).collect { pos -> profile.keySet().max { nucl -> profile[nucl][pos] } }.join('')
println consensus
profile.entrySet().each { entry ->
    println "${entry.getKey()}: ${entry.getValue().join(' ')}"
}