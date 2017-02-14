def a = [1, 2, 3, 4]
final int sz = a.size() - 1
println a.withIndex().collectMany { ai, i -> i < sz - 1 ? a.subList(i + 1, sz).collect({ [ai, it] }) : [] }

def t = [1, 2] as Tuple2
println "$t - ${t.class}"