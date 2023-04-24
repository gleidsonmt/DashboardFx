package io.github.gleidsonmt.dashboardfx.core.view;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  11/04/2023
 */
public class ViewMap {

    private List<ViewComposer> views = new ArrayList<>();

    public List<ViewComposer> getViews() {
        return views;
    }

    public void setViews(List<ViewComposer> views) {
        this.views = views;
    }

}
