
module io.github.gleidsonmt.dashboardfx {

    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.web;
    requires transitive javafx.media;

    requires java.logging;
    requires javafx.swing;

    requires org.yaml.snakeyaml;
    requires org.jetbrains.annotations;
    requires animatefx;
    requires org.controlsfx.controls;

    requires GNAvatarView;

    requires org.kordamp.ikonli.material;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    requires fr.brouillard.oss.cssfx;
//    requires io.github.gleidsonmt.gncontrols;
//    requires scenicView;
    requires com.dlsc.gemsfx;

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
    opens io.github.gleidsonmt.dashboardfx.core.picture_selector to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.model to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx.model;

}