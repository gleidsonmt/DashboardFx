/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/10/2022
 */


module io.github.gleidsonmt.dashboardfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.jetbrains.annotations;
    requires io.github.gleidsonmt.gndecorator;
    requires java.logging;


    opens io.github.gleidsonmt.dashboardfx to javafx.base, javafx.fxml, javafx.graphics;
    opens io.github.gleidsonmt.dashboardfx.core.app to javafx.base, javafx.fxml, javafx.graphics;


    exports io.github.gleidsonmt.dashboardfx;
    exports io.github.gleidsonmt.dashboardfx.core.app;



//    requires io.github.gleidsonmt.dashboardfx.core;

//    exports io.github.gleidsonmt.dashboardfx.core.app to javafx.fxml, javafx.graphics;

}