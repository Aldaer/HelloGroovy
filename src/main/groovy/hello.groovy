import org.codehaus.groovy.runtime.typehandling.GroovyCastException

void helloMethod() {
    def x = 1
    int y = 2
    println "Hello from Groovy! 1+2=${x + y} and GStrings are great!"
    println "x is ${x.class}"
    println "y is ${y.class}"
    x += '+'
    println "now x=$x and is ${x.class}"
    try {
        y += '+'
        println "now y=$y and is ${y.class}"
    } catch (GroovyCastException e) {
        println "cannot do that! $e"
    }
}

helloMethod()
