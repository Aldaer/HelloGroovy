import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DynamicOverloadTest {
    static class MyClass {
        void overloaded(A a) {
            System.out.println("Called from A");
        }

        void overloaded(B b) {
            System.out.println("Called from B");
        }
    }

    interface A { };

    interface B { };

    public static void main(String[] args) throws Exception {
        A a = new A() {};
        B b = new B() {};

        MyClass myInstance = new MyClass();
        myInstance.overloaded(a);
        myInstance.overloaded(b);

        System.out.println("Now with some black magic:");
        DynamicOverload.callMethod(myInstance, a);
        DynamicOverload.callMethod(myInstance, b);
    }

    static class DynamicOverload {
        private static final List<Class> SUPPORTED_CLASSES = Arrays.asList(A.class, B.class);
        private static final String METHOD_NAME = "overloaded";

        static void callMethod(MyClass myInstance, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            for (Class supportedClass : SUPPORTED_CLASSES) {
                if (supportedClass.isAssignableFrom(o.getClass())) {
                    Method overloadedMethod = MyClass.class.getDeclaredMethod(METHOD_NAME, supportedClass);
                    overloadedMethod.invoke(myInstance, o);
                    break;
                }
            }
        }
    }
}
