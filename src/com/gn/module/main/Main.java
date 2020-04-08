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

import com.gn.App;
import com.gn.GNAvatarView;
import com.gn.GNCarousel;
import com.gn.global.exceptions.NavigationException;
import com.gn.global.factory.ActionView;
import com.gn.global.factory.View;
import com.gn.global.plugin.GridFx;
import com.gn.global.plugin.ViewManager;
import com.gn.global.factory.AlertCell;
import com.gn.global.util.PopupCreator;
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
public class Main implements Initializable, ActionView {

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
    @FXML private ToggleButton home;
    @FXML private ToggleButton  about;
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
    @FXML private HBox barHeader;
    @FXML private HBox main;

    @FXML private RadioButton available;

    private FilteredList<ToggleButton> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<ToggleButton> items         = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> designItems   = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> controlsItems = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> chartsItems   = FXCollections.observableArrayList();

    private JFXDialog       dialog          = new JFXDialog();
    private JFXDialogLayout dialog_layout   = new JFXDialogLayout();

    private String path = "/com/gn/theme/css/";
    boolean scrolling   = false;

    private Parent popContent;
    public static Main ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

//        App.getDecorator().floatActions(barHeader);

        DrawerController drawerController = new DrawerController( drawer);
        items = drawerController.getItems();

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





//////        body.setContent(ViewManager.INSTANCE.get("dashboard"));
//
//        try {
//            addSubPop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        drawer.setPopStylesheet(getClass().getResource("/com/gn/theme/css/popover.css"));

    }

    private void hideHamburger(){
        hamburger.setMaxWidth(0);
        hamburger.setPrefWidth(0);
        hamburger.setMinWidth(0);
        hamburger.setMinHeight(0);
        hamburger.setVisible(false);
        barHeader.setPadding(new Insets(0));
    }

    private void hideDrawer(){
        main.getChildren().remove(drawer);
    }

    private void showHamburger(){
        hamburger.setMaxWidth(40);
        hamburger.setPrefWidth(40);
        hamburger.setMinWidth(40);
        hamburger.setMinHeight(40);

        hamburger.setVisible(true);
    }
    private void showDrawer(){
        if(!main.getChildren().contains(drawer)) {
            drawer.setPrefWidth(250D);
            main.getChildren().add(drawer);
            drawer.toBack();
        }
    }


    @FXML
    private void openDrawer(){
        PopupCreator.INSTANCE.createDrawerLeft(drawer);
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
////                body.setContent(ViewManager.INSTANCE.get(button.getText().toLowerCase()));
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

    private VBox filter(ObservableList<ToggleButton>  nodes){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("drawer-content");
        vBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (ToggleButton node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof ToggleButton) {
                items.add( (ToggleButton) node);
            }
        }

        for (Node node : ((VBox) controls.getContent()).getChildren()) {
            controlsItems.add((ToggleButton) node);
            items.add((ToggleButton) node);
        }

        for (Node node : ((VBox) design.getContent()).getChildren()) {
            designItems.add((ToggleButton) node);
            items.add((ToggleButton) node);
        }

        for (Node node : ((VBox) charts.getContent()).getChildren()) {
            chartsItems.add((ToggleButton) node);
            items.add((ToggleButton) node);
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

    private void updateViewDetails(String viewNm){
        try {
            title.setText(ViewManager.INSTANCE.openSubView(body, viewNm));
        } catch (NavigationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goAbout(){
        updateViewDetails("about");
    }

    @FXML
    private void buttons() {
        updateViewDetails("button");
    }

    @FXML
    private void goCarousel() {
        updateViewDetails("carousel");
    }

    @FXML
    private void goToggle() {
        updateViewDetails("toggle-button");
    }

    @FXML
    private void goCards(){
        updateViewDetails("cards");
    }

    @FXML
    private void goBanners(){
        updateViewDetails("banners");
    }

    @FXML
    private void goTextField(){
        updateViewDetails("text-field");
    }

    @FXML
    private void goDatePicker(){
        updateViewDetails("datepicker");
    }

    @FXML
    private void goCheckBox(){
        updateViewDetails("checkbox");
    }

    @FXML
    private void goComboBox(){
        updateViewDetails("combobox");
    }

    @FXML
    private void goColorPicker(){
        updateViewDetails("colorpicker");
    }


    @FXML
    private void goChoiceBox(){
        updateViewDetails("choicebox");
    }

    @FXML
    private void goSplitMenuButton(){
        updateViewDetails("splitmenubutton");
    }

    @FXML
    private void goMenuButton(){
        updateViewDetails("menubutton");
    }

    @FXML
    private void goMenuBar(){
       updateViewDetails("menubar");
    }

    @FXML
    private void goSlider(){
        updateViewDetails("slider");
    }

    @FXML
    private void goMediaView(){
        updateViewDetails("mediaview");
    }

    @FXML
    private void goLabel(){
        updateViewDetails("label");
    }

    @FXML
    private void goImageView(){
        updateViewDetails("imageview");
    }

    @FXML
    private void goHyperlink(){
        updateViewDetails("hyperlink");
    }

    @FXML
    private void goSpinner(){
        updateViewDetails("spinner");
    }

    @FXML
    private void goListView(){
        updateViewDetails("listview");
    }

    @FXML
    private void goRadioButton(){
        updateViewDetails("radiobutton");
    }

    @FXML
    private void goProgressBar(){
        updateViewDetails("progressbar");
    }

    @FXML
    private void goPasswordField(){
        updateViewDetails("passwordfield");
    }

    @FXML
    private void goProgressIndicator(){
        updateViewDetails("progressindicator");
    }

    @FXML
    private void goPagination(){
        updateViewDetails("pagination");
    }

    @FXML
    private void goPieChart(){
        updateViewDetails("piechart");
    }

    @FXML
    private void goStackedBarChart(){
        updateViewDetails("stackedbarchart");
    }

    @FXML
    private void goStackedAreaChart(){
        updateViewDetails("stackedareachart");
    }

    @FXML
    private void goScatterChart(){
        updateViewDetails("scatterchart");
    }


    @FXML
    private void goDashboard(){
        updateViewDetails("dashboard");
    }

    @FXML
    private void goAreaChart(){
        updateViewDetails("areachart");
    }

    @FXML
    private void goBarChart(){
        updateViewDetails("barchart");
    }

    @FXML
    private void goBubbleChart(){
        updateViewDetails("bubblechart");
    }

    @FXML
    private void goLineChart(){
        updateViewDetails("linechart");
    }

    @FXML
    private void goTableView(){
        updateViewDetails("tableview");
    }

    @FXML
    private void goScrollBar(){
        updateViewDetails("scrollbar");
    }

    @FXML
    private void goTreeTableView(){
        updateViewDetails("tree-table-view");
    }

    @FXML
    private void goTextArea(){
        updateViewDetails("text-area");
    }

    @FXML
    private void goTreeView(){
        updateViewDetails("tree-view");
    }

    @FXML
    private void goAnimateButtons(){
        updateViewDetails("animatedbutton");
    }

    @FXML
    private void goAlerts(){
        updateViewDetails("alerts");
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

    @Override
    public void enter() {
        App.getDecorator().getStage().widthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() <= 500) {
                showHamburger();
                barHeader.setPadding(new Insets(20,0,0,0));
            } else if (newValue.doubleValue() <= GridFx.Type.SM.getValue()){
                showHamburger();
                hideDrawer();
                barHeader.setPadding(new Insets(0,0,0,0));
//                PopupCreator.INSTANCE.closePopup();
            } else {
                barHeader.setPadding(new Insets(0,0,0,0));
                showDrawer();
                hideHamburger();
                PopupCreator.INSTANCE.closePopup();
            }
        });
    }

    @Override
    public void exit() {

    }
}
