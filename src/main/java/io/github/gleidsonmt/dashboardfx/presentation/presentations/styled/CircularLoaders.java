package io.github.gleidsonmt.dashboardfx.presentation.presentations.styled;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.loaders.*;

import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/08/2025
 */
public class CircularLoaders extends CustomizablePresentation {

    public CircularLoaders() {
        super("Circular Loaders");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .overview()
                .h3("Circular Loaders")
                .separator()
                .text("Circular Loaders are used to indicate that the application is loading data.")
                .text("If you want to learn more.")
                .node(TutorialUtils.createCardLink("See this article", "https://gleidsonmt.github.io/#/blog/post/0"))
                .demo(new Node[] {
                        new SuspenseCircle(),
                        new Suspense3DCircle(),
                        new TechCircle(),
                        new AtomicLoader()
                })
                .h3("Using")
                .code("""
                        // Importing
                        import io.github.gleidsonmt.glad.controls.loaders.*;
                        
                        // Creating loaders
                        SuspenseCircle suspenseCircle = new SuspenseCircle();
                        // Second
                        Suspense3DCircle suspense3DCircle = new Suspense3DCircle();
                        // Third
                        TechCircle techCircle = new TechCircle();
                        
                        // Update text
                        circle.setLegend("Loading.. ");
                        circle.setTitle("Task #23");
                        """)
                .h3("Customize")
                .text("You can customize the loader using the css.")
                .code("""
                        .circle-loader > .abstractContainer-circle > .track-circle
                        {
                            -fx-stroke: -fx-accent;
                        }
                        
                        .circle-loader > .abstractContainer-circle > .foreground-circle
                        {
                            -fx-stroke: rgba(0,0,0,.2);
                        }
                        
                        .circle-loader > .abstractContainer-circle > .title  {
                            -fx-font-size: 20pt;
                        }
                        
                        .circle-loader >  .legend  {
                            -fx-font-size: 14pt;
                        }
                        """, "css")

                ;
    }
}
