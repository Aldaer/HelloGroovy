package rosalind

import groovy.transform.Field

@Field int len

boolean hammingDistanceEquals1(String s1, String s2) {
    def b1 = s1.bytes
    def b2 = s2.bytes
    int dist = 0
    for (int i in 0..<len) {
        if (b1[i] != b2[i] && ++dist > 1) return false
    }
    dist == 1
}

def database = FASTA.readFromConsole().collect { it.seq }
len = database.get(0).length()
Map<String, Integer> numOccurring = [:].withDefault { it -> 0 }
database.each { seq ->
    numOccurring[seq]++
    numOccurring[GeneticData.reverseComplement(seq)]++
}
def errors = database.findAll { numOccurring[it] == 1 }
def correct = numOccurring.entrySet().findAll { it.value > 1 }.collect { it.key }
int halfLen = database.get(0).length() >> 1
def firstHalf = correct.collect { it.substring(0, halfLen) }
def secondHalf = correct.collect { it.substring(halfLen) }
int n = correct.size()
errors.each { errSeq ->
    def efh = errSeq.substring(0, halfLen)
    def esh = errSeq.substring(halfLen)
    def candidates = (0..<n).findAll { int i -> efh == firstHalf[i] || esh == secondHalf[i] }.collect { int i -> correct[i] }
    def correctSeq = candidates.find { s -> hammingDistanceEquals1(s, errSeq) }
    println "$errSeq->$correctSeq"
}
