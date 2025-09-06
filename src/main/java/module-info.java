module io.github.gleidsonmt.dashboardfx {

    requires javafx.media;
    requires javafx.graphics;

    requires java.instrument;

    requires io.github.gleidsonmt.glad;
    requires org.jetbrains.annotations;

    requires java.net.http;
    exports io.github.gleidsonmt.dashboardfx.drawer;

    requires eu.hansolo.colors;
    requires io.github.gleidsonmt.presentation;
    requires io.github.gleidsonmt.blockcode;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.base;
    requires java.logging;

    opens io.github.gleidsonmt.dashboardfx to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.dashboard;
    opens io.github.gleidsonmt.dashboardfx.dashboard to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx.model;

    opens io.github.gleidsonmt.dashboardfx.model to javafx.base;
    exports io.github.gleidsonmt.dashboardfx.material;
    opens io.github.gleidsonmt.dashboardfx.material to javafx.fxml;

}