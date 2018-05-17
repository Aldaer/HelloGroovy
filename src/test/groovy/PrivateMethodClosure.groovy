//@CompileStatic
class PrivateMethodClosure {
    int countEvens(List<Integer> ints) {
        ints.count { n ->
            println owner.class.name
            isEven(n) } as int
    }

    private boolean isEven(int i) {
        i % 2 == 0
    }
}
