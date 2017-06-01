package rosalind

def aminoAcids = GeneticData.CODONS.values().unique(false)
Map<Character, Integer> codonCount = aminoAcids.collectEntries { amino ->
    [amino.getChars()[0], GeneticData.CODONS.entrySet().count { it.value == amino }]
}

def protein = new Scanner(System.in).nextLine()
long variants = 1
protein.chars.each { aa -> variants = variants * codonCount[aa] % 1_000_000L }
variants = variants * codonCount['.' as char] % 1_000_000L
println variants