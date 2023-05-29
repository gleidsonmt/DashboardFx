
module io.github.gleidsonmt.dashboardfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    requires org.yaml.snakeyaml;
    requires org.jetbrains.annotations;
    requires javafx.web;
    requires org.kordamp.ikonli.material;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires animatefx;

//    requires io.github.gleidsonmt.gncontrols;
    requires scenicView;
    requires fr.brouillard.oss.cssfx;

    opens io.github.gleidsonmt.dashboardfx to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.controllers to javafx.fxml;
    opens  io.github.gleidsonmt.dashboardfx.core.controls.icon to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.core.view;
    exports io.github.gleidsonmt.dashboardfx.core.model;
    opens io.github.gleidsonmt.dashboardfx.core.model to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.core.controls to javafx.fxml;
}