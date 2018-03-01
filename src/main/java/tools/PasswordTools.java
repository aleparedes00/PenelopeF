package tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.random;
import static tools.RegexTools.match;

public class PasswordTools {
    /* Password alphabets */
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM = "0123456789";
    private static final String SPECIAL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    private static final String passwordRegex = "^(?=.*[" + ALPHA + "])(?=.*[" + ALPHA_CAPS + "])(?=.*[" + NUM + "])" +
            "(?=.*[" + SPECIAL + "])[" + ALPHA + ALPHA_CAPS + NUM + SPECIAL + "]{8,}";

    public static String generatePassword() {
        Character[] pwdBuilder = new Character[8];

        // At least one lowercase letter
        int random_char = (int) (random() * ALPHA.length());
        pwdBuilder[0] = ALPHA.charAt(random_char);

        // At least one capital letter
        random_char = (int) (random() * ALPHA_CAPS.length());
        pwdBuilder[1] = ALPHA_CAPS.charAt(random_char);

        // At least one number
        random_char = (int) (random() * NUM.length());
        pwdBuilder[2] = NUM.charAt(random_char);

        // At least one SPECIAL character
        random_char = (int) (random() * SPECIAL.length());
        pwdBuilder[3] = SPECIAL.charAt(random_char);

        // Fill the rest
        String alphabet = ALPHA + ALPHA_CAPS + NUM + SPECIAL;
        for (int i = 4; i < 8; i++) {
            random_char = (int) (random() * alphabet.length());
            pwdBuilder[i] = alphabet.charAt(random_char);
        }

        // Shuffle
        List<Character> toShuffle = Arrays.asList(pwdBuilder);
        Collections.shuffle(toShuffle);
        StringBuilder pwd = new StringBuilder();
        for (char c : toShuffle) {
            pwd.append(c);
        }

        return pwd.toString();
    }

    public static boolean hasRightFormat(String pwd) {
        return match(pwd, passwordRegex);
    }
}
