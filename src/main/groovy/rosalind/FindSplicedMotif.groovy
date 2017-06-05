package rosalind

(seq, subseq) = FASTA.readFromConsole().collect { it.seq }
def result = []
int posA = 0
int posB = 0
byte[] seqBytes = seq.bytes
byte[] subseqBytes = subseq.bytes
while (posA < seqBytes.size() && posB < subseqBytes.size()) {
    if (seqBytes[posA++] == subseqBytes[posB]) {
        result << posA
        posB++
    }
}
assert result.size() == subseqBytes.size()
println result.join(' ')

