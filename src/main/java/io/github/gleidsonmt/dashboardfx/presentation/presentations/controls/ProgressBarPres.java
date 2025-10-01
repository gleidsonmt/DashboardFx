package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
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
                .h3("ProgressBar")
                .text("A specialization of the ProgressIndicator which is represented as a horizontal bar.\n" +
                      "ProgressBar sets focusTraversable to false.")
                .legend("javafx.scene.control.ProgressBar")

                .h4("Install", "ProgressBar")
                .demo(new Node[]{
                        createDemo(0.5),
                        createDemo(0.75),
                        createDemo(0.75),
                        createDemo(-1)
                })
                .h4("Install", "ProgressBar")
                .code(TutorialUtils.installExample(Css.PROGRESS_BAR, "ProgressBar"))
                ;
    }

    private @NotNull ProgressBar createDemo(double progress, String... classes) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(progress);
        progressBar.getStyleClass().addAll(classes);
        return progressBar;
    }
}
