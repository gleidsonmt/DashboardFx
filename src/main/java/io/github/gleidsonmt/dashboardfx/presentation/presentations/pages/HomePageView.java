package io.github.gleidsonmt.dashboardfx.presentation.presentations.pages;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.presentation.Presentation;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/03/2025
 */
public class HomePageView extends CustomizablePresentation {

    public HomePageView() {
        super("Home Page");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .h2("Home Page")
                .separator()
                .node(TutorialUtils.createCodeOption(new HomePage(), """
                        
                        class HomePage extends StackPane {
                        
                            public HomePage() {
                                this.getStyleClass().addAll("padding-20");
                                getChildren().setAll(createScroll());
                            }
                        
                            private ScrollPane createScroll() {
                                ScrollPane scroll = new ScrollPane(createBody());
                                scroll.setFitToWidth(true);
                                scroll.setFitToHeight(true);
                                return scroll;
                            }
                        
                            private @NotNull Node createBody() {
                                return new VBox(createNav(), createWrapper());
                            }
                        
                            private @NotNull Node createNav() {
                                GridPane grid = new GridPane();
                        
                                Node links = createLinks();
                                Node logo = createLogo();
                                Node loginLink = createLoginLink();
                        
                                grid.getChildren().addAll(logo, links, loginLink);
                        
                                GridPane.setConstraints(logo, 0, 0);
                                GridPane.setConstraints(links, 1, 0);
                                GridPane.setConstraints(loginLink, 2, 0);
                        
                                GridPane.setHalignment(logo, HPos.LEFT);
                                GridPane.setHalignment(loginLink, HPos.RIGHT);
                                GridPane.setHgrow(links, Priority.ALWAYS);
                                return grid;
                            }
                        
                            private @NotNull Node createWrapper() {
                                VBox box = new VBox(createAnnouncement(), createTitle(), createLegend(), createActions());
                                box.setSpacing(20);
                                box.setAlignment(Pos.CENTER);
                                VBox.setVgrow(box, Priority.ALWAYS);
                                return box;
                            }
                        
                            private @NotNull Node createLinks() {
                                Hyperlink features = new Hyperlink("Features");
                                Hyperlink company = new Hyperlink("Company");
                                Hyperlink marketplace = new Hyperlink("Marketplace");
                        
                                Hyperlink product = new Hyperlink("Product");
                        
                                TilePane box = new TilePane(product, features, company, marketplace);
                                box.getChildren().forEach(el -> el.getStyleClass().addAll("h5"));
                                box.setAlignment(Pos.CENTER);
                                return box;
                            }
                        
                            private @NotNull Node createLogo() {
                                AvatarView avatar = new AvatarView();
                                avatar.setImage(Assets.getImage("logo_128.png", 100));
                                return avatar;
                            }
                        
                            private @NotNull Node createAnnouncement() {
                                Text text = new Text("Announcing our next round of funding.");
                                text.getStyleClass().addAll("h4", "font-instagram");
                                HBox box = new HBox(new TextFlow(text), createLoginLink());
                                box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                                box.getStyleClass().addAll("padding-10", "border-2", "border-light-gray-2", "round", "align-center");
                                return box;
                            }
                        
                            private @NotNull Node createLoginLink() {
                        
                                Hyperlink login = new Hyperlink("Login");
                                login.getStyleClass().addAll("h5", "display-right", "min-w-100");
                                login.setGraphic(new SVGIcon(Icon.ARROW_RIGHT_ALT));
                                return login;
                            }
                        
                            private @NotNull Node createLegend() {
                                return createText("Anim aute id magna aliqua ad ad non deserunt sunt. Qui irure qui lorem cupidatat commodo. Elit sunt amet fugiat veniam occaecat fugiat aliqua."
                                        , "");
                            }
                        
                            private @NotNull Node createTitle() {
                                return createText("Data to enrich your online business", "h2", "bold");
                            }
                        
                            private @NotNull Node createText(String _text, String... classes) {
                                Text text = new Text(_text);
                                text.getStyleClass().addAll(classes);
                                TextFlow textFlow = new TextFlow(text);
                                textFlow.setTextAlignment(TextAlignment.CENTER);
                                textFlow.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                                return textFlow;
                            }
                        
                            private @NotNull Node createActions() {
                                HBox box = new HBox();
                                box.getStyleClass().addAll("align-center", "spacing-5");
                                Button button = new Button("Get Started");
                                button.getStyleClass().addAll("radius-5");
                                Hyperlink hyperlink = new Hyperlink("Learn More");
                                box.getChildren().setAll(button, hyperlink);
                                box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                                return box;
                            }
                        }
                        """))
                ;
    }

    @Override
    public void onEnter(Root root) {
        super.onEnter(root);
    }
}

