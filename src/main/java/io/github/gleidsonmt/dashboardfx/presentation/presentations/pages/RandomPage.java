package io.github.gleidsonmt.dashboardfx.presentation.presentations.pages;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2025
 */
public class RandomPage extends CustomizablePresentation {
    public RandomPage() {
        super("Random Page");
    }

    @Override
    public Tutorial create() {
        return new Tutorial();

    }
}
