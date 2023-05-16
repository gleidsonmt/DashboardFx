package io.github.gleidsonmt.dashboardfx.core.interfaces;

import io.github.gleidsonmt.dashboardfx.core.view.View;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  23/04/2023
 */
public interface Routes {

    Routes setView(String view);

    Routes putAndGo(View View);

    Routes put(View View);

    View getView(String view);

    Routes nav(String key);

}
