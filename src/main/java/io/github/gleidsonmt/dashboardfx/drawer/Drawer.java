package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.dashboardfx.dashboard.Dashboard;
import io.github.gleidsonmt.dashboardfx.presentation.Newsletter;
import io.github.gleidsonmt.dashboardfx.presentation.Scroll;
import io.github.gleidsonmt.dashboardfx.presentation.about.AboutPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.Behavior;
import io.github.gleidsonmt.dashboardfx.presentation.core.FlowPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.Introduction;
import io.github.gleidsonmt.dashboardfx.presentation.core.Wrapper;
import io.github.gleidsonmt.dashboardfx.presentation.drawer.DrawerExample;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.charts.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.components.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.controls.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.layout.TextFlowPres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.shapes.TextPres;
import io.github.gleidsonmt.dashboardfx.presentation.util.ColorsPres;
import io.github.gleidsonmt.dashboardfx.utils.pages.BuildingPage;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.ModuleView;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import io.github.gleidsonmt.presentation.TreeTitle;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/07/2024
 */
@ApiStatus.Experimental
public class Drawer extends VBox {

    private ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    private List<Module> modules;

    private final DrawerContainer drawerContainer;
    private ToggleGroup group = new ToggleGroup();

    private final TextBox search = new TextBox(Icon.SEARCH);
//    String text, Icon _icon, boolean animated, boolean mask

    private final VBox searchBox = new VBox();
    private final VBox defaultBox = new VBox();

    public Drawer() {
        this(List.of(
                new View("Dashboard", new Dashboard()),
                new View("Newsletter", new Newsletter()),
                new ModuleSeparator(new SVGIcon(Icon.HUB), "Project"),
                new ModuleView("Core",
                        new View("Introduction", new Introduction()),
                        new View("Wrapper", new Wrapper()),
                        new View("Flow", new FlowPres()),
                        new View("Behavior", new Behavior())),
                new ModuleSeparator(new SVGIcon(Icon.DESIGN_SERVICES), "Theme"),
                new ModuleView("Shapes",
                        new View("Text", new TextPres()),
                        new View("Circle", new BuildingPage())),
                new ModuleView("Controls",
                        new View("Region", new RegionPres()),
                        new View("Labeled", new LabeledPres()),
                        new View("Text Input", new TextInputPres()),
                        new View("Progress Bar", new ProgressBarPres()),
                        new View("Table View", new TableViewPres()),
                        new View("Tree View", new TreeViewPres()),
                        new View("List View", new ListViewPres())),
                new ModuleView("Containers",
                        new View("TitledPane", new BuildingPage()),
                        new View("TabPane", new TabPres()),
                        new View("Text Flow", new TextFlowPres())),
                new ModuleView("Charts",
                        new View("Bar Chart", new BarChartPres()),
                        new View("Area Chart", new AreaChartPres()),
                        new View("Stacked Area Chart", new StackedAreaChartPres()),
                        new View("Stacked Bar Chart", new StackedBarChartPres()),
                        new View("Pie Chart", new DonutChartPres()),
                        new View("Line Chart", new LineChartPres())),
                new ModuleSeparator(new SVGIcon(Icon.STACK), "Examples"),
                new ModuleView("Components",

                        new View("SVGIcon", new SVGIconPres()),
                        new View("Button", new ButtonExample()),
                        new View("Badge", new BadgeExample()),
                        new View("Avatar View", new AvatarPres()),
                        new View("Toggle Switch", new ToggleSwitchPres())

                ),
                new ModuleView("Styled",
                        new View("Drawer", new DrawerExample()),
                        new View("BreadCrumb", new BuildingPage()),
                        new View("Tree View", new TreeViewExample()),
                        new View("Label", new LabelExample()),
                        new View("Cards", new CardsPres())),
                new ModuleView("Pages",
//                        new View("Home Page", new HomePage()),
//                        new View("Login", new LoginPage()),
                        new View("Error Page 404")),
                new ModuleSeparator(new SVGIcon(Icon.HELP), "Theme"),
                new ModuleView("Utils",
                        new View("Pallet Color", new ColorsPres()),
                        new View("Alignment", new BuildingPage())),
                new View("About", new AboutPres())
        ));
    }

