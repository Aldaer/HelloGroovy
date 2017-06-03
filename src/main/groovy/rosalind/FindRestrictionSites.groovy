package rosalind

String dna = FASTA.readFromConsole().get(0).seq
String cdna = GeneticData.reverseComplement(dna).reverse()

List<Tuple2> result = []
(4..12).each { int len ->
    for (int pos = 0; pos <= dna.length() - len; pos++) {
        int end = pos + len
        String locus1 = dna.substring(pos, end)
        String locus2 = cdna.substring(pos, end).reverse()
        if (locus1 == locus2) result << new Tuple2(pos, len)
    }
}
result.sort { a, b -> a.first - b.first }.each { println "${it.first + 1} ${it.second}" }



