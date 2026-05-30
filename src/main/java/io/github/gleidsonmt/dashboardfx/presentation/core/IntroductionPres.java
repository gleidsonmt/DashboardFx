package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class IntroductionPres extends CustomizablePresentation {

    public IntroductionPres() {
        super("Introduction");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .overview()
                .h3("Introduction")
                .separator()
                .text("""
                        I’ll tutor you around this ecosystem I built.
                        
                        The main idea here, it’s creating examples to show the power of JavaFX.
                        There’s a lot of techniques to do that, but it’s not a normal thing to see in JavaFX.
                        On a normal day, you need ways to get fast to get a system design, repeating tasks is the most boring thing daily.
                        This project expects to add some layers to production by using known patterns.
                        And I know there’s a curve to learn. But I hope to make it easier.
                        
                        """)
                .separator()
                .h3("Layout", "Introduction")
                .text("""
                        As the most dashboards the drawer is on the left, with the logo and a search field
                        As suggested the search view is used to filter by modules. When you typed inside it.
                        """)
                .image(Assets.getImage("part3.png"))
                .separator()
                .h3("Modules", "Introduction")
                .text("""
                        The modules is the view or a group of views.
                        If the it's a view is represented by a ToggleButton.
                        If it's a group of views is represented by a TilePane.
                        
                        When a view (ToggleButton) is clicked the content is updated to the content of this view.
                        The modules are separated by sections, project, theme, example and extras.
                        All modules follow a top and dow overview.. so you can actually go deeper and learn more getting the sequence of the modules.
                        """)

                .h3("Core", "Introduction")
                .text("""
                        The core section involves getting things by context and interactions by the root node.
                        """)
                .separator()
                .h3("Theme", "Introduction")
                .text("""
                        The theme section contains examples using default javafx + a custom theme.
                        The custom theme is provided by a class called ThemeProvider.
                        On the each view you can see how to install the custom css.
                        """)
                .separator()
                .h3("Example", "Introduction")
                .text("""
                        The example section has examples and customizable controls and how to work with it.
                        """)
                .separator()
                .h3("Extras", "Introduction")
                .text("""
                        The extras section represents tips with javafx and additional resource.
                        """)
                .separator()
                .text("""
                        Now if you want a point of start..
                        I've been working and idealizing a skeleton with the minimal to create a project and start.
                        (I'm still working on it, all help is welcome.).
                        """)


                ;

    }
}
