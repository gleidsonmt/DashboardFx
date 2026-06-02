package io.github.gleidsonmt.dashboardfx.presentation;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.drawer.View;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2025
 */
public abstract class CustomizablePresentation extends View implements ActionableView {

    public CustomizablePresentation(String name) {
        super(name);
    }

    public abstract Tutorial create();

    private Root root;

    @Override
    public void onEnter(Root root) {
        this.root =root;
        setContent(create().build().getRoot());
    }

    public Root getRoot() {
        return this.root;
    }
}
