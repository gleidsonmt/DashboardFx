/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.ToggleSwitch;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Gleidson Neve    s da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class MediaViewPresCreator extends TutorialCreator {

    private MediaView mediaView;
    private Media media;
    private MediaPlayer mediaPlayer;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private final Button play = new Button();
    private Slider slider = new Slider();
    private Slider volumeSlider = new Slider();
    private Label playTime = new Label();

    public MediaViewPresCreator(Context context) {
        super(context);
        this    .title("MediaView")
                .text("A Node that provides a view of Media being played by a MediaPlayer.")
                .demonstration(createMedia(), """
                         // Create the media source.
                         String source = getParameters().getRaw().get(0);
                         Media media = new Media(source);
                                            
                         // Create the player and set to play automatically.
                         MediaPlayer mediaPlayer = new MediaPlayer(media);
                         mediaPlayer.setAutoPlay(true);
                                            
                         // Create the view and add it to the Scene.
                         MediaView mediaView = new MediaView(mediaPlayer);
                        """)
                .footer(
                        new Author("OpenJFX",
                                "https://github.com/openjfx/openjfx.github.io",
                                "https://openjfx.io/javadoc/17/javafx.media/javafx/scene/media/MediaView.html")
                )
                .build();

    }

    private VBox createMedia() {
        VBox body = new VBox();

        mediaView = new MediaView();

//        mediaView.setPreserveRatio(true);
//        mediaView.setSmooth(false);

//        mediaView.setViewport(new Rectangle2D(600, 400, 600, 400));

        mediaView.setFitWidth(800);
        mediaView.setFitHeight(400);

        play.setAlignment(Pos.CENTER);

        try {
            media = new Media(context.getResource("media/Cheerleader.mp4").toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        GridPane actions = new GridPane();
        play.getStyleClass().addAll("round", "btn-flat");
        play.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        actions.setMinHeight(30);

        body.setMaxHeight(200);

        body.getChildren().addAll(mediaView, actions);
        createPlayIcon();
        play.setOnMouseClicked(event -> play());

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) ->
                updateValues());

        mediaPlayer.setOnPlaying(() -> {
            if (stopRequested) {
                mediaPlayer.pause();
                stopRequested = false;
            } else {
                createPauseIcon();
            }
        });

        mediaPlayer.setOnPaused(this::createPlayIcon);

        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            mediaPlayer.getMedia().getDuration();
            updateValues();
        });

        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mediaPlayer.setOnEndOfMedia(() -> {
            if (!repeat) {
                createPauseIcon();
                stopRequested = true;
                atEndOfMedia = true;
            }
        });

        slider.valueProperty().addListener(observable -> {
            if (slider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                if(duration != null) {
                    mediaPlayer.seek(duration.multiply(slider.getValue() / 100.0));
                }
                updateValues();

            }
        });

        volumeSlider.setValue(0.5);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (volumeSlider.isValueChanging()) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 50.0);
            }
        });

        actions.setAlignment(Pos.CENTER);
        actions.getChildren().addAll(play, slider, playTime, volumeSlider);

        GridPane.setConstraints(play,  0,0, 1, 1, HPos.LEFT, VPos.CENTER,  Priority.NEVER, Priority.ALWAYS);
        GridPane.setConstraints(slider,  1,0, 1, 1, HPos.LEFT, VPos.CENTER,  Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(playTime,  2,0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
        GridPane.setConstraints(volumeSlider,  3,0, 1, 1, HPos.RIGHT, VPos.CENTER,  Priority.NEVER, Priority.ALWAYS);

//        mediaPlayer.play();
        return body;
    }

    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }

    private @NotNull SVGPath createPlayIcon() {
        SVGPath icon = new SVGPath();
        icon.setContent("M320-203v-560l440 280-440 280Zm60-280Zm0 171 269-171-269-171v342Z");
        Group group = new Group(icon);
        play.setGraphic(group);
        icon.setScaleX(0.03);
        icon.setScaleY(0.03);
        return icon;
    }

    private @NotNull SVGPath createPauseIcon() {
        SVGPath icon = new SVGPath();
        icon.setContent("M300-660v360-360Zm-60 420v-480h480v480H240Zm60-60h360v-360H300v360Z");
        Group group = new Group(icon);
        play.setGraphic(group);
        icon.setScaleX(0.03);
        icon.setScaleY(0.03);
        return icon;
    }

    private void play() {
        updateValues();
        MediaPlayer.Status status = mediaPlayer.getStatus();
        if (status == MediaPlayer.Status.UNKNOWN
                || status == MediaPlayer.Status.HALTED) {
            // don't do anything in these states
            return;
        }

        if (status == MediaPlayer.Status.PAUSED
                || status == MediaPlayer.Status.READY
                || status == MediaPlayer.Status.STOPPED) {
            // rewind the movie if we're sitting at the end
            if (atEndOfMedia) {
                mediaPlayer.seek(mediaPlayer.getStartTime());
                atEndOfMedia = false;
//                icon.setGlyphName("PLAY");
//                    playButton.setGraphic(imageViewPlay);
//                play.setText(">");
                createPlayIcon();
                updateValues();
            }
            mediaPlayer.play();
//            icon.setGlyphName("PAUSE");
//                playButton.setGraphic(imageViewPause);
//            play.setText("||");
            createPauseIcon();
        } else {
            mediaPlayer.pause();
        }
    }

    private void updateValues() {
        if (playTime != null && slider != null && volumeSlider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    slider.setDisable(duration.isUnknown());
                    if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
                        slider.setValue(currentTime.divide(duration).toMillis() * 100.0);

                        double du = duration.toMillis() * 100.0;
                        Duration divide =  currentTime.divide(du);
                        slider.setValue(divide.toMillis());
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                    }
                }
            });
        }
    }

}
