package io.github.gleidsonmt.dashboardfx.material;

import eu.hansolo.colors.ColorHelper;
import eu.hansolo.colors.MaterialDesign;
import io.github.gleidsonmt.dashboardfx.utils.HoverAnimation;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/03/2025
 */
public class MaterialItem extends StackPane {

    private final String color;

    public MaterialItem(MaterialDesign color) {
        this.color = color.name();
        this.setMinSize(50, 50);
        this.setMaxSize(50, 50);
//        this.setMaxSize(80, 70);
        String name = color.name().replace("_", " ");
        String mod = color.name().replaceAll("_", "-").toLowerCase();
        String strWeb = ColorHelper.web(color.get());
        String strRgb = ColorHelper.rgb(color.get());
        String _text = String.join("", name, "\n", strWeb, "\n", strRgb, "\n", "-"+name.toLowerCase().replaceAll(" ", "-"));

        this.getStyleClass().addAll("round", "padding-10", "depth-1", "border-2", "bg-" + mod);

        this.setCursor(Cursor.HAND);
        Text text = new Text(name);

        String verifier = name;
        if (verifier.matches("(\\w+\\s+)+(\\d+$)")) { // somente
//            verifier = verifier.replaceAll("[^0-9]", "");
            verifier = name.replaceAll("[0-9]", "");
            verifier = verifier.replaceAll(" ", "");

            int size = Integer.parseInt(name.replaceAll("[^0-9]", ""));

            if (
                    verifier.equals("YELLOW") && size <= 700
                    ||
                    verifier.equals("LIME") && size <= 600
                    ||
                    verifier.equals("AMBER") && size <= 600
            ) {
                text.getStyleClass().addAll( "bold");
            } else if (size > 200) {
                text.getStyleClass().addAll("text", "text-white", "bold");
            }
        } else if (verifier.matches("(\\w+\\s+)+(A+\\d+$)")) { // A100

            verifier = name.replaceAll("[0-9]", "");
            verifier = verifier.substring(0, verifier.lastIndexOf("A")-1);
            int size = Integer.parseInt(name.replaceAll("[^0-9]", ""));

            if (
                    verifier.equals("YELLOW")
                    ||
                    verifier.equals("AMBER")
                    ||
                    verifier.equals("LIME")
                    ||
                    verifier.equals("LIGHT GREEN")
                    ||
                    (verifier.equals("CYAN") ||  verifier.equals("TEAL") || verifier.equals("GREEN")) && size < 400
            ) {
                text.getStyleClass().addAll( "bold");
            } else if (size > 100) {
                text.getStyleClass().addAll("text-white", "bold");
            }
        } else {
            if (verifier.equals("LIME") || verifier.equals("AMBER") || verifier.equals("YELLOW")) {
                text.getStyleClass().addAll( "bold");
            } else {
                text.getStyleClass().addAll( "text-white", "bold");
            }
        }
//        getChildren().add(text);

        this.setOnMousePressed(event -> {

            String clipboardContent = "Color.web(\"" +
                                      strWeb +
                                      "\");\n" +
                                      "Color." +
                                      strRgb +
                                      ";\n" +
                                      "-" + name.toLowerCase().replaceAll(" ", "-")
                    ;

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(clipboardContent);
            clipboard.setContent(content);

            ((Root) getScene().getRoot())
                    .behavior()
                    .snack()
                    .graphic(new SVGIcon(Icon.NOTIFICATION_IMPORTANT_FILLED) )
//                    .message("You copied \n'" + clipboardContent + "' \nto clipboard.")
                    .message("Copied to clipboard.")
                    .show();
        });

        HoverAnimation.install(this);

        Tooltip tooltip = new Tooltip(_text);
        Tooltip.install(this, tooltip);

//        System.out.println("\t-" + color.name().toLowerCase().replaceAll("_", "-") + ": " + color.rgb() + ";\n\t /* web " + color.web() + " */");

//        System.out.println(".bg-" + mod + " {\n\t-fx-background-color: -" + mod +";\n\t-bar-color: -" + mod + ";\n}");
    }

    public String getColor() {
        return color;
    }
}
