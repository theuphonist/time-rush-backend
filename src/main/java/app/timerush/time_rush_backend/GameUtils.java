package app.timerush.time_rush_backend;

import java.util.Random;

public class GameUtils {

    private GameUtils() {};
    
    public static String generateJoinCode(int length) {
        String joinCode = "";

        for (int i=0; i < length; i++) {
            final char nextCharacter = generateRandomNumberOrCapitalLetter();
            joinCode = joinCode.concat(String.valueOf(nextCharacter));
        }

        return joinCode;
    }

    private static char generateRandomNumberOrCapitalLetter() {

        final Random random = new Random();
        final int randomInteger = random.nextInt(36);

        // map 0-9 to unicode 48-57 (numeric characters)
        // map 10-35 to unicode 65-90 (latin capital letters)
        final int codePoint = randomInteger + (randomInteger < 10 ? 48 : 55);

        return (char)codePoint;
    }
}
