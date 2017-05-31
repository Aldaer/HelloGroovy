package rosalind

import java.util.regex.Matcher
import java.util.regex.Pattern

final Pattern GLYCO = ~'N[^P][ST][^P]'

class GlycosylationData {
    String name
    List<Integer> locations         // 1-based
}

sc = new Scanner(System.in)
def proteins = []
String s
while (s = sc.nextLine()) proteins << s

def fastas = proteins.collect { protein ->
    def url = new URL("http://www.uniprot.org/uniprot/${protein}.fasta")
    def seq = FASTA.getFromURL(url).get(0)
    seq.name = protein
    seq
}

def glycoData = fastas.collect { fasta ->
    Matcher m = GLYCO.matcher(fasta.seq)
    def locations = []
    int pos = 0
    while (m.find(pos)) {
        pos = m.start() + 1
        locations << pos
    }
    new GlycosylationData(name: fasta.name, locations: locations)
}.findAll { it.locations }

glycoData.each { println "${it.name}\n${it.locations.join(' ')}" }
