package algorithms.implementations;

import java.util.Scanner;

public class BiggerIsGreater {

    private static String rearrange(String s) {
        boolean cannotDo = true;
        int i;
        for (i = s.length() - 1; cannotDo && i > 0; i--) {
            cannotDo = s.charAt(i) <= s.charAt(i - 1);
        }
        if (cannotDo) return "no answer";

        char toReplace = s.charAt(i);
        String tail = s.substring(i + 1);
        char replacement = tail.charAt(0);
        for (char c : tail.toCharArray()) {
            if (c > toReplace && c < replacement) replacement = c;
        }
        StringBuilder result = new StringBuilder(s.substring(0, i)).append(replacement);
        tail.replaceFirst("" + replacement, "" + toReplace).chars().sorted().forEachOrdered(c -> result.append((char) c));
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++)
            strings[i] = sc.nextLine();

        for (String s : strings) System.out.println(rearrange(s));
    }
}