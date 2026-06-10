package io.github.gleidsonmt.dashboardfx.presentation.about;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.presentation.Presentation;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2025
 */
public class AboutPres extends CustomizablePresentation {

    public AboutPres() {
        super("About");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .h3("Introduction")
                .text("Hi, my name is Gleidson. I’m a developer from Brazil.")

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
                        All projects has its repositories and you can see it in the bottom of this presentation.
                        These project are updated according to this project goals.
                        """)
                .h3("Environment")
                .text("""
                        The project is build using Intellij community.
                        My configuration to build 
                        Javafx 23.0.2 
                        Java 23.0.2
                        Gradle 8.13
                        """)

                .h3("Dependencies")
                .h3("Block Code")
                .node(TutorialUtils.createCardLink("BlockCode Project", "https://github.com/gleidsonmt/blockcode"))
                .h3("Presentation")
                .text("The main base class to create presentations.")
                .node(TutorialUtils.createCardLink("Presentation Project", "https://github.com/gleidsonmt/presentation"))
                .h3("Glad")
                .text("The core that contains customizable controls and themes.")
                .node(TutorialUtils.createCardLink("Glad Project", "https://github.com/gleidsonmt/glad"))
                .h3("Testing")
                .text("Tools used to build and test this project.")
                .text("(This libs it's only used in runtime do not create an jar file, or an .exe with this, they will probably crash)")
                .text("ScenicView can get information about layout, css and transform nodes in runtime.")
                .node(TutorialUtils.createCardLink("Scenic View Project", "https://github.com/JonathanGiles/scenic-view"))
                .text("This project saves me a lot of time, it's live css.")
                .node(TutorialUtils.createCardLink("CSSFX", "https://github.com/McFoggy/cssfx"))
                .text("That's the end. But it's also the beginning to this project. Most things it's experimental, but the time will make consistence.")
                ;
    }
}
