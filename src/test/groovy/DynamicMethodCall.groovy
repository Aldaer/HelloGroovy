/**
 * Created by Artem_Lodygin on 04-Aug-17.
 */
class DynamicMethodCallTarget {
    void one(String param) {
        println "1-$param-1"
    }
    void two(String param) {
        println "2-$param-2"
        def d = callClosure {println "CALLED"}
    }



    static <T> T callClosure(Closure<T> t) {
        t()
    }
}

def dmc = new DynamicMethodCallTarget()
def methodName = 'one'
dmc."$methodName"("uno")
methodName = 'two'
dmc."$methodName"("duo")
