package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class SideNavController extends ActionView {

    @FXML
    private void goSales() {
//        context.routes().nav("sales_left", "sales_right");
    }

    @FXML
    private void goWelcome() {
//        View view = new SimpleView("view1", "/views/view01.fxml");
//        context.routes().putAndGo(view);
    }

    @FXML
    private void goView02() {
//        View view = new SimpleView("view2", "/view02.fxml");
//        context.routes().putAndGo(view);
    }

    public void goUnderstanding(ActionEvent actionEvent) {
    }

    public void goBuild(ActionEvent actionEvent) {
    }

    public void goButton(ActionEvent actionEvent) {
    }

    public void goLogin(ActionEvent actionEvent) {
    }

    @FXML
    private void goAbout() {
        context.routes().nav("about");
    }
}