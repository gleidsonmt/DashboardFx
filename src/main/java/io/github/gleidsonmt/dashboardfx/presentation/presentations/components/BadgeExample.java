package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.badge.Badge;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;

import java.util.Random;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/04/2025
 */
public class BadgeExample extends CustomizablePresentation {

    public BadgeExample() {
        super("Badge");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Badge")
                .text("Label style examples.")
                .legend("io.github.gleidsonmt.glad.controls.badge.Badge")

                .demo(new Node[]{
                        createBadge(Icon.NOTIFICATION_IMPORTANT, "-fx-box-color: -info;", ""),
                        createBadge(Icon.CHAT, "-fx-box-color: -red-500;", ""),
                        createBadge(Icon.CALENDAR_MONTH, "-fx-box-color: -warning;", ""),
                })
                .code("""
                        // Constructors
                        SVGIcon node = new SVGIcon(Icon.TODAY);
                        Badge badge = new Badge(node);
                        """)
                .table(
                        "Property", "Value",
                        new Row("-fx-box-color", "[<paint>]"),
                        new Row("-fx-type", "[<round> | <rect> | <rounded>]")
                )
                .code("""
                        // Set the number of notifications
                        badge.setNumberOfNotifications(9); 
                        // Set the icon
                        badge.setIcon(new SVGIcon(Icon.APP));
                        // set Max notifications
                        badge.setMaxNotifications(10);
                        """);
    }


    private Badge createBadge(Icon icon, String style, String... _classes) {
        Badge badge = new Badge(icon);
        badge.setNumberOfNotifications(new Random().nextInt(9));
        badge.setStyle(style);
        badge.getStyleClass().addAll(_classes);
        return badge;
    }
}
