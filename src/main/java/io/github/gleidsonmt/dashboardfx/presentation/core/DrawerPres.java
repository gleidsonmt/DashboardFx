package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public class DrawerPres extends CustomizablePresentation {
    public DrawerPres() {
        super("Drawer");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .overview()
                .h3("Drawer")
                .separator()
                .text("Drawer is a component that can be used to display content on the side of the screen.")
                .legend("javafx.scene.control.Drawer")

                ;
    }
}
