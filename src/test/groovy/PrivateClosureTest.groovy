import org.junit.Test

class PrivateClosureTest {

    @Test
    void testCall() throws Exception {
        def pmc = new PrivateMethodClosure()
        assert pmc.countEvens([0,1,2]) == 2

        def pmc2 = new PrivateMethodClosure(){}
        assert pmc2.countEvens([0,1,2]) == 2
    }
}
