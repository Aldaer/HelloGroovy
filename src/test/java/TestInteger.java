import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

public class TestInteger {
    @Test
    public void testInteger() throws NoSuchFieldException, IllegalAccessException {
        Integer x = 100;
        final ObjInt objInt = new ObjInt();
        objInt.t = x;
        System.out.printf("x = %d, t = %d\n", x, objInt.t);
        Field intVal= Integer.class.getDeclaredField("value");
        intVal.setAccessible(true);
        intVal.set(x, 200);
        Integer q = 100;
        System.out.printf("x = %d, t = %d, q = %d\n", x, objInt.t, q);
    }

    private class ObjInt {
        Integer t;
    }

    @Test
    public void testCatch() throws IOException {
        try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Catch 1");
            throw e;
        } catch (Exception e) {
            System.out.println("Catch 2");
        }
    }

    @Test
    public void testDouble() throws Exception {
        Double d1 = 1.1d;
        Double d2 = 1.1000d;
        assert Objects.equals(d1, d2);
        int x = 1 & 2;
        new HashMap<Integer,Integer>().put(1,1);
    }
}
