package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Root;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.layout.IRoot;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.nio.charset.Charset;

public class IRoutes implements Routes {

    private final ViewManager manager = new ViewManager();
    private final IRoot root;
    private final Context context;

    private StringProperty title = new SimpleStringProperty();

    public IRoutes(Root _root, Context _context) {
        this.root = (IRoot) _root;
        this.context = _context;
    }

    // navigate
    public Routes navigate(String id) {
        this.root.getChildren().setAll(root.getWrapper(), manager.get(id).getRoot());
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
                        ViewComposer viewComposer = new ViewComposer();
                        viewComposer.setName(key);
                        viewComposer.setTitle(
                                key.substring(0,1).toUpperCase() +
                                        key.substring(1)

                        );
                        return viewComposer;
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

    @Override
    public Routes registry(View view) {
        doOnInit(view);
        this.addView(view);
        return this;
    }

    public Routes registryAndGo(View view) {
        registry(view);
        try {
            setContent(view.getName());
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // Navigates and load
    public Routes navigate(String id, FXMLLoader loader) {

        IView view = new IView(id, loader);
//        this.addView(view);
        this.root.getLayout().setBody(loader.getRoot());

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
    public StringProperty title() {
        return title;
    }

    @Override
    public void setContent(String _view) throws NavigationException {
        View view = doActions(manager.get(_view));
        doActions(view);
        root.getLayout().setBody(view.getRoot());
    }

    @Override
    public void setView(String _view) throws NavigationException {
        View view = getView(_view);
        doActions(view);
        this.root.getLayout().setBody(view.getRoot());
    }

    private View doActions(View view) throws NavigationException {

        if (view == null) {
            throw new NavigationException("", "");
        }
        context.routes().title().setValue(view.getComposer().getTitle());

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
    @Deprecated(forRemoval = true)
    public View load(String folder, String title, String name) {
        return null;
    }

    @Override
    @Deprecated(forRemoval = true)
    public View load(Pane root, String title, String name) {
        return null;
    }
}
