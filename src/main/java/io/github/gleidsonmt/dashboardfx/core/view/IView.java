package io.github.gleidsonmt.dashboardfx.core.view;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  11/04/2023
 */
public class IView implements View {

    private ViewComposer composer = null;
    private final FXMLLoader loader;
    private String id;

    IView(String id, FXMLLoader loader) {
        this.id = id;
        this.loader = loader;
    }

    public IView(ViewComposer composer, FXMLLoader loader) {
        this.composer = composer;
        this.loader = loader;
    }

    @Override
    public ViewComposer getComposer() {
        return composer;
    }

    @Override
    public ActionView getController() {

//        if (loader.getController() != null) {
//
//            if (!(loader.getController() instanceof ActionView)) {
//
////                try {
////                    throw new ControllerCastException("CAST", "Error on controller " + loader.getController() + " does not extends action view.");
////                } catch (ControllerCastException e) {
////                    e.printStackTrace();
////                }
//
//            }
//        }

        return loader.getController();
    }

    @Override
    public Parent getRoot() {
        return loader.getRoot();
    }

    @Override
    public Charset getCharset() {
        return loader.getCharset();
    }

    @Override
    public URL getLocation() {
        return loader.getLocation();
    }

    @Override
    public String getName() {
        return composer == null ? id : composer.getName() ;
    }

    @Override
    public String toString() {
        return "ViewConstructor{" +
                "composer=" + composer +
                ", loader=" + loader +
                '}';
    }
}
