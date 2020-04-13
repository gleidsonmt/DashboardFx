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

import animatefx.animation.Shake;
import com.gn.App;
import com.gn.GNAvatarView;
import com.gn.global.factory.badges.BadgeSettings;
import com.gn.global.UserDetail;
import com.gn.global.exceptions.NavigationException;
import com.gn.global.factory.*;
import com.gn.global.factory.badges.BadgeTasks;
import com.gn.global.factory.badges.BadgeMessages;
import com.gn.global.factory.badges.BadgeNotification;
import com.gn.global.plugin.GridFx;
import com.gn.global.plugin.ViewManager;
import com.gn.global.util.PopupCreator;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.PopOver;

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
    @FXML private Label name;

    @FXML private RadioButton available;

    private FilteredList<ToggleButton> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<ToggleButton> items         = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> designItems   = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> controlsItems = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> chartsItems   = FXCollections.observableArrayList();

    private final UserDetail        userDetail          = new UserDetail("Jane Doe", "Jane", "SubTitle");
    private final BadgeMessages     badgeMessages       = new BadgeMessages();
    private final BadgeNotification badgeNotification   = new BadgeNotification();
    private final BadgeTasks badgeAlerts         = new BadgeTasks();
    private final BadgeSettings     badgeSettings       = new BadgeSettings("Text", "Subtitle");
    private final HBox              contentBadges       = new HBox();

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

//        App.getDecorator().floatActions(barHeader);

        DrawerController drawerController = new DrawerController( drawer );
        items = drawerController.getItems();

        populateItems();
        filteredList = new FilteredList<>( items, s -> true );

        search.textProperty().addListener( obs -> {

            String filter = search.getText();
            if ( filter == null || filter.length() == 0 ) {
                barInitial();
                clear.setMouseTransparent(true);
                searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
            } else {
                barFiltered(filter);
                clear.setMouseTransparent(false);
                searchIcon.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");

            }
        });

        search.setOnMouseClicked(event -> {
            new Shake(clear).play();
        });

        name.setOnMouseEntered(event ->{
            new Shake(name).play();
        });

    }

    private void hideHamburger(){
        hamburger.setMaxWidth(0);
        hamburger.setPrefWidth(0);
        hamburger.setMinWidth(0);
        hamburger.setMinHeight(0);
        hamburger.setVisible(false);
        barHeader.setPadding(new Insets(0));
    }

    private void hideDrawer() {
        main.getChildren().remove(drawer);
        App.getDecorator().showCustoms();

        if(!info.getChildren().contains(contentBadges)) info.getChildren().add(contentBadges);
//        App.getDecorator().removeCustom(userDetail);
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
        App.getDecorator().hideCustoms();
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

    @Override
    public void enter() {
        App.getDecorator().getStage().widthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() <= GridFx.Type.XS.getValue()) {
                hideDrawer();
                showHamburger();
//                barHeader.setPadding(new Insets(20,0,0,0));
                removeBadges();

            } else if (newValue.doubleValue() <= GridFx.Type.SM.getValue()){
                showHamburger();
                hideDrawer();
                barHeader.setPadding(new Insets(0,0,0,0));
                addBadges();
//                PopupCreator.INSTANCE.closePopup();
            } else {
                barHeader.setPadding(new Insets(0,0,0,0));
                showDrawer();
                hideHamburger();
                PopupCreator.INSTANCE.closePopup();
                addBadges();
            }
        });

        StackPane body = (StackPane) ViewManager.INSTANCE.get("login").getRoot();
        PopupCreator.INSTANCE.createPopup(body);

        App.getDecorator().addCustom(userDetail);
        App.getDecorator().addCustom(badgeSettings);

        addBadges();
    }

    @Override
    public void exit() {
    }

    private void addBadges(){
        updateStyles(false);

        if(!App.getDecorator().getCustoms().contains(userDetail)) App.getDecorator().addCustom(userDetail);

        if(!App.getDecorator().getCustoms().contains(badgeMessages)) {

            App.getDecorator().addCustom(badgeMessages);
            App.getDecorator().addCustom(badgeNotification);
            App.getDecorator().addCustom(badgeAlerts);
        }

    }

    private void removeBadges(){
        App.getDecorator().removeCustom(badgeMessages);
        App.getDecorator().removeCustom(badgeNotification);
        App.getDecorator().removeCustom(badgeAlerts);
        contentBadges.getChildren().setAll(badgeAlerts, badgeMessages, badgeNotification);
        updateStyles(true);

    }

    private void updateStyles(boolean addStyle){
        contentBadges.getChildren().forEach(
                e -> {
                    if(addStyle) {
                        if (!e.getStyleClass().contains("badge-drawer")) {
                            e.getStyleClass().add("badge-drawer");
                        }
                    } else {
                        e.getStyleClass().remove("badge-drawer");
                    }
                }
        );
    }

    @FXML
    private void openProfile(){
        userDetail.getPopOver().show(name);
    }
}
