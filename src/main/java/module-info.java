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

    requires io.github.gleidsonmt.gndecorator;
    requires io.github.gleidsonmt.gncontrols;

    requires fr.brouillard.oss.cssfx;

    requires scenicView;
    requires org.yaml.snakeyaml;
    requires animatefx;

    opens io.github.gleidsonmt.dashboardfx to javafx.base, javafx.fxml, javafx.graphics;
    opens io.github.gleidsonmt.dashboardfx.core.app to javafx.base, javafx.fxml, javafx.graphics;


    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.core.app;
    exports io.github.gleidsonmt.dashboardfx.core.app.interfaces;
    opens io.github.gleidsonmt.dashboardfx.core.app.interfaces to javafx.base, javafx.fxml, javafx.graphics;
    opens io.github.gleidsonmt.dashboardfx.core.app.controllers to javafx.fxml, javafx.graphics;

    opens io.github.gleidsonmt.dashboardfx.core.app.services to org.yaml.snakeyaml;
    opens io.github.gleidsonmt.dashboardfx.views to javafx.fxml;

//    requires io.github.gleidsonmt.dashboardfx.core;
//    exports io.github.gleidsonmt.dashboardfx.core.app to javafx.fxml, javafx.graphics;

}