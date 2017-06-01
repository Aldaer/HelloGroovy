package rosalind

def dataset = FASTA.readFromConsole()
def dna = dataset[0].seq
def introns = (1..<dataset.size()).collect { int i -> dataset[i].seq }
introns.each { dna = dna.replaceAll(it, '') }
String mRna = GeneticData.transcribe(dna)
def protein = new OpenReadingFrames().generateProteins(mRna).max { it.length() }
println protein
