package control;

import java.util.Calendar;
import java.util.Date;

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
    
    public static boolean checkDateFormat(String dateString){
        String[] split = dateString.split(":");
        if(split.length != 5){
            return false;
        }
        Date currentDate = new Date();
        if(Integer.parseInt(split[2]) < Calendar.YEAR || Integer.parseInt(split[2]) > Calendar.YEAR + 2){
            return false;
        }
//[dd:mm:yyyy:HH:MM]
        return true;
    }

}
