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

import com.gn.ViewManager;
import com.gn.control.*;
import com.gn.control.factory.AlertCell;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    @FXML private ImageView avatar;
    @FXML public  VBox sideBar;
    @FXML private HBox searchBox;
    @FXML private HBox boxStatus;
    @FXML private VBox info;
    @FXML private DrawerContent views;
    @FXML private Circle cStatus;
    @FXML private HBox status;
    @FXML public  ScrollPane body;
    @FXML public  Label title;
    @FXML private TextField search;
    @FXML private ScrollPane scroll;
    @FXML private DrawerList design;
    @FXML private DrawerList controls;
    @FXML private DrawerList charts;
    @FXML private DrawerItem home;
    @FXML private DrawerItem  about;
    @FXML private Button hamburger;
    @FXML private SVGPath searchIcon;
    @FXML private StackPane root;
    @FXML private Button clear;
    @FXML private JFXButton config;
    @FXML private GNDrawer drawer;
    @FXML private DrawerItem colors;
    @FXML private JFXBadge messages;

    private FilteredList<DrawerItem> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<DrawerItem> items         = FXCollections.observableArrayList();
    private ObservableList<DrawerItem> designItems   = FXCollections.observableArrayList();
    private ObservableList<DrawerItem> controlsItems = FXCollections.observableArrayList();
    private ObservableList<DrawerItem> chartsItems   = FXCollections.observableArrayList();

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

        if(drawer.getType().equals(GNDrawer.Type.AVATAR)){
            scrolling = false;
            drawer.setType(GNDrawer.Type.MINIMUN);
            drawer.getChildren().remove(searchBox);
            addEvents();
        } else {
            scrolling = true;
            drawer.getChildren().add(searchBox);
            scroll.toFront();
            drawer.setType(GNDrawer.Type.AVATAR);
        }
    }

    private void addEvents(){
        DrawerContent drawerContent;

        for (Node node : drawer.getChildren()) { // root

            if (node instanceof ScrollPane){

                drawerContent = (DrawerContent) ((ScrollPane) node).getContent();

                for(Node child : drawerContent.getChildren()){

                    if(child instanceof DrawerItem){
                        child.setOnMouseEntered(e -> {
                            popup.setAutoHide(true);
                            if(popup.isShowing())
                                popup.hide();
                        });
                    }

                    else if(child instanceof DrawerList){
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

        popup.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
        popup.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
        popup.setArrowIndent(0);
        popup.setArrowSize(0);
        popup.setCornerRadius(0);
        popup.setAutoFix(true);
        popup.setAnimated(false);
    }

    private void addEvent(Node node) {
        popup.setAnimated(false);
        popup.setDetached(false);
        popup.setDetachable(false);
        popup.setCloseButtonEnabled(false);
        popup.setArrowSize(0);
        popup.setHeaderAlwaysVisible(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());

        VBox v = new VBox();
        v.setPrefWidth(200);

        TitledPane pane = (DrawerList) node;
        VBox vbox = (VBox) pane.getContent();

        for (Node btn : vbox.getChildren()) {

            EventHandler event = ((DrawerItem) btn).getOnMouseClicked();
            String text = ((DrawerItem) btn).getText();
            JFXButton button = new JFXButton(text);
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
            if (drawer.getType() == GNDrawer.Type.MINIMUN) {
                Popover.ctrl.options.getChildren().setAll(v);
                popup.show(pane, -2);
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

    private VBox filter(ObservableList<DrawerItem>  nodes){
        VBox vBox = new VBox();
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (DrawerItem node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_RIGHT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof JFXButton) {
                items.add((DrawerItem) node);
            }
        }

        for (Node node : ((VBox) controls.getContent()).getChildren()) {
            controlsItems.add((DrawerItem) node);
            items.add((DrawerItem) node);
        }

        for (Node node : ((VBox) design.getContent()).getChildren()) {
            designItems.add((DrawerItem) node);
            items.add((DrawerItem) node);
        }

        for (Node node : ((VBox) charts.getContent()).getChildren()) {
            chartsItems.add((DrawerItem) node);
            items.add((DrawerItem) node);
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
        title.setText("TableView");
        body.setContent(ViewManager.getInstance().get("scrollbar"));
    }

    @FXML
    private void treeTableView(){
        title.setText("TreeTableView");
        body.setContent(ViewManager.getInstance().get("treetableview"));
    }

    @FXML
    private void treeView(){
        title.setText("TreeView");
        body.setContent(ViewManager.getInstance().get("treeview"));
    }


    @FXML
    private void openNotification(){

        GNAvatar avatar = new GNAvatar();
        GNAvatar avatar1 = new GNAvatar();
        GNAvatar avatar2 = new GNAvatar();
        GNAvatar avatar3 = new GNAvatar();
        GNAvatar avatar4 = new GNAvatar();

        avatar.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));
        avatar1.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));
        avatar2.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));
        avatar3.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));
        avatar4.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));

        ObservableList<AlertCell> list = FXCollections.observableArrayList(
                new AlertCell(avatar1, "New Comment", "24 minutes ago"),
                new AlertCell(avatar2, "New Comment", "41 minutes ago"),
                new AlertCell(avatar3, "New Comment", "43 minutes ago"),
                new AlertCell(avatar4, "New Comment", "23 minutes ago")

        );

        ListView<AlertCell> listView = new ListView<>();

        listView.getItems().addAll(list);

        VBox root = new VBox(listView);
        root.setPrefSize(300, 300);


        PopOver pop = new PopOver();
        pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
        pop.setContentNode(root);
        pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        pop.setArrowIndent(0);
        pop.setArrowSize(0);
        pop.setCornerRadius(0);
        pop.setAutoFix(true);
        pop.show(messages);

    }
}
