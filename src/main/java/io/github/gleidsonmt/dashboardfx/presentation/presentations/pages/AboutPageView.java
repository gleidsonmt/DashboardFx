package io.github.gleidsonmt.dashboardfx.presentation.presentations.pages;

import io.github.gleidsonmt.blockcode.CodeType;
import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.presentation.Code;
import io.github.gleidsonmt.presentation.Presentation;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/04/2025
 */
public class AboutPageView extends CustomizablePresentation implements ActionableView {

    public AboutPageView() {
        super("About Page");
    }

    @Override
    public Presentation create() {
        return  new Tutorial()
                .h3("Login Page")
                .separator()

                .node(TutorialUtils.createCodeOption(
                        new About(),
                        new Code("About.java",
                                """
                                        public class About extends ScrollPane {
                                        
                                            private final VBox body = new VBox();
                                        
                                            public About() {
                                                getStyleClass().addAll("fit-width fit-height".split(" "));
                                                body.getStyleClass().addAll("padding-30 align-center".split(" "));
                                                setContent(body);
                                                body.getChildren().add(createMissionHeader());
                                                body.getChildren().add(createTeamBox());
                                            }
                                        
                                            private Node createTeamBox() {
                                                VBox abstractContainer = new VBox();
                                                abstractContainer.setSpacing(20);
                                                var title = new Text("Our Team");
                                                title.getStyleClass().addAll("h2", "text-bold", "font-instagram");
                                                abstractContainer.getChildren().setAll(title, createFlowText("Sit facilis neque ab nulla vel. Cum eos in laudantium. Temporibus eos totam in dolorum. Nemo vel facere repellendus ut eos dolores similique.", "h4"));
                                        
                                                TilePane tilePane = new TilePane();
                                        
                                                try {
                                        
                                                    parseYaml().forEach(member -> {
                                                        AvatarView avatarView = new AvatarView(Assets.getImage(member.getImg()), 100,100);
                                                        var text = new Text(member.getName());
                                                        text.getStyleClass().addAll("h5", "bold");
                                                        var legend = new Text(member.getLegend());
                                                        text.getStyleClass().addAll("h5");
                                                        VBox box = new VBox(avatarView, text, legend);
                                                        box.setAlignment(Pos.CENTER);
                                                        tilePane.getChildren().add(box);
                                                    });
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                        
                                                abstractContainer.getChildren().add(tilePane);
                                                return abstractContainer;
                                            }
                                        
                                            public List<Member> parseYaml() throws IOException {
                                                var loaderoptions = new LoaderOptions();
                                                TagInspector taginspector =
                                                        tag -> tag.getClassName().equals(Member.class.getName());
                                                loaderoptions.setTagInspector(taginspector);
                                        
                                                Yaml yaml = new Yaml(new Constructor(Member.class, loaderoptions));
                                        
                                                InputStream inputStream = App.class.getResourceAsStream("yaml/members.yml");
                                                List<Member> members = new ArrayList<>();
                                                var load = yaml.loadAll(inputStream);
                                                while (load.iterator().hasNext()) {
                                                    members.add((Member) load.iterator().next());
                                                }
                                                return members;
                                            }
                                        
                                            private Node createMissionHeader() {
                                                GridPane grid = new GridPane();
                                        
                                                Text title = new Text("Our Mission");
                                                title.getStyleClass().addAll("h2", "text-bold", "font-instagram");
                                                grid.setHgap(10);
                                                grid.setVgap(10);
                                                grid.add(title, 0, 0);
                                                GridPane.setHgrow(title, Priority.ALWAYS);
                                                grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 1);
                                                grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 2);
                                                grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 3);
                                        
                                                var box = createBox();
                                                grid.add(box, 1, 0, 1, GridPane.REMAINING);
                                                return grid;
                                            }
                                        
                                            private VBox createBox() {
                                                VBox box = new VBox();
                                                box.setSpacing(10);
                                                box.setAlignment(Pos.CENTER);
                                        
                                                Text transactions = new Text("44 million");
                                                transactions.getStyleClass().addAll("h3", "text-bold", "bold");
                                        
                                                Text transactionsLegend = new Text("Transactions every 24 hours");
                                                transactionsLegend.getStyleClass().addAll("h5");
                                        
                                                Text assets = new Text("$119 trillion");
                                                assets.getStyleClass().addAll("h3", "bold");
                                        
                                                Text assetsLegend = new Text("Assets under holding");
                                                assetsLegend.getStyleClass().addAll("h5");
                                        
                                                Text newUsers = new Text("46,000");
                                                newUsers.getStyleClass().addAll("h3", "bold");
                                        
                                                Text newUserLegend = new Text("New users annually");
                                                newUserLegend.getStyleClass().addAll("h5");
                                        
                                                box.getChildren().setAll(transactions, transactionsLegend, assets, assetsLegend, newUsers, newUserLegend);
                                                return box;
                                            }
                                        
                                            private TextFlow createFlowText(String text, String... classes) {
                                                Text content = new Text(text);
                                                TextFlow textFlow = new TextFlow(content);
                                                textFlow.getStyleClass().addAll(classes);
                                                return textFlow;
                                            }
                                        
                                        }
                                        """),
                        new Code("Member.java", """
                                public class Member {
                                    private String img;
                                    private String name;
                                    private String legend;
                                
                                    public String getImg() {
                                        return img;
                                    }
                                
                                    public void setImg(String img) {
                                        this.img = img;
                                    }
                                
                                    public String getName() {
                                        return name;
                                    }
                                
                                    public void setName(String name) {
                                        this.name = name;
                                    }
                                
                                    public String getLegend() {
                                        return legend;
                                    }
                                
                                    public void setLegend(String legend) {
                                        this.legend = legend;
                                    }
                                }
                                
                                """),
                        new Code("members.yml","""
                                img: "avatars/man@400.png"
                                name: "Ethan Wright"
                                legend: "UX Designer"
                                ---
                                img: "avatars/woman@400.png"
                                name: "Emma Thompson"
                                legend: "Cybersecurity Analyst"
                                ---
                                img: "avatars/man1@400.png"
                                name: "Oliver Johnson"
                                legend: "Software Engineer"
                                ---
                                img: "avatars/woman1@400.png"
                                name: "Sophia Martinez"
                                legend: "Network Administrator"
                                ---
                                img: "avatars/man2@400.png"
                                name: "William Davis"
                                legend: "Data Scientist"
                                ---
                                img: "avatars/woman2@400.png"
                                name: "Isabella Wong"
                                legend: "IT Project Manager"
                                ---
                                img: "avatars/man3@400.png"
                                name: "Benjamin Lee"
                                legend: "Cloud Solutions Architect"
                                ---
                                img: "avatars/woman3@400.png"
                                name: "Gabriella Rodriguez"
                                legend: "Founder / CEO"
                                ---
                                img: "avatars/man4@400.png"
                                name: "Chloe Wilson"
                                legend: "Web Developer"
                                ---
                                img: "avatars/woman4@400.png"
                                name: "Madison Brown"
                                legend: "Machine Learning Engineer"
                                ---
                                img: "avatars/man5@400.png"
                                name: "Alexander Garcia"
                                legend: "Database Administrator"
                                ---
                                img: "avatars/woman5@400.png"
                                name: "Abigail Robinson"
                                legend: "Co-Founder / CTO"
                                """, CodeType.YAML))
                )


                ;
    }

}