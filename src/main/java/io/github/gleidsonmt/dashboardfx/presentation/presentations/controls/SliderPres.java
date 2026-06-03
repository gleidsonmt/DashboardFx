package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Locale;


public class SliderPres extends CustomizablePresentation {

    public SliderPres() {
        super("Slider");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Slider")
                .text("""
                        The Slider control lets users select a numeric value by dragging a thumb along a track.
                        It can be displayed horizontally or vertically, show tick marks and labels, snap to fixed steps, and react to value changes in real time.""")
                .legend("javafx.scene.control.Slider")
                .demo(createDemo())
                .h4("Install", "Slider")
                .code(TutorialUtils.installExample(Css.SLIDER) + """
                        Text textSimpleSlider = new Text("Basic Slider: ");

                        Slider sliderHorizontalS = new Slider();
                        Slider sliderVerticalS = new Slider();

                        sliderHorizontalS.setBlockIncrement(2.0);
                        sliderHorizontalS.setMajorTickUnit(1);
                        sliderHorizontalS.setMinorTickCount(0);
                        sliderHorizontalS.setSnapToTicks(true);
                        sliderHorizontalS.setOrientation(Orientation.HORIZONTAL);

                        sliderVerticalS.setBlockIncrement(2.0);
                        sliderVerticalS.setMajorTickUnit(1);
                        sliderVerticalS.setMinorTickCount(0);
                        sliderVerticalS.setSnapToTicks(true);
                        sliderVerticalS.setOrientation(Orientation.VERTICAL);

                        Separator separator = new Separator();
                        separator.setOrientation(Orientation.VERTICAL);

                        Text textSlider = new Text("Configured Slider: ");

                        Label textValueH = new Label("    Horizontal value: none");
                        Label textValueV = new Label("    Vertical value: none");

                        Slider sliderHorizontal = new Slider(0.0, 10.0, 5.0);
                        Slider sliderVertical = new Slider(0.0, 10.0, 5.0);

                        // Configure tick marks, labels, snapping, and orientation.
                        sliderHorizontal.setShowTickMarks(true);
                        sliderHorizontal.setShowTickLabels(true);
                        sliderHorizontal.setBlockIncrement(2.0);
                        sliderHorizontal.setMajorTickUnit(1);
                        sliderHorizontal.setMinorTickCount(0);
                        sliderHorizontal.setSnapToTicks(true);
                        sliderHorizontal.setOrientation(Orientation.HORIZONTAL);

                        sliderVertical.setShowTickMarks(true);
                        sliderVertical.setShowTickLabels(true);
                        sliderVertical.setBlockIncrement(2.0);
                        sliderVertical.setMajorTickUnit(1);
                        sliderVertical.setMinorTickCount(0);
                        sliderVertical.setSnapToTicks(true);
                        sliderVertical.setOrientation(Orientation.VERTICAL);

                        // Apply the theme slider style.
                        sliderHorizontal.getStyleClass().add("slider");

                        VBox vBox = new VBox(
                                textSimpleSlider,
                                sliderHorizontalS,
                                sliderVerticalS
                        );

                        VBox vBox1 = new VBox(
                                textSlider,
                                sliderHorizontal,
                                sliderVertical
                        );

                        HBox hBox = new HBox(
                                vBox,
                                separator,
                                vBox1,
                                textValueH,
                                textValueV
                        );

                        // Read the current value while the slider is being dragged.
                        sliderHorizontal.valueProperty().addListener(new ChangeListener<Number>() {
                            public void changed(ObservableValue<? extends Number> changed, Number oldValue, Number newValue) {
                                int intValue = newValue.intValue();
                                textValueH.setText("    Horizontal value: " + intValue);
                            }
                        });

                        sliderVertical.valueProperty().addListener(new ChangeListener<Number>() {
                            public void changed(ObservableValue<? extends Number> changed, Number oldValue, Number newValue) {
                                int intValue = newValue.intValue();
                                textValueV.setText("    Vertical value: " + intValue);
                            }
                        });

                        // Update the track fill dynamically as the slider value changes.
                         sliderHorizontal.valueProperty().addListener((obs, oldVal, newVal) -> {
                                       double percentage = (newVal.doubleValue() - sliderHorizontal.getMin())
                                               / (sliderHorizontal.getMax() - sliderHorizontal.getMin()) * 100;
                        
                                       String style = String.format(
                                               Locale.US,
                                               "-fx-background-color: linear-gradient(to right, #2563EB %.2f%%, #E2E8F0 %.2f%%);",
                                               percentage,
                                               percentage
                                       );
                        
                                       Region track = (Region) sliderHorizontal.lookup(".track");
                                       if (track != null) {
                                           track.setStyle(style);
                                       }
                                   });
                        
                                   sliderHorizontalS.valueProperty().addListener((obs, oldVal, newVal) -> {
                                       double percentage = (newVal.doubleValue() - sliderHorizontalS.getMin())
                                               / (sliderHorizontalS.getMax() - sliderHorizontalS.getMin()) * 100;
                        
                                       String style = String.format(
                                               Locale.US,
                                               "-fx-background-color: linear-gradient(to right, #2563EB %.2f%%, #E2E8F0 %.2f%%);",
                                               percentage,
                                               percentage
                                       );
                        
                                       Region track = (Region) sliderHorizontalS.lookup(".track");
                                       if (track != null) {
                                           track.setStyle(style);
                                       }
                                   });

                        FlowPane root = new FlowPane(10, 10, hBox);
                        """);
    }

