/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.main;

import com.gn.GNAvatarView;
import com.gn.global.plugin.ViewManager;
import com.gn.global.factory.AlertCell;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Main implements Initializable {

    @FXML private GNAvatarView avatar;
    @FXML public  VBox sideBar;
    @FXML private HBox searchBox;
    @FXML private HBox boxStatus;
    @FXML private VBox info;
    @FXML private VBox views;
    @FXML private Circle cStatus;
    @FXML private Label status;
    @FXML public  ScrollPane body;
    @FXML public  Label title;
    @FXML private TextField search;
    @FXML private ScrollPane scroll;
    @FXML private TitledPane design;
    @FXML private TitledPane controls;
    @FXML private TitledPane charts;
    @FXML private Button home;
    @FXML private Button  about;
    @FXML private Button hamburger;
    @FXML private SVGPath searchIcon;
    @FXML private StackPane root;
    @FXML private Button clear;
    @FXML private JFXButton config;
    @FXML private VBox drawer;
    @FXML private JFXBadge messages;
    @FXML private JFXBadge notifications;
    @FXML private JFXBadge bg_info;
    @FXML private ToggleGroup group;

    @FXML private RadioButton available;

    private FilteredList<Button> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<Button> items         = FXCollections.observableArrayList();
    private ObservableList<Button> designItems   = FXCollections.observableArrayList();
    private ObservableList<Button> controlsItems = FXCollections.observableArrayList();
    private ObservableList<Button> chartsItems   = FXCollections.observableArrayList();

    private JFXDialog       dialog          = new JFXDialog();
    private JFXDialogLayout dialog_layout   = new JFXDialogLayout();

    private String path = "/com/gn/theme/css/";
    boolean scrolling   = false;

    private Parent popContent;
    public static Main ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        ctrl = this;
        loadContentPopup();

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                cStatus.setFill( ((RadioButton) newValue).getTextFill());
                status.setText(((RadioButton)newValue).getText());
            }
        });



        populateItems();
        filteredList = new FilteredList<>(items, s -> true);

        search.textProperty().addListener(obs -> {

            String filter = search.getText();
            if (filter == null || filter.length() == 0) {
                barInitial();
                clear.setMouseTransparent(true);
                searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
            } else {
                barFiltered(filter);
                clear.setMouseTransparent(false);
                searchIcon.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");

            }
        });
        body.setContent(ViewManager.getInstance().get("dashboard"));

        try {
            addSubPop();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        drawer.setPopStylesheet(getClass().getResource("/com/gn/theme/css/popover.css"));
    }


    @FXML
    private void altLayout() {


        int minimum = 70;
        int max = 250;

        if(drawer.getPrefWidth() == max){

            drawer.setPrefWidth(minimum);

            drawer.getChildren().remove(info);
            drawer.getChildren().remove(searchBox);

            for(Node node : views.getChildren()) {
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((Button) node).setAlignment(Pos.BASELINE_CENTER);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_CENTER);
                    ((TitledPane) node).setExpanded(false);
                    ((TitledPane) node).setCollapsible(false);
                } else {
                    break;
                }
            }

            avatar.setStrokeWidth(0);
            addEvents();

        } else {
            drawer.setPrefWidth(max);

            drawer.getChildren().addAll(info, searchBox);
            searchBox.toBack();
            info.toBack();
            avatar.toBack();
            avatar.setStrokeWidth(2);
            for(Node node : views.getChildren()){
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.LEFT);
                    ((Button) node).setAlignment(Pos.BASELINE_LEFT);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.RIGHT);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_RIGHT);
                    ((TitledPane) node).setCollapsible(true);
                } else {
                    break;
                }
            }
        }
    }

    private void addEvents(){
        VBox drawerContent;

        for (Node node : drawer.getChildren()) { // root
            if (node instanceof ScrollPane){

                drawerContent = (VBox) ((ScrollPane) node).getContent();

                for(Node child : drawerContent.getChildren()){
                    if(child instanceof Button){
                        child.setOnMouseEntered(e -> {
                            popup.setAutoHide(true);
                            if(popup.isShowing())
                                popup.hide();
                        });
                    }

                    else if(child instanceof TitledPane){
                        addEvent(child);
                    }
                }
            }

            else {
                // for others layouts
            }
        }
    }

    private void addSubPop() throws Exception {
        popup.setContentNode(FXMLLoader.load(getClass().getResource("/com/gn/module/main/Popover.fxml")));

//        popup.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

        popup.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
        popup.setArrowIndent(0);
        popup.setArrowSize(0);
        popup.setCornerRadius(0);
        popup.setAutoFix(true);
    }

    private void addEvent(Node node) {
        popup.setDetached(false);
        popup.setDetachable(false);
        popup.setCloseButtonEnabled(false);
        popup.setArrowSize(0);
        popup.setHeaderAlwaysVisible(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());

        VBox v = new VBox();
        v.setPrefWidth(200);

        TitledPane pane = (TitledPane) node;
        VBox vbox = (VBox) pane.getContent();

        for (Node btn : vbox.getChildren()) {
            EventHandler event = ((Button) btn).getOnMouseClicked();
            String text = ((Button) btn).getText();
            Button button = new Button(text);
            button.setPrefWidth(v.getPrefWidth());
            button.setOnMouseClicked(e -> {
                body.setContent(ViewManager.getInstance().get(button.getText().toLowerCase()));
                title.setText(button.getText());
                popup.hide();
            });
            button.setMinHeight(40);
            v.getChildren().add(button);
        }

//        Popover.ctrl.options.getChildren().clear();

        node.setOnMouseEntered((Event e) -> {
            if (drawer.getPrefWidth() == 70) {
                Popover.ctrl.options.getChildren().setAll(v);
                popup.show(pane, -1);
            }
        });
    }

    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scroll.setContent(views);
        ( (VBox) design.getContent()).getChildren().setAll(designItems);
        ( (VBox) controls.getContent()).getChildren().setAll(controlsItems);
        ( (VBox) charts.getContent()).getChildren().setAll(chartsItems);

        views.getChildren().removeAll(home, about);
        views.getChildren().add(home);
        views.getChildren().add(about);
        home.setContentDisplay(ContentDisplay.LEFT);
        about.setContentDisplay(ContentDisplay.LEFT);
        home.setAlignment(Pos.CENTER_LEFT);
        about.setAlignment(Pos.CENTER_LEFT);

        home.toBack();
        about.toFront();
        hamburger.setMouseTransparent(false);
    }

    private void barFiltered(String filter){
        views.getChildren().removeAll(home, about);
        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scroll.setContent(filter(filteredList));

        hamburger.setMouseTransparent(true);
    }

    private VBox filter(ObservableList<Button>  nodes){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("drawer-content");
        vBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (Button node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof Button) {
                items.add( (Button) node);
            }
        }

        for (Node node : ((VBox) controls.getContent()).getChildren()) {
            controlsItems.add((Button) node);
            items.add((Button) node);
        }

        for (Node node : ((VBox) design.getContent()).getChildren()) {
            designItems.add((Button) node);
            items.add((Button) node);
        }

        for (Node node : ((VBox) charts.getContent()).getChildren()) {
            chartsItems.add((Button) node);
            items.add((Button) node);
        }
    }


    private void loadContentPopup(){
        try {
            popContent = FXMLLoader.load(getClass().getResource("/com/gn/module/main/Config.fxml"));
            popConfig.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
            popConfig.setContentNode(popContent);
            popConfig.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            popConfig.setArrowIndent(0);
            popConfig.setArrowSize(0);
            popConfig.setCornerRadius(0);
            popConfig.setAutoFix(true);
            popConfig.setAnimated(false);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openConfig(){
        if(popConfig.isShowing()){
            popConfig.hide();
        } else {
            popConfig.show(config, 0);
            popConfig.getRoot().setFocusTraversable(true);
        }
    }

    @FXML
    private void clearText(){
        search.clear();
    }


    @FXML
    private void buttons() {
        body.setContent(ViewManager.getInstance().get("button"));
        title.setText("Button");
    }

    @FXML
    private void carousel() {
        title.setText("Carousel");
        body.setContent(ViewManager.getInstance().get("carousel"));
    }

    @FXML
    private void toggle() {
        title.setText("Toggle Button");
        body.setContent(ViewManager.getInstance().get("toggle"));
    }

    @FXML
    private void cards(){
        title.setText("Cards");
        body.setContent(ViewManager.getInstance().get("cards"));
    }

    @FXML
    private void banners(){
        title.setText("Banners");
        body.setContent(ViewManager.getInstance().get("banners"));
    }

    @FXML
    private void textField(){
        title.setText("TextField");
        body.setContent(ViewManager.getInstance().get("textfield"));
    }

    @FXML
    private void datePicker(){
        title.setText("DatePicker");
        body.setContent(ViewManager.getInstance().get("datepicker"));
    }

    @FXML
    private void checkBox(){
        title.setText("CheckBox");
        body.setContent(ViewManager.getInstance().get("checkbox"));
    }

    @FXML
    private void comboBox(){
        title.setText("ComboBox");
        body.setContent(ViewManager.getInstance().get("combobox"));
    }

    @FXML
    private void colorPicker(){
        title.setText("ComboBox");
        body.setContent(ViewManager.getInstance().get("colorpicker"));
    }


    @FXML
    private void choiceBox(){
        title.setText("ChoiceBox");
        body.setContent(ViewManager.getInstance().get("choicebox"));
    }

    @FXML
    private void splitMenuButton(){
        title.setText("SplitMenuButton");
        body.setContent(ViewManager.getInstance().get("splitmenubutton"));
    }

    @FXML
    private void menuButton(){
        title.setText("MenuButton");
        body.setContent(ViewManager.getInstance().get("menubutton"));
    }

    @FXML
    private void menuBar(){
        title.setText("MenuBar");
        body.setContent(ViewManager.getInstance().get("menubar"));
    }

    @FXML
    private void slider(){
        title.setText("Slider");
        body.setContent(ViewManager.getInstance().get("slider"));
    }

    @FXML
    private void mediaView(){
        title.setText("MediaView");
        body.setContent(ViewManager.getInstance().get("mediaview"));
    }

    @FXML
    private void label(){
        title.setText("Label");
        body.setContent(ViewManager.getInstance().get("label"));
    }

    @FXML
    private void imageView(){
        title.setText("ImageView");
        body.setContent(ViewManager.getInstance().get("imageview"));
    }

    @FXML
    private void hyperlink(){
        title.setText("HyperLink");
        body.setContent(ViewManager.getInstance().get("hyperlink"));
    }

    @FXML
    private void spinner(){
        title.setText("Spinner");
        body.setContent(ViewManager.getInstance().get("spinner"));
    }

    @FXML
    private void listView(){
        title.setText("ListView");
        body.setContent(ViewManager.getInstance().get("listview"));
    }

    @FXML
    private void radio(){
        title.setText("RadioButton");
        body.setContent(ViewManager.getInstance().get("radiobutton"));
    }

    @FXML
    private void progressBar(){
        title.setText("ProgressBar");
        body.setContent(ViewManager.getInstance().get("progressbar"));
    }

    @FXML
    private void passwordField(){
        title.setText("PasswordField");
        body.setContent(ViewManager.getInstance().get("passwordfield"));
    }

    @FXML
    private void progressIndicator(){
        title.setText("ProgressIndicator");
        body.setContent(ViewManager.getInstance().get("progressindicator"));
    }

    @FXML
    private void pagination(){
        title.setText("Pagination");
        body.setContent(ViewManager.getInstance().get("pagination"));
    }

    @FXML
    private void pieChart(){
        title.setText("PieChart");
        body.setContent(ViewManager.getInstance().get("piechart"));
    }

    @FXML
    private void stackedBarChart(){
        title.setText("StackedBarChart");
        body.setContent(ViewManager.getInstance().get("stackedbarchart"));
    }

    @FXML
    private void stackedAreaChart(){
        title.setText("StackedAreaChart");
        body.setContent(ViewManager.getInstance().get("stackedareachart"));
    }

    @FXML
    private void scatterChart(){
        title.setText("ScatterChart");
        body.setContent(ViewManager.getInstance().get("scatterchart"));
    }


    @FXML
    private void dashboard(){
        title.setText("Dashboard");
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void areaChart(){
        title.setText("AreaChart");
        body.setContent(ViewManager.getInstance().get("areachart"));
    }

    @FXML
    private void barChart(){
        title.setText("BarChart");
        body.setContent(ViewManager.getInstance().get("barchart"));
    }

    @FXML
    private void bubbleChart(){
        title.setText("BubbleChart");
        body.setContent(ViewManager.getInstance().get("bubblechart"));
    }

    @FXML
    private void lineChart(){
        title.setText("LineChart");
        body.setContent(ViewManager.getInstance().get("linechart"));
    }

    @FXML
    private void tableView(){
        title.setText("TableView");
        body.setContent(ViewManager.getInstance().get("tableview"));
    }

    @FXML
    private void scrollBar(){
        title.setText("ScrollBar");
        body.setContent(ViewManager.getInstance().get("scrollbar"));
    }

    @FXML
    private void treeTableView(){
        title.setText("TreeTableView");
        body.setContent(ViewManager.getInstance().get("treetableview"));
    }

    @FXML
    private void textArea(){
        title.setText("TextArea");
        body.setContent(ViewManager.getInstance().get("text-area"));
    }

    @FXML
    private void treeView(){
        title.setText("TreeView");
        body.setContent(ViewManager.getInstance().get("treeview"));
    }

    @FXML
    private void animateButtons(){
        title.setText("Animated Button");
        body.setContent(ViewManager.getInstance().get("animated-button"));
    }

    @FXML
    private void jfxTextField(){
        title.setText("JFXTextField");
        body.setContent(ViewManager.getInstance().get("jfx-text-field"));
    }

    @FXML
    private void alerts(){
        title.setText("Alerts");
        body.setContent(ViewManager.getInstance().get("alerts"));
    }

    private PopOver pop = new PopOver();
    @FXML
    private void openMessages(){
        if(!pop.isShowing()){
            GNAvatarView avatar1 = new GNAvatarView();
            GNAvatarView avatar2 = new GNAvatarView();
            GNAvatarView avatar3 = new GNAvatarView();
            GNAvatarView avatar4 = new GNAvatarView();

            avatar1.setImage(new Image(getClass().getResource("/com/gn/module/media/man.png").toExternalForm()));
            avatar2.setImage(new Image(getClass().getResource("/com/gn/module/media/woman.png").toExternalForm()));
            avatar3.setImage(new Image(getClass().getResource("/com/gn/module/media/man.png").toExternalForm()));

            ObservableList<AlertCell> list = FXCollections.observableArrayList(
                    new AlertCell(avatar1, "Will Junior","Lorem ipsum dolor color", "24 minutes ago"),
                    new AlertCell(avatar2, "Jad Gail","Lorem ipsum dolor color", "today"),
                    new AlertCell(avatar3, "Bart","Lorem ipsum dolor color", "3 seconds ago")
            );

            Separator top = new Separator();
            Separator bottom = new Separator();

            Label message = new Label("Messages");
            Label count = new Label("4 News");
            count.getStyleClass().add("text-success");
            GridPane title = new GridPane();
            title.setMinHeight(40D);

            title.setAlignment(Pos.CENTER);
            title.add(message, 0, 0);
            title.add(count, 1,0);
            GridPane.setHalignment(count, HPos.RIGHT);

            ListView<AlertCell> listView = new ListView<>();

            listView.getItems().addAll(list);
            listView.getStyleClass().add("border-0");

            Button btn = new Button("Read all messages");
            btn.getStyleClass().add("btn-flat");

            VBox root = new VBox(title, top, listView, bottom, btn);
            root.setAlignment(Pos.CENTER);
            root.setPrefSize(300, 300);
            title.setPrefWidth(root.getPrefWidth());
            count.setPrefWidth(root.getPrefWidth());
            message.setPrefWidth(root.getPrefWidth());
            count.setAlignment(Pos.CENTER_RIGHT);
            title.setPadding(new Insets(0, 25, 0, 25));
            btn.setPrefWidth(root.getPrefWidth());

            listView.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());


            pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
            pop.setContentNode(root);
            pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            pop.setArrowIndent(0);
            pop.setArrowSize(0);
            pop.setCloseButtonEnabled(false);
            pop.setHeaderAlwaysVisible(false);
            pop.setCornerRadius(0);
            pop.setAutoFix(true);
            pop.show(messages);

        } else {
            pop.hide();
        }
    }

    @FXML
    private void openNotification(){
      if(!pop.isShowing()){
            GNAvatarView avatar1 = new GNAvatarView();
            GNAvatarView avatar2 = new GNAvatarView();
            GNAvatarView avatar3 = new GNAvatarView();

            avatar1.setImage(new Image(getClass().getResource("/com/gn/module/media/warning-35.png").toExternalForm()));
            avatar2.setImage(new Image(getClass().getResource("/com/gn/module/media/error-35.png").toExternalForm()));
            avatar3.setImage(new Image(getClass().getResource("/com/gn/module/media/notification-35.png").toExternalForm()));

            ObservableList<AlertCell> list = FXCollections.observableArrayList(
                    new AlertCell(avatar1, "Warning","Lorem ipsum dolor color", "24 minutes ago"),
                    new AlertCell(avatar2, "Error","Lorem ipsum dolor color", "today"),
                    new AlertCell(avatar3, "Notification","Lorem ipsum dolor color", "3 seconds ago")
            );

            Separator top = new Separator();
            Separator bottom = new Separator();

            Label message = new Label("Messages");
            Label count = new Label("4 News");
            count.getStyleClass().add("text-success");
            GridPane title = new GridPane();
            title.setMinHeight(40D);

            title.setAlignment(Pos.CENTER);
            title.add(message, 0, 0);
            title.add(count, 1,0);
            GridPane.setHalignment(count, HPos.RIGHT);

            ListView<AlertCell> listView = new ListView<>();

            listView.getItems().addAll(list);
            listView.getStyleClass().add("border-0");

            Button btn = new Button("Read all messages");
            btn.getStyleClass().add("btn-flat");

            VBox root = new VBox(title, top, listView, bottom, btn);
            root.setAlignment(Pos.CENTER);
            root.setPrefSize(300, 300);
            title.setPrefWidth(root.getPrefWidth());
            count.setPrefWidth(root.getPrefWidth());
            message.setPrefWidth(root.getPrefWidth());
            count.setAlignment(Pos.CENTER_RIGHT);
            title.setPadding(new Insets(0, 25, 0, 25));
            btn.setPrefWidth(root.getPrefWidth());

            listView.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());


            pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
            pop.setContentNode(root);
            pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            pop.setArrowIndent(0);
            pop.setArrowSize(0);
            pop.setCloseButtonEnabled(false);
            pop.setHeaderAlwaysVisible(false);
            pop.setCornerRadius(0);
            pop.show(notifications);

        } else {
              pop.hide();
        }
    }
}
