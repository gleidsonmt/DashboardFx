package io.github.gleidsonmt.dashboardfx.core;

import io.github.gleidsonmt.dashboardfx.core.impl.Layout;
import io.github.gleidsonmt.dashboardfx.core.impl.layout.Flow;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.view.layout.SnackBar;
import io.github.gleidsonmt.dashboardfx.core.view.layout.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.model.SearchItem;
import javafx.collections.ObservableList;

import java.net.URL;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/04/2023
 */
public interface Context {

    URL getResource(String res);

    Layout layout();

    void setLayout(Layout layout);

    Routes routes();

    Wrapper wrapper();

    Flow flow();

    ObservableList<SearchItem> searchItems();

    /*********************************************************
     *
     * Util methods
     *
     *******************************************************/


    SnackBar createSnackBar();

    void openLink(String uri);

    ActionView controllerOf(String view);

}
