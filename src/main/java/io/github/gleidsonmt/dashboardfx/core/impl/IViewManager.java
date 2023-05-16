package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ViewManager;
import io.github.gleidsonmt.dashboardfx.core.view.View;

import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IViewManager implements ViewManager {

    private final HashMap<String, View> views = new HashMap<>();

    private View current;
    private View previous;

    @Override
    public void add(View View) {
        views.put(View.getName(), View);
    }

    @Override
    public View get(String name) {
        return views.get(name);
    }
}
