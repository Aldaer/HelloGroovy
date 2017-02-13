package algorithms.implementations;

def message = new Scanner(System.in).nextLine()
int rows = Math.sqrt(message.length())
cols = rows * rows >= message.length() ? rows : rows + 1
if ((rows*cols) < message.length()) rows++
def result = new StringBuilder()
for (c in 0..cols-1) {
    for (r in 0..rows-1) {
        int index = r * cols + c
        if (index < message.length()) result << message.charAt(index)
    }
    result << ' '
}
println result