    public Drawer(@NotNull List<Module> _modules) {
//    public Drawer(List _modules) {

        this.modules = _modules;
        this.setId("drawer");
        this.drawerContainer = new DrawerContainer(defaultBox);
        this.getChildren().addAll(search, drawerContainer);
        search.setPromptText("Search");
        search.setAction(true);
        VBox.setMargin(search, new Insets(10, 0, 10, 0));

        setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(drawerContainer, Priority.ALWAYS);
        search.setMinHeight(40);

        this.modules.forEach(this::makeFirstLevel);
        this.setPrefWidth(250);
        this.setMaxWidth(250);
//        getStylesheets().add(Objects.requireNonNull(Start.class.getResource("css/drawer/variante_one.css")).toExternalForm());

        currentModule.addListener((_, _, newValue) -> group.getToggles().forEach(e -> {
            if (e.getUserData() == newValue) {
                group.selectToggle(e);
            }
        }));

        group.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                r((ToggleButton) newValue, true);
            }
            if (oldValue != null) {
                Node oldParent = getRoot(((ToggleButton) oldValue));
                Node newParent = getRoot(((ToggleButton) newValue));
                if (oldParent != newParent) {
                    r((ToggleButton) oldValue, false);
                    Platform.runLater(() -> {
                        if (newParent instanceof TitledPane parent) {
                            parent.setExpanded(true);
                        }
                        if (oldParent instanceof TitledPane p) {
                            p.setExpanded(false);
                        }
                    });
                }
            }
        });

        if (!group.getToggles().isEmpty()) {
            group.selectToggle(group.getToggles().get(0));
            currentModule.setValue((ModuleView) group.getToggles().get(0).getUserData());
        }

        search.textProperty().addListener((_, _, newVal) -> {
            if (!newVal.isEmpty()) {
                drawerContainer.setContainer(searchBox);
                searchBox.getChildren().clear();
//
                find(name -> name.contains(newVal))
                        .forEach(e -> {
                            Optional<BoxModule> opt = findModuleInSearchBox(e); // verify if box has e
                            if (opt.isEmpty() && e.getParent() != null) { // if box doesn't have a module and module has a parent
                                // add a new BoxModule with module
                                searchBox.getChildren().add(new BoxModule(e.getParent().getName(), createToggle(e)));
                            } else if (e.getParent() != null) { // if module already in search box and module has a parent
                                // find, and add to a BoxModule already in search box.
                                getBoxModule(e).get().getChildren().add(createToggle(e));
                            } else { // eh um module sem pai
                                searchBox.getChildren().add(createToggle(e));
                            }
                        });
            } else {
                drawerContainer.setContainer(defaultBox);
                VBox.setVgrow(drawerContainer, Priority.ALWAYS);
            }
        });

        this.setHeader(new DrawerHeader());
        this.setFooter(new DrawerFooter());
    }

    /**
     * If drawer is in mode of search, find a moduleImpl.
     *
     * @param module The moduleImpl in the SearchBox.
     * @return The BoxModule (VBox) that is equal to the moduleImpl name.
     */
    private @NotNull Optional<BoxModule> findModuleInSearchBox(Module module) {
        return searchBox
                .getChildren()
                .stream()
                .filter(el -> el instanceof BoxModule)
                .map(el -> (BoxModule) el)
                .filter(el -> module.getParent() != null)
                .filter(el -> el.getName().equals(module.getParent().getName()))
                .findAny();
    }

    private @NotNull Optional<BoxModule> getBoxModule(Module e) {
        return searchBox.getChildren()
                .stream()
                .filter(el -> el instanceof BoxModule)
                .map(el -> (BoxModule) el)
                .filter(el -> e.getParent().getName().equals(el.getName()))
                .findAny();
    }


    private @NotNull List<Module> find(Predicate<String> predicate) {
        List<Module> findedList = new ArrayList<>();
        _find(modules, findedList, predicate);
        return findedList;
    }

    private @Nullable Module _find(@NotNull List<Module> modules, List<Module> findedList, Predicate<String> predicate) {
        for (Module mod : modules) {
            if (predicate.test(mod.getName().toLowerCase())) {
                findedList.add(mod);
            } else {
                if (!(mod instanceof ModuleSeparator) && !mod.getModules().isEmpty()) {
                    Module module = _find(mod.getModules(), findedList, predicate);
                    if (module != null) {
                        findedList.add(module);
                    }
                }
            }
        }
        return null;
    }

    private @Nullable Module find(@NotNull List<Module> modules, String name) {
        for (Module mod : modules) {
            if (!mod.getName().equals(name)) {
                if (!(mod instanceof ModuleSeparator)) {
                    if (!mod.getModules().isEmpty()) {
                        Module moduleImpl = find(mod.getModules(), name);
                        if (moduleImpl != null) return moduleImpl;
                    }
                }
            } else {
                return mod;
            }
        }
        return null;
    }

    public void navigate(String name, String topic) {
        Module moduleImpl = find(this.modules, name);
        currentModule.set(moduleImpl);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Optional<Node> optional = getScene().getRoot().lookupAll("#tutorial-scroll").stream().findFirst();
                if (optional.isPresent() && optional.get() instanceof ScrollPane scroll) {
                    VBox box = (VBox) scroll.getContent();

                    Optional<TreeTitle> opt = box.getChildren().stream()
                            .filter(el -> el instanceof TreeTitle)
                            .map(el -> (TreeTitle) el)
                            .filter(el -> el.getText().equals(topic))
                            .findAny();
                    Scroll.scrollTo(scroll, opt.get());

                    BorderPane border = (BorderPane) getScene().getRoot().lookup("#tutorial-body");
                    Tutorial tutorial = (Tutorial) border.getUserData();
                    tutorial.select(topic);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 50);
    }

    public void navigate(String name) {
        Module moduleImpl = find(this.modules, name);
        currentModule.set(moduleImpl);
    }

    private void update(Node titledPane, boolean active) {
        if (active) {
            if (!titledPane.getStyleClass().contains("module-selected")) {
                titledPane.getStyleClass().addAll("module-selected");
            }
        } else {
            titledPane.getStyleClass().removeAll("module-selected");
        }
    }

    public void setHeader(Node node) {
        this.getChildren().add(0, node);
    }

    public void setFooter(Node node) {
        this.getChildren().add(node);
    }

    private Node getRoot(Node module) {
        if (module != null && module.getStyleClass().contains("module-first")) {
            return module;
        } else {
            if (module != null) return getRoot(module.getParent());
        }
        return null;
    }

    public void r(Node module, boolean active) {
        if (module instanceof TitledPane titledPane && titledPane.getStyleClass().contains("module-first")) {
            update(titledPane, active);
        } else {
            if (module.getParent() == null) return;
            r(module.getParent(), active);
        }
    }

    public ToggleButton createToggle(Module moduleImpl) {
        ToggleButton b = new ToggleButton(moduleImpl.getName());
//        b.setCache(true);
//        b.setCacheHint(CacheHint.QUALITY);
        b.setUserData(moduleImpl);
        b.getStyleClass().add("drawer-item");
        b.setAlignment(Pos.CENTER_LEFT);
        b.setPrefWidth(Double.MAX_VALUE);
        b.setOnMouseClicked(e -> currentModule.set(moduleImpl));

        b.addEventFilter(MouseEvent.MOUSE_RELEASED,_-> {
            Root root = (Root) getScene().getRoot();
            root.behavior().closeDrawer();
        });

        if (moduleImpl.getGraphic() != null) {
            b.setGraphic(moduleImpl.getGraphic());
        }

        group.getToggles().add(b);
        return b;
    }

    public void makeFirstLevel(Module module) {
        if (module instanceof ModuleSeparator) {
            Label label = new Label(((ModuleSeparator) module).getText());
            label.setGraphic(((ModuleSeparator) module).getIcon());
            label.getStyleClass().add("font-instagram-headline");
//            label.setStyle("-fx-font-family: \"Instagram Sans\"; -fx-font-size: 12px;");
//            label.setCache(true);
//            label.setCacheHint(CacheHint.QUALITY);
            VBox box = new VBox(label, new Separator());
//            box.setPadding(new Insets(0, 5,0,5));
            VBox.setMargin(box, new Insets(10, 5, 0, 5));
            box.setSpacing(10);

            defaultBox.getChildren().addAll(box);
        }

        if (module.getModules() == null) return;

        if (module.getModules().isEmpty()) {
//            this.getChildren().add(createToggle(moduleImpl));
            ((VBox) this.drawerContainer.getContent()).getChildren().add(createToggle(module));
        } else {
            TitledPane container = createPanel(module, true);
            container.getStyleClass().add("module-first");
//            this.getChildren().add(container);
            ((VBox) this.drawerContainer.getContent()).getChildren().add(container);
            if (!module.getModules().isEmpty()) {
//            VBox.setMargin(b, new Insets(0, 0, 0, 10));
                module.getModules().forEach(el -> {
//                    if (el instanceof View view) {
                    if (!el.getModules().isEmpty()) {
                        TitledPane pane = createPanel(el);
                        ((Pane) container.getContent()).getChildren().add(pane);
                        el.setContainer((Pane) container.getContent());
                        el.getModules().forEach(e -> {
                            e.setContainer((Pane) pane.getContent());
                            recurse(e);
                        });
                    } else {
                        ToggleButton button = createToggle(el);

                        ((Pane) container.getContent()).getChildren().add(button);
                        button.setOnMouseClicked(e -> currentModule.set(el));
                    }
                });
            }
        }
    }

    public void recurse(Module moduleImpl) {
        if (moduleImpl instanceof View) {
            ToggleButton b = createToggle(moduleImpl);

            if (moduleImpl.getContainer() != null) {
                moduleImpl.getContainer().getChildren().add(b);
            } else {
                this.getChildren().add(b);
            }
        } else {

            TitledPane container = createPanel(moduleImpl);
            moduleImpl.getContainer().getChildren().add(container);
            if (!moduleImpl.getModules().isEmpty()) {
                moduleImpl.getModules().forEach(el -> {
                    el.setContainer((Pane) container.getContent());
                    recurse(el);
                });
            }
        }
    }

    private @NotNull TitledPane createPanel(Module module) {
        return createPanel(module, false);
    }

    private @NotNull TitledPane createPanel(@NotNull Module module, boolean first) {
        VBox content = new VBox();
        content.getStyleClass().add("container");
        TitledPane titledPane = new TitledPane(module.getName(), content);

        if (module.getGraphic() != null) {
            titledPane.setGraphic(module.getGraphic());
        }
        titledPane.setExpanded(false);

        titledPane.setContentDisplay(ContentDisplay.RIGHT);
        titledPane.setAlignment(Pos.TOP_RIGHT);
        titledPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        titledPane.getStyleClass().add("drawer-menu");
        content.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        if (!module.isAnimated() && !first) {
            ToggleButton b = new ToggleButton(module.getName());
            b.setUserData(module);
            b.getStyleClass().add("drawer-item");
            b.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            b.setAlignment(Pos.CENTER_LEFT);
            b.setPrefWidth(230);
            group.getToggles().add(b);
            titledPane.getStyleClass().add("module-item");

            titledPane.setGraphic(b);
            titledPane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            titledPane.setCollapsible(false);
            titledPane.setAnimated(false);

            b.setOnMouseClicked(e ->
            {
                if (!(e.getTarget() instanceof VBox)) {
                    currentModule.set(module);
                }
            });
        }

        return titledPane;
    }

    public Module getCurrentModule() {
        return currentModule.get();
    }

    public ObjectProperty<Module> currentModuleProperty() {
        return currentModule;
    }

}
