package io.github.gleidsonmt.dashboardfx.presentation.presentations.styled;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class ToggleGroupPres extends CustomizablePresentation {

    private ToggleGroup groupCustom = new ToggleGroup();

    public ToggleGroupPres() {
        super("Toggle Group");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .overview()
                .h3("ToggleButton Pres")
                .separator()
                .text("Examples of building multiple options with toggle buttons and groups.")
                .text("First define a Container for your layout that can be any layout (StackPane, AnchorPane, etc).")
                .text("Give that a class container-option")
                .text("This will enable to switch outlined buttons to default.")
                .text("Before copying that examples start add this piece of code to your main css file.")

                .code("""
                        .container-option .toggle-button {
                            -fx-effect: none;
                            -fx-border-insets: 0px;
                            -fx-background-insets: 0px;
                        }
                        
                        .container-option .toggle-button:selected {
                            -fx-background-color: -fx-accent;
                            -fx-border-width: 0px;
                        }
                        
                        .container-option .toggle-button:selected .text {
                            -text-color: white;
                        }

                        """, "css")
                .text("Create the container")
                .code("""
                        ...
                        HBox container = new HBox();
                        container.getStyleClass().add("container-option");
                        ...
                        """)
                .h3("Switch", "ToggleButton Pres")
                .text("Create the buttons")
                .code("""
                    ...
                    ToggleButton left = new ToggleButton("On");
                    left.getStyleClass().addAll( "w-50","btn-outlined", "pill-left");
                    left.setStyle("-fx-border-width: 2px 0px 2px 2px; ");
                    
                    ToggleButton right = new ToggleButton("Off");
                    right.getStyleClass().addAll( "w-50", "btn-outlined", "pill-right");
                    right.setStyle("-fx-border-width: 2px 2px 2px 0px;");
                    ...
                    container.getChildren().setAll(left, right);
                    """)
                .demo(createExample())
                .h3("Align", "ToggleButton Pres")
                .demo(createExample2())
                .h3("Blocks", "ToggleButton Pres")
                .demo(createCustom())
                .h4("Multiple Blocks", "Blocks")
                .demo(createStorage())

                ;
    }

    private Node createExample() {
        HBox container = new HBox();
        container.getStyleClass().add("container-option");
        ToggleGroup group = new ToggleGroup();
        ToggleButton left = new ToggleButton("On");
        left.getStyleClass().addAll( "w-50","btn-outlined", "pill-left");
        left.setStyle("-fx-border-width: 2px 0px 2px 2px; ");
        ToggleButton right = new ToggleButton("Off");
        right.getStyleClass().addAll( "w-50", "btn-outlined", "pill-right");
        right.setStyle("-fx-border-width: 2px 2px 2px 0px;");

        group.getToggles().setAll(left,  right);
        container.getChildren().setAll(left, right);
        return container;
    }

    private Node createExample2() {
        HBox container = new HBox();
        ToggleGroup group = new ToggleGroup();
        ToggleButton left = new ToggleButton("left");
        left.getStyleClass().addAll( "w-50","btn-outlined", "pill-left");
        left.setStyle("-fx-border-width: 2px 0px 2px 2px; ");
        ToggleButton middle = new ToggleButton("middle");
        middle.getStyleClass().addAll("rect", "btn-outlined");
        middle.setStyle("-fx-border-width: 2px 0px 2px 0px;");
        ToggleButton right = new ToggleButton("right");
        right.getStyleClass().addAll( "w-50", "btn-outlined", "pill-right");
        right.setStyle("-fx-border-width: 2px 2px 2px 0px;");

        group.getToggles().setAll(left, middle, right);
        container.getChildren().setAll(left, middle, right);
        new SVGIcon(Icon.LOCATION_ON);
        container.getChildren().forEach(e -> {
            e.getStyleClass().add("out-toggle-item");
        });
        return container;
    }

    private Node createStorage() {
        HBox body = new HBox();
        VBox box = new VBox();

        Text title = new Text("Storage");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("Transfer your balance to your bank account");
        legend.getStyleClass().addAll("h5");
        ToggleGroup group = new ToggleGroup();

        ToggleButton four = new ToggleButton("4 GB");
        ToggleButton eight = new ToggleButton("8 GB");
        ToggleButton sixteen = new ToggleButton("16 GB");
        ToggleButton thirtyTwo = new ToggleButton("32 GB");
        ToggleButton sixtyFour = new ToggleButton("64 GB");

        body.setSpacing(20);

        group.getToggles().setAll(four, eight, sixteen, thirtyTwo,  sixtyFour);
        body.getChildren().setAll(four, eight, sixteen, thirtyTwo, sixtyFour);
        box.getChildren().addAll(title, body);

        return box;
    }

    private Node createCustom() {
        VBox box = new VBox();
        box.setSpacing(20);

        ToggleButton toggleOne = createToggle(
                "Hobby",
                "8GB / 4 CPUs * 160 GB SSD disk",
                "$40",
                "/mo");

        ToggleButton toggleTwo = createToggle(
                "Startup",
                "12GB / 6 CPUs · 256 GB SSD disk",
                "$80",
                "/mo");


        ToggleButton toggleThree = createToggle(
                "Business",
                "16GB / 8 CPUs · 512 GB SSD disk",
                "$160",
                "/mo");

        box.getChildren().setAll(toggleOne, toggleTwo, toggleThree);
        return box;
    }

    private ToggleButton createToggle(String text, String legend, String price, String legendt) {
        ToggleButton toggle = new ToggleButton(text);
        toggle.getStyleClass().add("inner-toggle");
        toggle.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        GridPane grid = new GridPane();

        groupCustom.getToggles().add(toggle);

        Text one = new Text(text);
        Text two = new Text(legend);
        Text three = new Text(price);
        Text four = new Text(legendt);

        toggle.setGraphic(grid);

        grid.getChildren().addAll(one, two, three, four);

        GridPane.setConstraints(one, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(two, 0,1,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(three, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(four, 1,1,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        return toggle;
    }
}
