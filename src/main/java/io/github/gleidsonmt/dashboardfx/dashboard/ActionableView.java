package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.glad.base.Root;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/06/2025
 */
public interface ActionableView {

    void onEnter(Root root);

    default void onExit(Root root){}
}
