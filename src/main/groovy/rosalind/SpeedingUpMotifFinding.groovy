package rosalind

String seq = FASTA.readFromConsole().get(0).seq
def failureArray = new int[seq.length()]
for (int k in 2..seq.length()) {
    int j = failureArray[k - 2] + 1

    while (j > 0 && seq.substring(k - j, k) != seq.substring(0, j)) {
        j--
    }
    failureArray[k-1] = j
}

println failureArray.join(' ')