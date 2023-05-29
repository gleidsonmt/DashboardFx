package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.impl.layout.Flow;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.view.layout.SnackBar;
import io.github.gleidsonmt.dashboardfx.core.view.layout.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.model.SearchItem;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private SnackBar snackBar;
    private final HostServices hostServices;

    private final ObservableList<SearchItem> searchItems;

    public IContext(IRoot root, HostServices hostServices) {
        this.root = root;
        this.hostServices = hostServices;
        this.routes = new IRoutes(root, this);
        searchItems = FXCollections.observableArrayList();
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

    private Flow flow;
    @Override
    public Flow flow() {
        if (flow == null) flow = new Flow(root);
        return flow;
    }

    @Override
    public ObservableList<SearchItem> searchItems() {
        return searchItems;
    }

    @Override
    public Wrapper wrapper() {
        return root.getWrapper();
    }

    @Override
    public ActionView controllerOf(String view) {
        return this.routes.getView(view).getController();
    }

    @Override
    public void openLink(String uri) {
        hostServices.showDocument(uri);
    }

    @Override
    public SnackBar createSnackBar() {
        if (snackBar == null) snackBar = new SnackBar(root);
        return snackBar;
    }
}
