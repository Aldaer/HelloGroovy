package algorithms.implementations

static String numberInWords(int n) {
    switch (n) {
        case 10: return 'ten'
        case 11: return 'eleven'
        case 12: return 'twelve'
        case 13: return 'thirteen'
        case 14: return 'fourteen'
        case 15: return 'fifteen'
        case 16: return 'sixteen'
        case 17: return 'seventeen'
        case 18: return 'eighteen'
        case 19: return 'nineteen'
    }
    def num = ''
    switch (n.intdiv(10)) {
        case 2: num = 'twenty'; break
        case 3: num = 'thirty'; break
        case 4: num = 'forty'; break
        case 5: num = 'fifty'; break
    }
    num += ' '
    switch (n % 10) {
        case 1: num += 'one'; break
        case 2: num += 'two'; break
        case 3: num += 'three'; break
        case 4: num += 'four'; break
        case 5: num += 'five'; break
        case 6: num += 'six'; break
        case 7: num += 'seven'; break
        case 8: num += 'eight'; break
        case 9: num += 'nine'; break
    }
    num.trim()
}

static String minuteForm(int n) {
    def minute = 'minute'
    if (n % 10 != 1 || n == 11) minute += 's'
    minute
}

static String time(int h, int m) {
    int nextH = h %12 + 1
    switch (m) {
        case 0: return h.inWords() + ' o\' clock'
        case 15: return 'quarter past ' + h.inWords()
        case 30: return 'half past ' + h.inWords()
        case 45: return 'quarter to ' + nextH.inWords()
        case 31..59:
            int mm = 60 - m
            return mm.inWords() + ' ' + mm.minutes() + ' to ' + nextH.inWords()
        default:
            return m.inWords() + ' ' + m.minutes() + ' past ' + h.inWords()
    }
}

Scanner sc = new Scanner(System.in)
Integer.metaClass.inWords = { numberInWords(delegate as int) }
Integer.metaClass.minutes = { minuteForm(delegate as int) }
def (h, m) = [sc.nextInt(), sc.nextInt()]
println time(h, m)
