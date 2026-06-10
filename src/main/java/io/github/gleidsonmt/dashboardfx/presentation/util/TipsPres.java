package io.github.gleidsonmt.dashboardfx.presentation.util;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;

public class TipsPres extends CustomizablePresentation {

    public TipsPres() {
        super("Tips");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .h1("Tips")
                .separator()
                .text("Practical tips for developing and contributing to DashboardFx.")
                .h2("Use the Gradle Wrapper")
                .text("""
                        Use the wrapper included in the repository instead of a system Gradle installation.
                        It runs the project with the Gradle version expected by the build.
                        """)
                .code(".\\gradlew build\n.\\gradlew run", "bash")
                .h2("Install the theme first")
                .text("""
                        Install the required theme styles on the Scene before using their style classes.
                        This ensures that controls are styled correctly when they are displayed.
                        """)
                .code("ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);", "java")
                .h2("Clone with submodules")
                .text("""
                        Clone the repository with its submodules so that the glad, presentation,
                        and blockcode projects are available locally.
                        """)
                .code("git clone --recursive https://github.com/gleidsonmt/DashboardFx.git", "bash")
                .h3("Synchronize submodules")
                .text("""
                        After switching branches or pulling changes, update the submodules to the commits
                        recorded by the current DashboardFx branch.
                        """)
                .code("git submodule update --init --recursive", "bash")
                .h3("Debug the running application")
                .text("""
                        Start the application with JVM debugging enabled, then attach the debugger from your IDE.
                        This is useful for inspecting event handlers, bindings, and runtime state.
                        """)
                .code(".\\gradlew run --debug-jvm", "bash")
                .h4("Use development tools carefully")
                .text("""
                        ScenicView and CSSFX can inspect the scene graph and reload CSS during development.
                        Enable HoTools only while debugging and keep these tools out of packaged builds.
                        """)
                .code("HotTools.addTools(stage.getScene());", "java")
                .h3("Keep CSS in the correct layer")
                .text("""
                        Use ThemeProvider and Css values for shared glad styles.
                        Use Assets.getCss for DashboardFx resources such as master.css and project-specific overrides.
                        """)
                .code("""
                        ThemeProvider.install(scene, Css.ALL);
                        scene.getStylesheets().add(Assets.getCss("master.css"));
                        """, "java")
                .h3("Keep pull requests focused")
                .text("""
                        Keep each pull request focused on one task.
                        Avoid unrelated formatting, generated files, IDE settings, or accidental submodule changes.
                        """)
                .h3("Use the Css enum")
                .text("""
                        Prefer Css enum values instead of hard-coded stylesheet paths.
                        This makes theme dependencies easier to understand and maintain.
                        """)
                .code("ThemeProvider.install(scene, Css.BUTTON, Css.COLORS);", "java");
    }
}
