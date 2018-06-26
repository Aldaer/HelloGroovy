import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CodepointTest {
    private static final String WIDE_STRING = "A \uD800\uDC20 B";
    private static final List<String> WIDE_TOKENS = Arrays.asList("A", " ", "\uD800\uDC20", " ", "B");

    @Test
    public void testWideStringFromCodepoints() {
        int wideCodepoint = 0x10020;
        String wideString = "A " + new String(Character.toChars(wideCodepoint)) + " B";
        assertThat(wideString, is(WIDE_STRING));
    }

    @Test
    public void testTokenizeWideString() throws Exception {
        final List<String> tokens = tokenize(WIDE_STRING);
        assertThat(tokens, is(WIDE_TOKENS));
    }

    private List<String> tokenize(String s) {
        if (s.length() == 0) return Collections.emptyList();

        char[] chars = s.toCharArray();

        List<String> tokens = new ArrayList<>();
        boolean insideWhitespace = isWhitespace(s.codePointAt(0));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            int ccp = Character.codePointAt(chars, i);

            if (isWhitespace(ccp) ^ insideWhitespace) {
                tokens.add(sb.toString());
                sb.setLength(0);
                insideWhitespace = !insideWhitespace;
            }

            if (Character.isBmpCodePoint(ccp)) {
                sb.append((char) ccp);
            } else {
                i++;
                sb.append(Character.toChars(ccp));
            }
        }
        if (sb.length() > 0) tokens.add(sb.toString());
        return tokens;
    }

    private boolean isWhitespace(int cp) {
        return cp == 0x20;
    }
}
