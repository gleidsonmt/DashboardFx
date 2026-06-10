package io.github.gleidsonmt.dashboardfx.presentation.util;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;

public class TipsPres extends CustomizablePresentation {

    public TipsPres() {
        super("Tips");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .h3("Tips")
                .text("Practical tips for developing and contributing to DashboardFx.")
                .h4("Use the Gradle Wrapper", "Tips")
                .text("""
                        Use the wrapper included in the repository instead of a system Gradle installation.
                        It runs the project with the Gradle version expected by the build.
                        """)
                .code(".\\gradlew build\n.\\gradlew run", "bash")
                .h4("Install the theme first", "Tips")
                .text("""
                        Install the required theme styles on the Scene before using their style classes.
                        This ensures that controls are styled correctly when they are displayed.
                        """)
                .code("ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);", "java")
                .h4("Clone with submodules", "Tips")
                .text("""
                        Clone the repository with its submodules so that the glad, presentation,
                        and blockcode projects are available locally.
                        """)
                .code("git clone --recursive https://github.com/gleidsonmt/DashboardFx.git", "bash")
                .h4("Synchronize submodules", "Tips")
                .text("""
                        After switching branches or pulling changes, update the submodules to the commits
                        recorded by the current DashboardFx branch.
                        """)
                .code("git submodule update --init --recursive", "bash")
                .h4("Debug the running application", "Tips")
                .text("""
                        Start the application with JVM debugging enabled, then attach the debugger from your IDE.
                        This is useful for inspecting event handlers, bindings, and runtime state.
                        """)
                .code(".\\gradlew run --debug-jvm", "bash")
                .h4("Use development tools carefully", "Tips")
                .text("""
                        ScenicView and CSSFX can inspect the scene graph and reload CSS during development.
                        Enable LibrariesTools only while debugging and keep these tools out of packaged builds.
                        """)
                .code("LibrariesTools.addTools(stage.getScene());", "java")
                .h4("Keep CSS in the correct layer", "Tips")
                .text("""
                        Use ThemeProvider and Css values for shared glad styles.
                        Use Assets.getCss for DashboardFx resources such as master.css and project-specific overrides.
                        """)
                .code("""
                        ThemeProvider.install(scene, Css.ALL);
                        scene.getStylesheets().add(Assets.getCss("master.css"));
                        """, "java")
                .h4("Keep pull requests focused", "Tips")
                .text("""
                        Keep each pull request focused on one task.
                        Avoid unrelated formatting, generated files, IDE settings, or accidental submodule changes.
                        """)
                .h4("Use the Css enum", "Tips")
                .text("""
                        Prefer Css enum values instead of hard-coded stylesheet paths.
                        This makes theme dependencies easier to understand and maintain.
                        """)
                .code("ThemeProvider.install(scene, Css.BUTTON, Css.COLORS);", "java");
    }
}