    private Node createDemo() {
        Text textSimpleSlider = new Text("Basic Slider: ");

        Slider sliderHorizontalS = new Slider();
        Slider sliderVerticalS = new Slider();

        sliderHorizontalS.setBlockIncrement(2.0);
        sliderHorizontalS.setMajorTickUnit(1);
        sliderHorizontalS.setMinorTickCount(0);
        sliderHorizontalS.setSnapToTicks(true);
        sliderHorizontalS.setOrientation(Orientation.HORIZONTAL);

        sliderVerticalS.setBlockIncrement(2.0);
        sliderVerticalS.setMajorTickUnit(1);
        sliderVerticalS.setMinorTickCount(0);
        sliderVerticalS.setSnapToTicks(true);
        sliderVerticalS.setOrientation(Orientation.VERTICAL);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        Text textSlider = new Text("Configured Slider: ");

        Label textValueH = new Label("    Horizontal value: none");
        Label textValueV = new Label("    Vertical value: none");

        Slider sliderHorizontal = new Slider(0.0, 10.0, 5.0);
        Slider sliderVertical = new Slider(0.0, 10.0, 5.0);

        sliderHorizontal.setShowTickMarks(true);
        sliderHorizontal.setShowTickLabels(true);
        sliderHorizontal.setBlockIncrement(2.0);
        sliderHorizontal.setMajorTickUnit(1);
        sliderHorizontal.setMinorTickCount(0);
        sliderHorizontal.setSnapToTicks(true);
        sliderHorizontal.setOrientation(Orientation.HORIZONTAL);

        sliderVertical.setShowTickMarks(true);
        sliderVertical.setShowTickLabels(true);
        sliderVertical.setBlockIncrement(2.0);
        sliderVertical.setMajorTickUnit(1);
        sliderVertical.setMinorTickCount(0);
        sliderVertical.setSnapToTicks(true);
        sliderVertical.setOrientation(Orientation.VERTICAL);

        sliderHorizontal.getStyleClass().add("slider");

        VBox vBox = new VBox(
                textSimpleSlider,
                sliderHorizontalS,
                sliderVerticalS
        );

        VBox vBox1 = new VBox(
                textSlider,
                sliderHorizontal,
                sliderVertical
        );

        HBox hBox = new HBox(
                vBox,
                separator,
                vBox1,
                textValueH,
                textValueV
        );

        sliderHorizontal.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed, Number oldValue, Number newValue) {
                int intValue = newValue.intValue();
                textValueH.setText("    Horizontal value: " + intValue);
            }
        });

        sliderVertical.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed, Number oldValue, Number newValue) {
                int intValue = newValue.intValue();
                textValueV.setText("    Vertical value: " + intValue);
            }
        });



//        sliderHorizontal.valueProperty().addListener((obs, oldVal, newVal) -> {
//            double percentage = (newVal.doubleValue() - sliderHorizontal.getMin()) / (sliderHorizontal.getMax() - sliderHorizontal.getMin()) * 100;
//            String style = String.format("-fx-background-color: linear-gradient(to right, #2563EB %f%%, #E2E8F0 %f%%);", percentage, percentage);
//            sliderHorizontal.lookup(".track").setStyle(style);
//        });

//        sliderHorizontalS.valueProperty().addListener((obs, oldVal, newVal) -> {
//            double percentage = (newVal.doubleValue() - sliderHorizontalS.getMin()) / (sliderHorizontalS.getMax() - sliderHorizontalS.getMin()) * 100;
//            String style = String.format("-fx-background-color: linear-gradient(to right, #2563EB %f%%, #E2E8F0 %f%%);", percentage, percentage);
//            sliderHorizontalS.lookup(".track").setStyle(style);
//        });
        sliderHorizontal.valueProperty().addListener((obs, oldVal, newVal) -> {
            double percentage = (newVal.doubleValue() - sliderHorizontal.getMin())
                    / (sliderHorizontal.getMax() - sliderHorizontal.getMin()) * 100;

            String style = String.format(
                    Locale.US,
                    "-fx-background-color: linear-gradient(to right, #2563EB %.2f%%, #E2E8F0 %.2f%%);",
                    percentage,
                    percentage
            );

            Region track = (Region) sliderHorizontal.lookup(".track");
            if (track != null) {
                track.setStyle(style);
            }
        });

        sliderHorizontalS.valueProperty().addListener((obs, oldVal, newVal) -> {
            double percentage = (newVal.doubleValue() - sliderHorizontalS.getMin())
                    / (sliderHorizontalS.getMax() - sliderHorizontalS.getMin()) * 100;

            String style = String.format(
                    Locale.US,
                    "-fx-background-color: linear-gradient(to right, #2563EB %.2f%%, #E2E8F0 %.2f%%);",
                    percentage,
                    percentage
            );

            Region track = (Region) sliderHorizontalS.lookup(".track");
            if (track != null) {
                track.setStyle(style);
            }
        });

        FlowPane root = new FlowPane(10, 10, hBox);

        return root;
    }
}
