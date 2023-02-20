import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/02/2023
 */
public class TestKeys {
    public static void main(String[] args) {
        Pattern key = Pattern.compile("(bit)|(byte)|(short)|(int)|(float)|(double)|(long)|(throw)|(catch)|(try)|(new)");

        String test5 = "new"; // true
        String test6 = "double"; // true
        String test7 = "aleatorio"; // false
        String test8 = "catch"; // false

        System.out.println("test5 = " + key.matcher(test5).find());
        System.out.println("test6 = " + key.matcher(test6).find());
        System.out.println("test7 = " + key.matcher(test7).find());
        System.out.println("test8 = " + key.matcher(test8).find());

        String text = "// Default\n" +
                "Button button = new Button(\"Button\");\n" +
                "// cancel\n" +
                "button.setCancelButton(true);\n" +
                "// Flat\n" +
                "buttonFlat.getStyleClass(\"btn-flat\");\n" +
                "// GNButton\n" +
                "GNButton gnButton = new GNButton();\n";

        Arrays.stream(text.split(" |\"")).forEach(System.out::println);
    }
}
