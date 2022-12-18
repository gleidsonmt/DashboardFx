package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.layout.Root;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.nio.charset.Charset;

public class IRoutes implements Routes {

    private final ViewManager manager = new ViewManager();
    private final Root root;
    private final Context context;

    public IRoutes(Root _root, Context _context) {
        this.root = _root;
        this.context = _context;
    }

    // navigate
    public Routes navigate(String id) {
        this.root.getChildren().setAll(manager.get(id).getRoot());
        return this;
    }

    @Override
    public Routes registry(String key, Parent parent) {
        this.addView(
                new View() {
                    @Override
                    public String getName() {
                        return key;
                    }

                    @Override
                    public ViewComposer getComposer() {
                        return null;
                    }

                    @Override
                    public ActionView getController() {
                        return null;
                    }

                    @Override
                    public Parent getRoot() {
                        return parent;
                    }

                    @Override
                    public Charset getCharset() {
                        return null;
                    }

                    @Override
                    public URL getLocation() {
                        return null;
                    }
                }
        );

        return this;
    }

    // Navigates and load
    public Routes navigate(String id, FXMLLoader loader) {

        IView view = new IView(id, loader);
//        this.addView(view);
        this.root.getChildren().setAll((Node) loader.getRoot());

        return this;
    }


    public Routes registry(IView IView) {
        this.manager.add(IView);
        return this;
    }


    @Deprecated
    public Routes registry(String key, FXMLLoader loader) {
        View view = new IView(key, loader);
        this.addView(view);
        return this;
    }

    @Override
    public void setContent(String _view) throws NavigationException {
        View view = doActions(manager.get(_view));
        System.out.println("view = " + view.getRoot());
        doActions(view);
        root.getLayout().setBody(view.getRoot());
    }

    @Override
    public void setView(String _view) throws NavigationException {
        View view = getView(_view);
        doActions(view);
        this.root.getLayout().setBody(view.getRoot());
    }

    private View doActions(View view) {

        if (view.getController() != null) {
            Object controller = view.getController();
            if (controller  instanceof ActionView _view ) {
                _view.onEnter(context);
            }
        }

        return view;

    }

    private View doOnInit(View view) {
        if (view.getController() != null) {
            Object controller = view.getController();
            if (controller instanceof  ActionView _view) {
                _view.onInit(context);
            }
        }
        return view;
    }

    public void addView(View view) {
        doOnInit(view);
        manager.add(view);
    }

    @Deprecated
    @Override
    public void goHome() {

    }

    @Override
    public View getView(String view) {
        return manager.get(view);
    }

    @Override
    public View getCurrent() {
        return null;
    }

    @Override
    public  View getPrevious() {
        return null;
    }

    @Override
    @Deprecated
    public View load(String folder, String title, String name) {
        return null;
    }

    @Override
    @Deprecated
    public View load(Pane root, String title, String name) {
        return null;
    }
}
