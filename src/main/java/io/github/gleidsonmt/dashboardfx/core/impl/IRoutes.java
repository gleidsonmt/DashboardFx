package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IRoutes implements Routes {

    private final IViewManager manager = new IViewManager();

    private final IRoot root;
    private final Context context;

    private StringProperty title = new SimpleStringProperty();

    public IRoutes(IRoot root, Context context) {
        this.root = root;
        this.context = context;
    }

    @Override
    public Routes setView(String _view) {
//        View view = doActions(manager.get(_view));
        if (_view != null) {
            View View = manager.get(_view);
            if (valid(View)) {
//                root.getBody().setContent(View);
            }
        }
//        doActions(view);
//        root.getLayout().setBody(view.getRoot());
        return this;
    }

    @Override
    public void putAndGo(View view) {
        if(valid(view)) {
            manager.add(view);
            doOnInit(view);
            root.getBody().getLayout().setContent(view.getRoot());
            doActions(view);
            title.set(view.getName());
        } else {

        }
        root.getBody().getLayout().setRight(null);
    }

    public Routes put(View View) {
        if(valid(View)) {
            doOnInit(View);
            manager.add(View);
        }
//        this.addView(View);
        return this;
    }

    @Override
    public View getView(String view) {
        return manager.get(view);
    }

    @Override
    public Routes nav(String key) {
        View View = manager.get(key);
        if (View != null) {
            doActions(View);
            root.getBody().getLayout().setContent(View.getRoot());
        }

        title.set(key);

        root.getBody().getLayout().setRight(null);
        return this;
    }

    @Override
    public StringProperty title() {
        return title;
    }

    private boolean valid(View View) {
        return View != null;
    }

    private void doActions(View View) {
        if(View.getController() != null) {
            View.getController().onEnter();
        }
    }

    private void doOnInit(View View) {
        if(valid(View)) {
            if(View.getController() != null) {
                View.getController().onInit(context);
            }
        }
    }
}
