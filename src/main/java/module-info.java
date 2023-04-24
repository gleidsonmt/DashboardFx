
module io.github.gleidsonmt.dashboardfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    requires org.yaml.snakeyaml;
    requires org.jetbrains.annotations;
    requires javafx.web;

//    requires io.github.gleidsonmt.gncontrols;
//    requires scenicView;

    opens io.github.gleidsonmt.dashboardfx to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.controllers to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.core.view;
}