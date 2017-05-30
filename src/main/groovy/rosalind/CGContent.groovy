package rosalind

def fastas = FASTA.readFromConsole()
def contents = fastas.collect { fasta ->
    def cg = fasta.seq.chars.inject(0) { sumCG, chr ->
        chr == FASTA.C || chr == FASTA.G ? sumCG + 1 : sumCG
    }
    new Tuple2(fasta.name, cg / fasta.seq.size())
}

def maxCG = contents.max { it.second }
println maxCG.first
println String.format(Locale.ENGLISH, '%.6f', maxCG.second*100)

