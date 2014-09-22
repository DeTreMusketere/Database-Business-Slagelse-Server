package control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Patrick
 */
public class StringTool {

    /**
     * Converts a string to replace æ, ø and å with unicode values
     *
     * @param string The string to convert.
     * @return Converted string.
     */
    public static String convertÆØÅ(String string) {
        String s1 = string.replaceAll("æ", "\u00E6");
        String s2 = s1.replaceAll("Æ", "\u00C6");
        String s3 = s2.replaceAll("ø", "\u00F8");
        String s4 = s3.replaceAll("Ø", "\u00D8");
        String s5 = s4.replaceAll("å", "\u00E5");
        String s6 = s5.replaceAll("Å", "\u00C5");

        return string;
    }

    /**
     * Capitalizes all words in a string. Use this for capitalizing names.
     *
     * @param string The string to capitalize.
     * @return The original string with capitalized words.
     */
    public static String capitalizeWords(String string) {
        String resultString = "";
        String[] split = string.split(" ");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            resultString += s.substring(0, 1).toUpperCase() + s.substring(1);
            if (i + 1 < split.length) {
                resultString += " ";
            }
        }
        return resultString;
    }

    /**
     * Capitalizes sentences and will add a "." at the end of the string, if it
     * isn't already there.
     *
     * @param string The string to capitalize.
     * @return The original string with capitalized sentences and a "." at the
     * end.
     */
    public static String capitalizeSentences(String string) {
        String resultString = "";
        String[] split = string.split("\\Q. \\E");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            System.out.println(split[i]);
            resultString += s.substring(0, 1).toUpperCase() + s.substring(1);
            if (i + 1 < split.length) {
                resultString += ". ";
            }
        }
        if (resultString.charAt(resultString.length() - 1) != '.') {
            resultString += ".";
        }
        return resultString;
    }

    /**
     * Checks if a string uses the proper dateFormat used in this program.
     *
     * @param dateString The string to check. Has to be written
     * "dd:MM:yyyy:HH:mm"
     * @return True if the format is correct, false if not.
     */
    public static boolean checkDateFormat(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy:HH:mm");
        LocalDateTime time = null;
        try {
            time = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            return false;
        }
        String[] split = dateString.split(":");
        int day = Integer.parseInt(split[0]);
        switch (time.getMonthValue()) {
            case 2:
                if (time.getYear() % 4 == 0) {
                    if (day > 29) {
                        return false;
                    }
                    break;
                } else if (day > 28) {
                    return false;
                }
                break;
            case 4:
                if (day > 30) {
                    return false;
                }
                break;
            case 6:
                if (day > 30) {
                    return false;
                }
                break;
            case 9:
                if (day > 30) {
                    return false;
                }
                break;
            case 11:
                if (day > 30) {
                    return false;
                }
                break;
        }
        return true;
    }

}
