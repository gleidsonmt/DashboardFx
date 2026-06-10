package io.github.gleidsonmt.dashboardfx.presentation.presentations.styled;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
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

    private final ToggleGroup groupCustom = new ToggleGroup();

    public ToggleGroupPres() {
        super("Toggle Group");
    }

    @Override
    public Presentation create() {

        return new Tutorial()
                .overview()
                .h3("Toggle Group")
                .separator()
                .text("Examples of building multiple options with toggle buttons and groups.")
                .h3("Pill")
                .text("If you want to learn more.")
                .node(TutorialUtils.createCardLink("See this article", "https://gleidsonmt.github.io/#/blog/post/1"))
                .demo(createExample())

                .code("""
                            HBox abstractContainer = new HBox();
                            abstractContainer.getStyleClass().add("abstractContainer-option");
                            ToggleGroup group = new ToggleGroup();
                            ToggleButton left = new ToggleButton("On");
                            left.getStyleClass().addAll( "w-50","btn-outlined", "pill-left");
                            left.setStyle("-fx-border-width: 2px 0px 2px 2px; ");
                            ToggleButton right = new ToggleButton("Off");
                            right.getStyleClass().addAll( "w-50", "btn-outlined", "pill-right");
                            right.setStyle("-fx-border-width: 2px 2px 2px 0px;");
                        
                            group.getToggles().setAll(left,  right);
                            abstractContainer.getChildren().setAll(left, right);
                        """)
                .separator()
                .text("Add this classes to your main css file.", "padding-10")
                .code("""
                        .abstractContainer-option .toggle-button {
                            -fx-effect: none;
                            -fx-border-insets: 0px;
                            -fx-background-insets: 0px;
                        }
                        
                        .abstractContainer-option .toggle-button:selected {
                            -fx-background-color: -fx-accent;
                            -fx-border-width: 0px;
                        }
                        
                        .abstractContainer-option .toggle-button:selected .text {
                            -text-color: white;
                        }
                        
                        """, "css")
                .h3("Align")
                .demo(createExample2())
                .code("""
                    private Node createExample2() {
                        HBox abstractContainer = new HBox();
                        abstractContainer.setSpacing(5);
                        abstractContainer.getStyleClass().addAll("abstractContainer-align");
                        ToggleButton left = crateAlignToggle(Icon.FORMAT_ALIGN_LEFT);
                        ToggleButton center = crateAlignToggle(Icon.FORMAT_ALIGN_CENTER);
                        ToggleButton right = crateAlignToggle(Icon.FORMAT_ALIGN_RIGHT);
                        ToggleButton justify = crateAlignToggle(Icon.FORMAT_ALIGN_JUSTIFY);
                        abstractContainer.getChildren().addAll(left, center, right, justify);
                        new ToggleGroup().getToggles().addAll(left, center, right, justify);
                        return abstractContainer;
                    }
                
                    private ToggleButton crateAlignToggle(Icon _icon) {
                        SVGIcon icon = new SVGIcon(_icon);
                        ToggleButton toggle = new ToggleButton();
                        toggle.setGraphic(icon);
                        toggle.getStyleClass().addAll("w-20 btn-outlined graphic-only".split(" "));
                        return toggle;
                    }
                    """)
                .separator()
                .text("Add this classes to your main css file.", "padding-10")

                .code("""
                        .abstractContainer-align .toggle-button {
                            -fx-border-color: derive(-dark-gray, 30%);
                            -fx-background-radius: 3px;
                            -fx-border-radius: 3px;
                            -fx-border-width: 2px;
                            -fx-effect: none;
                            -fx-background-color: white;
                            -fx-border-insets: -1px;
                        }
                        .abstractContainer-align .toggle-button .icon {
                            -fx-fill: derive(-dark-gray, 30%);
                        }
                        
                        .abstractContainer-align .toggle-button:hover {
                            -fx-border-color: -dark-gray;
                        }
                        
                        .abstractContainer-align .toggle-button:hover .icon {
                            -fx-fill: -dark-gray;
                        }
                        .abstractContainer-align .toggle-button:selected {
                            -fx-background-color: -fx-accent;
                            -fx-border-color: -fx-accent;
                        }
                        
                        .abstractContainer-align .toggle-button:selected .icon {
                            -fx-fill: white;
                        }
                        
                        """, "css")
                .h3("Blocks")
                .demo(createCustom())
                .code("""
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
                        """)
                .h4("Storage Blocks")
                .demo(createStorage())
                .code("""
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
                        
                        """)

                ;
    }

    public Node createExample() {
        HBox abstractContainer = new HBox();
        abstractContainer.getStyleClass().add("abstractContainer-option");
        ToggleGroup group = new ToggleGroup();
        ToggleButton left = new ToggleButton("On");
        left.getStyleClass().addAll("w-50", "btn-outlined", "pill-left");
        left.setStyle("-fx-border-width: 2px 0px 2px 2px; ");
        ToggleButton right = new ToggleButton("Off");
        right.getStyleClass().addAll("w-50", "btn-outlined", "pill-right");
        right.setStyle("-fx-border-width: 2px 2px 2px 0px;");

        group.getToggles().setAll(left, right);
        abstractContainer.getChildren().setAll(left, right);
        return abstractContainer;
    }

    private Node createExample2() {
        HBox abstractContainer = new HBox();
        abstractContainer.setSpacing(5);
        abstractContainer.getStyleClass().addAll("abstractContainer-align");
        ToggleButton left = crateAlignToggle(Icon.FORMAT_ALIGN_LEFT);
        ToggleButton center = crateAlignToggle(Icon.FORMAT_ALIGN_CENTER);
        ToggleButton right = crateAlignToggle(Icon.FORMAT_ALIGN_RIGHT);
        ToggleButton justify = crateAlignToggle(Icon.FORMAT_ALIGN_JUSTIFY);
        abstractContainer.getChildren().addAll(left, center, right, justify);
        new ToggleGroup().getToggles().addAll(left, center, right, justify);
        // left, center, righg e jusity
        return abstractContainer;
    }

    private ToggleButton crateAlignToggle(Icon _icon) {
        SVGIcon icon = new SVGIcon(_icon);
        ToggleButton toggle = new ToggleButton();
        toggle.setGraphic(icon);
        toggle.getStyleClass().addAll("w-20 btn-outlined graphic-only".split(" "));
        return toggle;
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

        group.getToggles().setAll(four, eight, sixteen, thirtyTwo, sixtyFour);
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

        GridPane.setConstraints(one, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(two, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(three, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(four, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        return toggle;
    }
}
