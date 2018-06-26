package algorithms;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CharContainerTest {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    CharContainer listContainer;
    CharContainer setContainer;
    CharContainer arrayContainer;

    Character[] bigCharacterArray;

    @Before
    public void setUp() throws Exception {
        listContainer = new CharContainer() {
            List<Character> characterList = chars.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());

            @Override
            public boolean contains(Character c) {
                return characterList.contains(c);
            }
        };

        setContainer = new CharContainer() {
            TreeSet<Character> characterSet = chars.chars().mapToObj(ch -> (char) ch).collect(Collectors.toCollection(TreeSet::new));

            @Override
            public boolean contains(Character c) {
                return characterSet.contains(c);
            }
        };

        arrayContainer = new CharContainer() {
            char[] charArray = chars.toCharArray();

            @Override
            public boolean contains(Character c) {
                return Arrays.binarySearch(charArray, c) >= 0;
            }
        };

        bigCharacterArray = new Random().ints(64, 150).limit(10_000_000).mapToObj(i -> (char) i).toArray(Character[]::new);
    }

    @Test
    public void testContains() {
        System.out.println("List container:");
        runTest(listContainer);
        System.out.println("Set container:");
        runTest(setContainer);
        System.out.println("Sorted array container:");
        runTest(arrayContainer);
    }


    private void runTest(CharContainer container) {
        int hits = 0;
        int misses = 0;
        long startTime = System.currentTimeMillis();
        for (Character c : bigCharacterArray) {
            if (container.contains(c)) hits++;
            else misses++;
        }

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("   Hits: " + hits);
        System.out.println("   Misses: " + misses);
        System.out.println("   Elapsed: " + elapsed);
    }

}