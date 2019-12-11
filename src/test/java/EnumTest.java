import java.util.*;

public class EnumTest {
    static final Map<Integer, MyEnum> ENUM_MAP = new HashMap<>();
//    static final MyEnum DEFAULT = MyEnum.ONE;

    public enum MyEnum {
        ONE(1),
        TWO(2),
        THREE(3);

        private final int value;

        MyEnum(int value) {
            this.value = value;
            ENUM_MAP.put(value, this);
        }
    }

    public static void main(String[] args) {
        System.out.println(ENUM_MAP.get(1));
        System.out.println(ENUM_MAP.get(2));
        System.out.println(ENUM_MAP.get(3));
    }
}