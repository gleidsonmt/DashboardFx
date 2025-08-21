package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
public class ProgressBarPres extends CustomizablePresentation {

    public ProgressBarPres() {
        super("ProgressBar");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Progress Bar")
                .text("A specialization of the ProgressIndicator which is represented as a horizontal bar.\n" +
                      "ProgressBar sets focusTraversable to false.")
                .legend("javafx.scene.control.ProgressBar")
                .h4("Default", "Progress Bar")
                .demo(createDemo())
                .code("ProgressBar progressBar = new ProgressBar();")
                .h4("Indeterminate", "Progress Bar")
                .demo(createDemo(-1))
                .code("progressBar.setProgress(-1);");
    }

    private @NotNull Node createDemo(String... classes) {
        return createDemo(0.5, classes);
    }

    private @NotNull ProgressBar createDemo(double progress, String... classes) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(progress);
        progressBar.getStyleClass().addAll(classes);
        return progressBar;
    }
}
