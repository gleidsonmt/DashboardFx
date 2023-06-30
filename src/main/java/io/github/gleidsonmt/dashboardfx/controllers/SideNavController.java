package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.services.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.view.SimpleView;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.controls.*;
import io.github.gleidsonmt.dashboardfx.views.tutorial.NewsLetter;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2023
 */
public class SideNavController extends ActionView {

    @FXML private StackPane root;
    @FXML private ToggleGroup group;

    @FXML
    private void goDash() throws NavigationException {
        context.routes().nav("dash");
    }

    @FXML
    private void goNewsletter() {
        go("view_news", new NewsLetter(context));
    }

    @FXML
    private void goUnderstanding() {
        go("tutorial_understanding", new TutorialUnderstanding(context));
    }

    @FXML
    private void goButton() {
        go("button", new ButtonPresCreator(context));
    }

    @FXML
    private void goCheckBox() {
        go("view_check", new CheckBoxPresCreator(context));
    }

    @FXML
    private void goComboBox() {
        go("view_check", new ComboBoxPresCreator(context));
    }

    @FXML
    private void goHyperlink() {
        go("view_hyperlink", new HyperlinkPresCreator(context));
    }

    @FXML
    private void goLabels() {
        go("view_label", new LabelPresCreator(context));
    }

    @FXML
    private void goSlider() {
        go("slider", new SliderPresCreator(context));
    }

    @FXML
    private void goMenuButton() {
        go("menu_button", new MenuButtonPresCreator(context));
    }

    @FXML
    private void goListView() {
        go("view_list", new ListViewCreator(context));
    }

    @FXML
    private void goMediaView() {
        go("mediaView", new MediaViewPresCreator(context));
    }

    @FXML
    private void goPassword() {
        go("view_pass", new PasswordPresCreator(context));
    }

    @FXML
    private void goTableView() {
        go("view_table", new TableViewPresCreator(context));
    }

    @FXML
    private void goTextField() {
        go("view_label", new TextFieldPresCreator(context));
    }

    @FXML
    private void goTreeView() {
        go("view_tree", new TreeViewPresCreator(context));
    }

    @FXML
    private void goSwitch() {
        go("view_tree", new ToggleButtonPresCreator(context));
    }

    @FXML
    private void goLogin() {
        context.routes().setView("login");
    }

    @FXML
    private void goCarousel() {
        go("carousel", new CarouselViewPresCreator(context));
    }

    @FXML
    private void goRating() {
        go("rating", new RatingPresCreator(context));
    }

    @FXML
    private void goMenuBar() {
        go("menu_bar", new MenuBarPresCreator(context));
    }

    @FXML
    private void goRadioButton() {
        go("radio_button", new RadioButtonPresCreator(context));
    }

    @FXML
    private void goChoiceBox() {
        go("choice_box", new ChoiceBoxPresCreator(context));
    }

    @FXML
    private void goSpinner() {
        go("spinner", new SpinnerPresCreator(context));
    }
    @FXML
    private void goColorPicker() {
        go("color_picker", new ColorPickerPresCreator(context));
    }

    @FXML
    private void goDatePicker() {
        go("date_picker", new DatePickerPresCreator(context));
    }

    @FXML
    private void goPagination() {
        go("pagination", new DatePickerPresCreator(context));
    }

    @FXML
    private void goTreeTableView() {
        go("tree_table_view", new TreeTableViewPresCreator(context));
    }

    @FXML
    private void goHtmlEditor() {
        go("html_editor", new HtmlEditorViewPresCreator(context));
    }
    @FXML
    private void goAbout() throws NavigationException{
        context.routes().nav("about");
    }

    public void removeFocus() {
        group.getSelectedToggle().setSelected(false);
    }

    private void go(String name, TutorialCreator tutorialCreator) {
        context.routes().putAndGo(new SimpleView(name, tutorialCreator));
    }

    private void createProblemView(){
        Circle rectangle = new Circle();
        rectangle.setFill(new ImagePattern(
                new Image(context.getResource("style/img/404.png").toExternalForm())
        ));
        rectangle.setRadius(200);
        context.routes().putAndGo(new SimpleView("error_404", new StackPane(rectangle)
        ));
    }

    @Override
    public void onInit(Context context) {
//        this.context = context;
        super.onInit(context);
        new DrawerBehavior(root, group, context);
    }
}