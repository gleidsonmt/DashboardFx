/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/10/2022
 */


module io.github.gleidsonmt.dashboardfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.jetbrains.annotations;
    requires java.logging;

    requires controlsfx;

    requires io.github.gleidsonmt.gndecorator;
    requires io.github.gleidsonmt.gncontrols;

    requires fr.brouillard.oss.cssfx;

    requires org.commonmark;

    requires scenicView;
    requires org.yaml.snakeyaml;
    requires animatefx;

//    requires CurvedFittedAreaChart;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.material;
    requires org.kordamp.ikonli.javafx;

    opens io.github.gleidsonmt.dashboardfx to javafx.base, javafx.fxml, javafx.graphics;
    opens io.github.gleidsonmt.dashboardfx.core.app to javafx.base, javafx.fxml, javafx.graphics;

    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.core.app;
    exports io.github.gleidsonmt.dashboardfx.core.app.interfaces;
    opens io.github.gleidsonmt.dashboardfx.core.app.interfaces to javafx.base, javafx.fxml, javafx.graphics;
    opens io.github.gleidsonmt.dashboardfx.core.app.controllers to javafx.fxml, javafx.graphics;

    opens io.github.gleidsonmt.dashboardfx.controllers to javafx.fxml;
    opens io.github.gleidsonmt.dashboardfx.core.layout to javafx.fxml;
    exports io.github.gleidsonmt.dashboardfx.core.app.view_wrapper;
    opens io.github.gleidsonmt.dashboardfx.core.app.view_wrapper to javafx.base, javafx.fxml, javafx.graphics;
    exports io.github.gleidsonmt.dashboardfx.core.app.services;
    opens io.github.gleidsonmt.dashboardfx.core.app.services to javafx.base, javafx.fxml, javafx.graphics, org.yaml.snakeyaml;
    exports io.github.gleidsonmt.dashboardfx.views;
    opens io.github.gleidsonmt.dashboardfx.views to javafx.base, javafx.fxml, javafx.graphics;
    exports io.github.gleidsonmt.dashboardfx.views.controls;
    opens io.github.gleidsonmt.dashboardfx.views.controls to javafx.base, javafx.fxml, javafx.graphics;

//    requires io.github.gleidsonmt.dashboardfx.core;
//    exports io.github.gleidsonmt.dashboardfx.core.app to javafx.fxml, javafx.graphics;

}