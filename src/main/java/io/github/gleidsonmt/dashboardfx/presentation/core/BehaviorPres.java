package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import io.github.gleidsonmt.glad.dialog.alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class BehaviorPres extends StackPane {

    public BehaviorPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Behavior")
                        .text("""
                                BehaviorPres class has calling the actions on the root.""")
                        .legend("io.github.gleidsonmt.glad.base.BehaviorPres")
                        .h4("Dialogs", "Behavior")
                        .demo(
                                createDemo(e -> {
                                    Root root = (Root) getScene().getRoot();
                                    Button ok = new Button("Button");
                                    ok.setOnAction(_ -> root.behavior().dialog().close());
                                    VBox content = new VBox(new Text("Lorem ipsum dolor color"), ok);
                                    content.setAlignment(Pos.CENTER);
                                    content.setSpacing(10);
                                    root.behavior().dialog().open(content);

                                })
                        )
                        .code("""
                                Root root = (Root) getScene().getRoot();
                                Button ok = new Button("Button");
                                VBox content = new VBox(new Text("Lorem ipsum dolor color"), ok);
                                root.behavior().dialog().open(content);
                                """)
                        /*   */
                        .h4("Opening", "Behavior")
                        .demo(new Node[]{
                                        createDemo(_ -> {
                                                    Root root = (Root) this.getScene().getRoot();
//                                                    root.behavior().alert()
//                                                            .open("About", new Text("Press escape to close."), AlertType.ERROR);

                                                    root.behavior().alert()
                                                            .title("About")
                                                            .content(new Text("Press escape to close."))
                                                            .effect(WrapperEffect.BLUR)
                                                            .type(AlertType.ERROR)
                                                            .show();

                                                }
                                        ),
                                        createDemo(e -> {
                                                    Root root = (Root) this.getScene().getRoot();
                                                    root.behavior().alert().open("About", new Text("Press  to close."), AlertType.INFO);
                                                }
                                        ),
                                        createDemo(e -> {
                                                    Root root = (Root) this.getScene().getRoot();
                                                    root.behavior().alert().open("About", new Text("Press  to close."), AlertType.WARNING);
                                                }
                                        ),
                                        createDemo(e -> {
                                                    Root root = (Root) this.getScene().getRoot();
                                                    root.behavior().alert().open("About", new Text("Press  to close."), AlertType.SUCCESS);
                                                }
                                        )
                                }
                        )
                        .code("""
                                 root.behavior().alert().open("About", new Text("Lorem ipsum dolor color"), AlertType.INFO);
                                 root.behavior().alert().open("About", new Text("Lorem ipsum dolor color"), AlertType.WARNING);
                                 root.behavior().alert().open("About", new Text("Lorem ipsum dolor color"), AlertType.DANGER);
                                 root.behavior().alert().open("About", new Text("Lorem ipsum dolor color"), AlertType.SUCCESS);
                                """)
                        .h4("Combine with WrapperPres", "BehaviorPres")
                        .demo(createDemo(e -> {
                            Root root = (Root) this.getScene().getRoot();
                            root.wrapper().show(WrapperEffect.BLUR);
                            root.behavior().alert().open("About", new Text("Press  to close."), AlertType.SUCCESS);
                        }))
                        .code("""
                                Root root = (Root) this.getScene().getRoot();
                                root.wrapper().show(WrapperEffect.BLUR);
                                root.behavior().alert().open("About", new Text("Press  to close."), AlertType.SUCCESS);
                                """)
                        .h4("Add Buttons", "Behavior")
                        .text("""
                                The buttons are added in a button bar container.
                                """)
                        .node(TutorialUtils.createLink("See more in Button Bar", "Button Bar"))
                        .demo(createDemo(e -> {
                            Root root = (Root) this.getScene().getRoot();
                            Button cancel = new Button("Cancel");
                            Button apply = new Button("Apply");
                            cancel.setCancelButton(true);
                            cancel.setOnAction(_ -> {
                                root.wrapper().hide();
                                root.behavior().alert().hide();
                            });
                            root.wrapper().show(WrapperEffect.BLUR);
                            root.behavior().alert().open("About", new Text("Press  to close."), AlertType.SUCCESS, cancel, apply);
                        }))
                        .code("""
                                Button cancel = new Button("Cancel");
                                Button apply = new Button("Apply");
                                root.behavior().alert().open("About", new Text("Press  to close."), AlertType.SUCCESS, cancel, apply);
                                """)

                        .h4("Close the Alert", "Behavior")
                        .code("""
                                Button cancel = new Button("Cancel");
                                cancel.setOnAction(event -> {
                                    root.behavior().alert().close();
                                });
                                """)

                        // maybe you change form here, to get from drawer() method
                        .h4("Drawer", "Behavior")
                        .text("""
                                Try to open the drawer.
                                if the width is small the drawer appears from left,
                                but the width is larger nothing happens. 
                                """)
                        .demo(createDemo())
                        .code("root.behavior().openDrawer();")

                        .build()
                        .getRoot()
        );
    }

    private TextFlow createTextFlow(String... classes) {
        TextFlow textFlow = new TextFlow(new Text(
                "Lotem ipsum dolor color"
        ));
        textFlow.getStyleClass().addAll(classes);
        return textFlow;
    }

    private Node createDemo() {
        Button button = new Button("Click on me!");
        button.setOnAction(_ -> {
            Root root = (Root) button.getScene().getRoot();
            root.behavior().openDrawer();
        });
        return button;
    }

    private Node createDemo(EventHandler<ActionEvent> event) {
        Button button = new Button("Click on me!");
        button.setOnAction(event);
        return button;
    }
}
