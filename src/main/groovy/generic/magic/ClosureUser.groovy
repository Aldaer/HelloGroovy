package generic.magic

/**
 * Created by Artem_Lodygin on 01-Dec-17.
 */
class ClosureUser {
    static Closure myClosure

    static String[] setByClosure() {
        try {
            String[] s = ["init"]
            myClosure = { a ->
                println "Setting value: $a"
                s[0] = a
            }
            myClosure.call("preset")
            return s
        }
        finally {
            myClosure.call("finally")
        }
    }

    static void main(String[] args) {
        def a = setByClosure()
        println "a = $a"
        myClosure.call("text")
        println "a = $a"
    }
}
