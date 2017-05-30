package rosalind

class FASTA {
    String name
    String seq

    static final A = 'A' as char
    static final C = 'C' as char
    static final G = 'G' as char
    static final T = 'T' as char

    static List<FASTA> readFromConsole() {
        Scanner sc = new Scanner(System.in)
        def name = sc.nextLine()
        def current = new StringBuilder()
        def result = []
        while (true) {
            def seq = sc.nextLine()
            if (seq.startsWith('>') || !seq) {
                result << new FASTA(name: name.substring(1), seq: current.toString())
                if (seq) {
                    current.setLength(0)
                    name = seq
                } else break
            } else {
                current << seq
            }
        }
        result
    }
}
