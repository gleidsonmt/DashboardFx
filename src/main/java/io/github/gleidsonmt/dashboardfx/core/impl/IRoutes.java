package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.view.View;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IRoutes implements Routes {

    private final IViewManager manager = new IViewManager();

    private final IRoot root;
    private final Context context;

    public IRoutes(IRoot root, Context context) {
        this.root = root;
        this.context = context;
    }

    @Override
    public Routes setView(String _view) {
//        View view = doActions(manager.get(_view));
        if (_view != null) {
            View view = manager.get(_view);
            if (valid(view)) {
//                root.getBody().setContent(view);
            }
        }
//        doActions(view);
//        root.getLayout().setBody(view.getRoot());
        return this;
    }

    @Override
    public Routes putAndGo(View view) {
        if(valid(view)) {
            manager.add(view);
            doOnInit(view);
            root.getBody().getLayout().setContent(view.getRoot());
            doActions(view);
        } else {

        }
        root.getBody().getLayout().setRight(null);
        return this;
    }

    public Routes put(View view) {
        if(valid(view)) {
            doOnInit(view);
            manager.add(view);
        }
//        this.addView(view);
        return this;
    }

    @Override
    public View getView(String view) {
        return manager.get(view);
    }

    @Override
    public Routes nav(String key) {
        View view = manager.get(key);
        if (view != null) {
            doActions(view);
            root.getBody().getLayout().setContent(view.getRoot());
        }

        root.getBody().getLayout().setRight(null);
        return this;
    }



    private boolean valid(View view) {
        return view != null;
    }

    private void doActions(View view) {
        if(view.getController() != null) {
            view.getController().onEnter();
        }
    }

    private void doOnInit(View view) {
        if(valid(view)) {
            if(view.getController() != null) {
                view.getController().onInit(context);
            }
        }
    }
}
