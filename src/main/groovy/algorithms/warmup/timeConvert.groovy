package algorithms.warmup

parts = new Scanner(System.in).nextLine().tokenize(':')
h = (parts[0] as int) % 12
if (parts[2].substring(2) == 'PM') h += 12
printf "%02d:${parts[1]}:${parts[2].substring(0, 2)}", h
