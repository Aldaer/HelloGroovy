import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericCollectionTest {

    private void removeZombies(List<Person> roster) {
        roster.removeIf(Person::isDead);
    }

    private void addAZombie(List<Person> roster) {
        roster.add(new Person("Shawn", true));
    }


    @Test
    public void testPersonRelatedMethods() {
        List<Employee> employeeList = new ArrayList<>();
        removeZombies(employeeList);

        List<Person> personList = new ArrayList<>();
        addAZombie(personList);

        List<Object> stuffList = new ArrayList<>();
        addAZombie(stuffList);

        // addAZombie(employeeList); // We do not want zombies in our company!
    }

    static class Person {
        final String name;
        final boolean dead;

        Person(String name, boolean dead) {
            this.name = name;
            this.dead = dead;
        }

        boolean isDead() {
            return dead;
        }
    }

    static class Employee extends Person {
        final String position;

        public Employee(String name, String position) {
            super(name, false);
            this.position = position;
        }
    }
}
