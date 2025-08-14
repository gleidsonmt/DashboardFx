package io.github.gleidsonmt.dashboardfx.presentation.about;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2025
 */
public class AboutPres extends StackPane {

    public AboutPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Goals")
                        .text("""
                                This projects aims to amplify javafx developers core.
                                Using examples, and multiple components.
                                """)
                        .h3("Non Goals")
                        .text("""
                                I like an amount of technologies based on web designer, sometimes
                                this techniques can be fit in other contexts.
                                """)

                        .h3("Structure")
                        .text("""
                                This is a Gradle multi-project, which means it contains multiple projects within it.
                                The first one is blockcode, an adaption to view highlighted code. (java, css, xml)
                                The second one is presentation, a base class to create presentations.
                                The third one is glad, the core that contains customizable controls and themes.
                                All projects has its repositories and you can see it in the bootom of this presentation.
                                """)
                        .indicators()
                        .h3("Environment")
                        .text("""
                                The project is build using Intellij community.
                                At moment the setup is
                                Javafx 23.0.2
                                Java 23.0.2
                                Gradle 8.13
                                """)
                        .build()
                        .getRoot()
        );
    }


}
