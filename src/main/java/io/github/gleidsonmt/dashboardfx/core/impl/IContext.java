package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Routes;

import java.net.URL;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  23/04/2023
 */
public class IContext implements Context {

    private final Routes routes;
    private final IRoot root;

    public IContext(IRoot root) {
        this.root = root;
        this.routes = new IRoutes(root, this);
    }

    @Override
    public URL getResource(String loc) {
        return Objects.requireNonNull(
                getClass().getResource(
                        "/io.github.gleidsonmt.dashboardfx/" + loc));
    }

    @Override
    public Layout layout() {
        return root.getBody().getLayout();
    }

    @Override
    public void setLayout(Layout layout) {
        root.getBody().setContent(layout);
    }

    @Override
    public Routes routes() {
        return routes;
    }

    @Override
    public ActionView controllerOf(String view) {
        return this.routes.getView(view).getController();
    }
}
