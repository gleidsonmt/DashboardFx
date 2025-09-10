package io.github.gleidsonmt.dashboardfx.presentation;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.View;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2025
 */
public abstract class CustomizablePresentation extends View implements ActionableView {

    public CustomizablePresentation(String name) {
        super(name);
    }

    public abstract Tutorial create();

    @Override
    public void onEnter(Root root) {
        setContent(create().build().getRoot());
    }
}
