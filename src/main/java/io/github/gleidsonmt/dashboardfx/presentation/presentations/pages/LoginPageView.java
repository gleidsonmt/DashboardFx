package io.github.gleidsonmt.dashboardfx.presentation.presentations.pages;

import io.github.gleidsonmt.dashboardfx.ResizablePane;
import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Code;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/04/2025
 */
public class LoginPageView extends CustomizablePresentation implements ActionableView {

    private final VBox body = new VBox();

    public LoginPageView() {
        super("Login Page");
        configLayout();
    }

    @Override
    public Presentation create() {

        return new Tutorial()
//                .node(TutorialUtils.createAction("Preview", e -> {
//                    TutorialUtils.showPage(new LoginPage());
//                }))
                .h2("Login Page")
                .node(TutorialUtils.createCodeOption(
                        new LoginPage(),
                """
                        public class LoginPage extends VBox {
                    
                            public LoginPage() {
                                getChildren().addAll(
                                        createTitle(),
                                        createText(),
                                        createField(false),
                                        createField(true),
                                        createOptions(),
                                        createBar());
                            }


                            private Text createTitle() {
                                Text text = new Text("Welcome");
                                text.getStyleClass().addAll("h1", "text-accent", "font-instagram", "bold");
                                VBox.setMargin(text, new Insets(0, 0, 10, 0));
                                return text;
                            }

                            private Text createText() {
                                Text text = new Text("Welcome back, Please login in your account.");
                                text.getStyleClass().addAll("h4", "font-instagram");
                                VBox.setMargin(text, new Insets(0, 0, 10, 0));
                                return text;
                            }

                            private TextBoxBase createField(boolean pass) {
                                TextBoxBase textBox = pass ? createPass() : createTextField();
                                textBox.setPrefWidth(600);
                                textBox.getStyleClass().addAll("min-h-50", "padding-20", "bg-insets-10");
                                VBox.setMargin(textBox, new Insets(10, 0, 10, 0));
                                textBox.setStyle("-fx-padding: 10px;");
                                return textBox;
                            }

                            private TextBox createTextField() {
                                TextBox pass = new TextBox(Icon.ACCOUNT_CIRCLE);
                                pass.setPromptText("Name");
                                pass.setAction(true);
                                return pass;
                            }

                            private PasswordBox createPass() {
                                PasswordBox pass = new PasswordBox(Icon.LOCK);
                                pass.setPromptText("Password");
                                pass.setAction(true);
                                return pass;
                            }

                            private GridPane createOptions() {
                                GridPane grid = new GridPane();
                                grid.setPrefWidth(500);
                                CheckBox checkBox = new CheckBox("Remember");
                                Hyperlink hyperlink = new Hyperlink("Forgot your password?");

                                ColumnConstraints columnOne = new ColumnConstraints();
                                ColumnConstraints columnTwo = new ColumnConstraints();
                                columnOne.setPercentWidth(50);
                                columnTwo.setPercentWidth(50);

                                VBox.setMargin(grid, new Insets(20, 0, 20, 0));

                                grid.getColumnConstraints().addAll(columnOne, columnTwo);

                                GridPane.setHalignment(hyperlink, HPos.RIGHT);

                                grid.add(checkBox, 0, 0);
                                grid.add(hyperlink, 1, 0);
                                return grid;
                            }

                            private ButtonBar createBar() {
                                ButtonBar buttonBar = new ButtonBar();
                                Button login = new Button("Login");
                                login.setPrefWidth(300);
                                Button register = new Button("Register");
                                register.setCancelButton(true);
                                ButtonBar.setButtonData(login, ButtonBar.ButtonData.OK_DONE);
                                buttonBar.getButtons().addAll(login, register);
                                return buttonBar;
                            }

                        }
                        """)
                )

//                .demo(new ResizablePane(new LoginPage()))
//                .code()
                ;
    }

    private void configLayout() {
        body.setAlignment(Pos.CENTER);
        body.setPrefWidth(700);
        body.setFillWidth(false);
        body.setPadding(new Insets(20));
    }
}