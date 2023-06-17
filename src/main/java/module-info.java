
module io.github.gleidsonmt.dashboardfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    requires java.logging;

    requires org.yaml.snakeyaml;
    requires org.jetbrains.annotations;
    requires animatefx;
//    requires io.github.gleidsonmt.gncarousel;

    requires org.kordamp.ikonli.material;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    requires fr.brouillard.oss.cssfx;


//    requires io.github.gleidsonmt.gncontrols;
//    requires scenicView;

    opens io.github.gleidsonmt.dashboardfx to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.controllers to javafx.fxml;
    opens  io.github.gleidsonmt.dashboardfx.core.controls.icon to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx;
    opens io.github.gleidsonmt.dashboardfx.views.controls to javafx.base;
    exports io.github.gleidsonmt.dashboardfx.core.view;
    opens io.github.gleidsonmt.dashboardfx.core.controls to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx.core.model;
    opens io.github.gleidsonmt.dashboardfx.core.model to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.core.controls.skin to javafx.fxml;

}