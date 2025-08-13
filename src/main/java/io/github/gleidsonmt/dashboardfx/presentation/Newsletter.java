package io.github.gleidsonmt.dashboardfx.presentation;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.StringUtils;
import javafx.scene.layout.StackPane;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2025
 */
public class Newsletter extends StackPane {

    public Newsletter() {
        getChildren().setAll(
          new Tutorial()
                  .h3("Whats up!? Try to maintain updates here.")
                  .legend(LocalDate.of(2025, Month.SEPTEMBER, 4) + " - " +LocalTime.of(13, 45))
                  .text("""
                        I really had a journey to learn english.
                        Now I can talk, but I'm a bad writer.
                      """)
                  .build()
                  .getRoot()
        );
    }


}
