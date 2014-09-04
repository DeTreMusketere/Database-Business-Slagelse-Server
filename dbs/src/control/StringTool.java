
package control;

/**
 *
 * @author Patrick
 */
public class StringTool {
    
    /**
     * Converts a string to replace æ, ø and å with unicode values
     * @param string The string to convert.
     * @return Converted string.
     */
    public static String convertÆØÅ(String string){
        String s1 = string.replaceAll("æ", "\u00E6");
        String s2 = s1.replaceAll("Æ", "\u00C6");
        String s3 = s2.replaceAll("ø", "\u00F8");
        String s4 = s3.replaceAll("Ø", "\u00D8");
        String s5 = s4.replaceAll("å", "\u00E5");
        String s6 = s5.replaceAll("Å", "\u00C5");
        
        return string;
    }

}
