package io.github.gleidsonmt.dashboardfx.presentation.about;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import javafx.scene.layout.StackPane;

import java.net.URI;

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
                        .h3("Dependencies", "Introduction")
                        .h3("Block Code", "Dependencies")
                        .text("This libs creates a block of codes using syntax highlight.")
                        .node(TutorialUtils.createCardLink("BlockCode Project", "https://github.com/gleidsonmt/blockcode"))

                        .h3("Presentation", "Dependencies")
                        .text("The main base class to create presentations.")
                        .node(TutorialUtils.createCardLink("Presentation Project", "https://github.com/gleidsonmt/presentation"))

                        .h3("Glad", "Dependencies")
                        .text("The core that contains customizable controls and themes.")
                        .node(TutorialUtils.createCardLink("Glad Project","https://github.com/gleidsonmt/glad"))

                        .h3("Testing", "Dependencies")
                        .text("Tools used to build and test this project.")
                        .text("(This libs it's only used in runtime do not create an jar file, or an .exe with this, they will probably crash)")
                        .text("ScenicView can get information about layout, css and transform nodes in runtime.")
                        .node(TutorialUtils.createCardLink("Scenic View Project", "https://github.com/JonathanGiles/scenic-view"))
                        .text("Special lib to visualize and update css when app is running.")
                        .node(TutorialUtils.createCardLink("CSSFX",  "https://github.com/McFoggy/cssfx"))
                        .text("That's the end. But it's also the beginning to this project. Most things it's experimental, but the time will make consistence.")
                        .build()
                        .getRoot()
        );
    }


}
