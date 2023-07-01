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

package io.github.gleidsonmt.dashboardfx.core.picture_selector;

import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.picture_selector.PlusSlider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/06/2023
 */
public class PictureSelectorController extends ActionView  {

    @FXML private Circle top_left;
    @FXML private Circle bottom_left;
    @FXML private GNButton btnLockInTop;
    @FXML private SVGPath path_lup;

    @FXML private StackPane root;;
    @FXML private GridPane body;
    @FXML private AnchorPane screenSelector;
    @FXML private AnchorPane boxSelector;
    @FXML private ImageView imageView;
    @FXML private VBox wrapper;
    @FXML private StackPane boxContainer;

    @FXML private Circle miniView;
    @FXML private Circle circleView;
    @FXML private Rectangle rectView;

    private static final int MIN_PIXELS = 10;

    private double initX;
    private double initY;

    private double newX;
    private double newY;

    private double maxW;
    private double maxH;

    private double deltaX;
    private double deltaY;

    // Port for rectangle view
    private double viewWidth = 600;
    private double viewHeight = 400;

    private final ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

    private PlusSlider plusSlider = new PlusSlider();


    @FXML
    private void scrollImage(@NotNull ScrollEvent event) {

        double delta = event.getDeltaY() * -1; // * -1 invert scroll rolling

        Rectangle2D viewport = imageView.getViewport();

        double width = imageView.getImage().getWidth();
        double height = imageView.getImage().getHeight();


//        double scale = clamp(Math.pow(1.01, delta),
        double scale = clamp(Math.pow(1.01, delta),

                // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
                Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),

                // don't scale so that we're bigger than image dimensions:
                Math.max(width / viewport.getWidth(), height / viewport.getHeight())

        );


        Point2D mouse = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));

        double newWidth = viewport.getWidth() * scale;
        double newHeight = viewport.getHeight() * scale;

        double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                0, width - newWidth);

        double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                0, height - newHeight);


        if (!screenSelector.getChildren().contains(plusSlider)) {

            screenSelector.getChildren().add(plusSlider);
            plusSlider.setMax(10);
            plusSlider.setMin(-imageView.getImage().getHeight());
            plusSlider.setBlockIncrement(-40);

        }

//
        int cursorDiff = 20;
        int plusHeight = 45;

        double maxX = screenSelector.getWidth() - cursorDiff;
        double maxY = screenSelector.getHeight() - plusHeight;

        var left = event.getX() > maxX;
        var bottom = event.getY() > maxY;

        // position and limit borders
        plusSlider.setLayoutX( left ? event.getX() - cursorDiff : event.getX() + cursorDiff );
        plusSlider.setLayoutY(bottom ? event.getY() - plusHeight : event.getY() + cursorDiff);

        plusSlider.setValue(newHeight * -1);

        // don't pass the bounds limits either
        if (newHeight > imageView.getImage().getHeight()) return;
        if (newWidth > imageView.getImage().getWidth()) return;

        imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
    }

    /**
     * Gets the initial view port position.
     * @param event Mouse event for get x and y pos.
     */
    @FXML
    private void getImageCord(@NotNull MouseEvent event) {

        if (event.getTarget() instanceof Circle) return;

        // Get the mouse position in image when is clicked.
        Point2D mousePress = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));

        // Set the initial point 2D
        mouseDown.set(mousePress);

    }

    /**
     * Drag image
     * @param event Mouse event for get x and y pos.
     */
    @FXML
    private void dragImage(@NotNull MouseEvent event) {

        if (event.getTarget() instanceof Circle) return;

        if (!event.isControlDown()) return;

        // Get new cordinates from the event.
        Point2D dragPoint = imageViewToImage(imageView, new Point2D(event.getX(), event.getY()));

        // Move the image for the next cordinates
        moveViewPort(imageView, dragPoint.subtract(mouseDown.get()));

        // Register actual coordinates
        mouseDown.set(imageViewToImage(imageView, new Point2D(event.getX(), event.getY())));

    }

    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    private void moveViewPort(@NotNull ImageView imageView, @NotNull Point2D delta) {

        // Get the view port actual
        Rectangle2D viewport = imageView.getViewport();

        // Get th e properties from iaveView
        double width = imageView.getImage().getWidth() ;
        double height = imageView.getImage().getHeight() ;

        // Get max positions
        double maxX = width - viewport.getWidth();
        double maxY = height - viewport.getHeight();

        // Move
        double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

        if (minY < 0) return;

        imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));

    }



    // convert mouse coordinates in the imageView to coordinates in the actual image:
    private @NotNull Point2D imageViewToImage(@NotNull ImageView imageView, @NotNull Point2D imageViewCoordinates) {

        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInParent().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInParent().getHeight();

        Rectangle2D viewport = imageView.getViewport();

        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(),
                viewport.getMinY() + yProportion * viewport.getHeight());

    }

    private double clamp(double value, double min, double max) {

        if (value < min) return min;

        return Math.min(value, max);

    }

    public void setImage(Image image) {

        imageView.setImage(image);
        centerImage(image);

    }

    @FXML
    private void lockInTop() {
        AnchorPane.setTopAnchor(boxSelector, 0D);
        btnLockInTop.setVisible(false);
    }

    @FXML
    private void closePopup() {
//        context.getDecorator().getRoot().getWrapper().getPopup().close();
    }

    @FXML
    private void draggBoxSelector(@NotNull MouseEvent event) {

//        if (event.getTarget() instanceof Path) return;
////
//        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) {
//            return;
//        }
////
//        if (event.isStillSincePress()) {
//            return;
//        }
//
//        AnchorPane.clearConstraints(boxSelector);
//
//        newX = event.getScreenX();
//        newY = event.getScreenY();
//
//        deltaX = newX - initX;
//        deltaY = newY - initY;
//
//        Bounds bounds = screenSelector.getLayoutBounds();
//
//        double padTop = boxSelector.getLocalToParentTransform().getTy() ;
//        double padLef = boxSelector.getLocalToParentTransform().getTx() ;
//
////        boolean maxX = (deltaX + boxSelector.getWidth()) < 401;
////        boolean maxY = (deltaY + boxSelector.getHeight()) < 401 ;
//        boolean maxX = (deltaX + boxSelector.getWidth()) < (screenSelector.getWidth() ) + (padLef + 2);
//        boolean maxY = (deltaY + boxSelector.getHeight()) < (screenSelector.getHeight() ) + (padTop + 2);
//
//        if (deltaX > (padLef-2) && maxX)
//            boxSelector.setLayoutX( deltaX);
//
//        if (deltaY > (padTop-2) && maxY)
//            boxSelector.setLayoutY( deltaY );

    }


    /**
     *  Get the initial cordinates and anchor for drag event.
     * @param event Mouse event for this event
     */
    @FXML
    private void fixedOnBottomLeft(MouseEvent event) {

//        btnLockInTop.setVisible(true);

        initY = clampPointerBottomY(event);
        initX = clampPointerForLeftX(event);

        clearConstraints(boxSelector);

        double _minX =  (boxSelector.getLocalToParentTransform().getTx()  ) ;
        double _minY =  (boxSelector.getLocalToParentTransform().getTy()  ) ;

        double _maxX = viewWidth - (_minX + boxSelector.getWidth());

        AnchorPane.setRightAnchor( boxSelector, _maxX );
        AnchorPane.setTopAnchor( boxSelector, _minY );

    }

    /**
     * Event drag from bottom left.
     * This event starts on the circle at the bottom.
     * @param event Mouse event for this event
     */
    @FXML
    private void resizeFromBottomLeft(@NotNull MouseEvent event) {

        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) return; // if out of bounds and not the primary button(left)
        if (event.isStillSincePress()) return; // if the mouse doesn't continuos pressed

        // get the new x positions
        newX = event.getSceneX();
        newY = event.getSceneY();

        // get the difference from initial clicked to dragg
        deltaX = newX - initX;
        deltaY = newY - initY;

        // get the bounds for the box limit
        double maxY = (screenSelector.getLocalToSceneTransform().getTy() + screenSelector.getHeight()) -2;
        double maxX = (screenSelector.getLocalToSceneTransform().getTx() -2);

        // don't leave from the limit
        if ( newY < maxY )  setHeight(boxSelector.getPrefHeight() + deltaY);
        if ( newX > maxX )  setWidth (boxSelector.getPrefWidth()  - deltaX);

    }

    @FXML
    private void fixedOnBottomRight(MouseEvent event) {

//        btnLockInTop.setVisible(true);

        initY = clampPointerBottomY(event);
        initX = clampPointerForRightX(event);

        clearConstraints(boxSelector);
//
        double _minX =  (boxSelector.getLocalToParentTransform().getTx()  ) ;
        double _minY =  (boxSelector.getLocalToParentTransform().getTy()  ) ;

        AnchorPane.setTopAnchor( boxSelector, _minY );
        AnchorPane.setLeftAnchor( boxSelector, _minX );

    }

    @FXML
    private void resizeFromBottomRight(@NotNull MouseEvent event) {

        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) return; // if out of bounds and not the primary button(left)
        if (event.isStillSincePress()) return; // if the mouse doesn't continuos pressed

        // get the new x positions
        newX = event.getSceneX();
        newY = event.getSceneY();

        // get the difference from initial clicked to dragg
        deltaX = newX - initX;
        deltaY = newY - initY;

        double maxX = (screenSelector.getLocalToSceneTransform().getTx() + screenSelector.getWidth())  -2; // 2 is the border width
        double maxY = (screenSelector.getLocalToSceneTransform().getTy() + screenSelector.getHeight()) -2;

        // don't out the limits
        if (newX < maxX) setWidth(boxSelector.getPrefWidth() + deltaX);
        if (newY < maxY) setHeight(boxSelector.getPrefHeight() + deltaY);

    }

    @FXML
    private void fixedOnTopLeft(MouseEvent event) {

        // Gets the initial cordinates and clamp with the size of circle
        initY = clampPointerTopY(event);
        initX = clampPointerForLeftX(event);

        // Remove old anchors
        clearConstraints(boxSelector);

        // Get the min x and y postion (Relative to screenSelector)
        double _minX =  (boxSelector.getLocalToParentTransform().getTx() ) ;
        double _minY =  (boxSelector.getLocalToParentTransform().getTy()  ) ;

        // Get the maxX and maxY position in the parent (Relative to screenSelector)
        double _maxX = viewWidth - (_minX + boxSelector.getWidth());
        double _maxY = screenSelector.getHeight() - (_minY + boxSelector.getHeight());

        // Anchor positions (Relative to screenSelector)
        AnchorPane.setRightAnchor(boxSelector, _maxX );
        AnchorPane.setBottomAnchor(boxSelector, _maxY );

    }

    @FXML
    private void resizeFromTopLeft(@NotNull MouseEvent event) {

        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) return; // if out of bounds and not the primary button(left)
        if (event.isStillSincePress()) return; // if the mouse doesn't continuos pressed

        // get the new x positions
        newX = event.getSceneX();
        newY = event.getSceneY();

        // get the difference from initial clicked to dragg
        deltaX = newX - initX;
        deltaY = newY - initY;

        // get the bounds for the box
        double maxX = (screenSelector.getLocalToSceneTransform().getTx() -2);
        double maxY = (screenSelector.getLocalToSceneTransform().getTy() -2);

        // don't leave from the limit
        if (newY > maxY) setHeight(boxSelector.getPrefHeight()  - deltaY);
        if (newX > maxX) setWidth(boxSelector.getPrefWidth()    - deltaX);
    }

    @FXML
    private void fixedOnTopRight(MouseEvent event) {

        // Gets the initial cordinates and clamp with the size of circle
        initY = clampPointerTopY(event);
        initX = clampPointerForRightX(event);

        // Remove old anchors
        clearConstraints(boxSelector);

        // Get the min x and y postion (Relative to screenSelector)
        double _minX =  (boxSelector.getLocalToParentTransform().getTx() ) ;
        double _minY =  (boxSelector.getLocalToParentTransform().getTy()  ) ;

        // Get the maxX and maxY position in the parent (Relative to screenSelector)
        double _maxX = viewWidth - (_minX + boxSelector.getWidth());
        double _maxY = screenSelector.getHeight() - (_minY + boxSelector.getHeight());

        // Anchor positions (Relative to screenSelector)
        AnchorPane.setLeftAnchor(boxSelector, _minX );
        AnchorPane.setBottomAnchor(boxSelector, _maxY );


    }
    @FXML
    private void resizeFromTopRight(@NotNull MouseEvent event) {

        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) return; // if out of bounds and not the primary button(left)
        if (event.isStillSincePress()) return; // if the mouse doesn't continuos pressed

        // get the new x positions
        newX = event.getSceneX();
        newY = event.getSceneY();

        // get the difference from initial clicked to dragg
        deltaX = newX - initX;
        deltaY = newY - initY;

        // get the bounds for the box
        double maxX = (screenSelector.getLocalToSceneTransform().getTx() + screenSelector.getWidth()) -2;
        double maxY = (screenSelector.getLocalToSceneTransform().getTy() -2);

        if (newX < maxX) setWidth(boxSelector.getPrefWidth() + deltaX);
        if (newY > maxY) setHeight(boxSelector.getPrefHeight() - deltaY);

    }


    @FXML
    private void getBoxCordinates(@NotNull MouseEvent event) {

        if (event.getTarget() instanceof Circle) return; // dont use the path or update

        if (event.getTarget() instanceof Path) return; // dont use the path or update

        initX = (event.getScreenX() ) - (boxSelector.getLocalToParentTransform().getTx());
        initY = (event.getScreenY() ) - (boxSelector.getLocalToParentTransform().getTy());
    }


    @FXML
    private void moveBox(@NotNull MouseEvent event) {

        if (event.getTarget() instanceof Circle) return;

        if (!event.isPrimaryButtonDown() || (initX == -1 && initY == -1)) return;

        if (event.isStillSincePress()) return;

        clearConstraints(boxSelector);

        newX = event.getScreenX();
        newY = event.getScreenY();

        deltaX = newX - initX;
        deltaY = newY - initY;

        // Min
        double minX = imageView.getLocalToParentTransform().getTx();
        double minY = imageView.getLocalToParentTransform().getTy();

        double maxY = deltaY + boxSelector.getHeight();
        double maxX = deltaX + boxSelector.getWidth();


        // Delimiter the bounds
        if (deltaX > (minX -2) && maxX <screenSelector.getWidth() + 1)
            boxSelector.setLayoutX( deltaX);

        if (deltaY > (minY -2) && maxY < screenSelector.getHeight() + 1)
            boxSelector.setLayoutY( deltaY );

    }


    /***************************************************************
     *
     *              Utils
     *
     **************************************************************/

    private void clearConstraints(Node node) {
        AnchorPane.clearConstraints(node);
    }

    private double clampPointerForLeftX(@NotNull MouseEvent event) {
        double min = boxSelector.getLocalToSceneTransform().getTx();
        double _count = event.getSceneX() - min;
        double _sum = event.getSceneX() - _count;
        return  _sum - 1;
    }

    private double clampPointerForRightX(@NotNull MouseEvent event) {
        double min = (boxSelector.getLocalToSceneTransform().getTx() + boxSelector.getWidth()) -2;
        double _count = event.getSceneX() - min;
        double _sum = event.getSceneX() - _count;
        return  _sum - 1;
    }

    private double clampPointerTopY(@NotNull MouseEvent event) {
        double min = boxSelector.getLocalToSceneTransform().getTy();
        double _count = event.getSceneY() - min;
        double _sum = event.getSceneY() - _count;
        return _sum -1;
    }

    private double clampPointerBottomY(@NotNull MouseEvent event) {
        double min = (boxSelector.getLocalToSceneTransform().getTy() + boxSelector.getHeight()) -2; // two border width
        double _count = event.getSceneY() - min;
        double _sum = event.getSceneY() - _count;
        return _sum -1;
    }

    private void setWidth(double width) {
        if (width >= boxSelector.getMinWidth()) {
            boxSelector.setPrefWidth(width);
            initX = newX;
        }
    }

    private void setHeight(double height) {
        if (height >= boxSelector.getMinHeight()) {
            boxSelector.setPrefHeight(height);
            initY = newY;
        }
    }

    /**
     * Center image in the center of the box and set the max view port.
     * @param image The image for get sizes.
     */
    private void centerImage(@NotNull Image image) {

        // Get the actual view port
        viewWidth = Math.min(image.getWidth(), (context.stage().getWidth() /2));
        viewHeight = Math.min(image.getHeight(), (context.stage().getHeight() / 2));


        // Sets the view port
        imageView.setViewport(new Rectangle2D(0,0, viewWidth, viewHeight));


        // Sets the image view size
        imageView.setFitWidth(viewWidth);
        imageView.setFitHeight(viewHeight);

    }

    /**
     * The image for others scenes
     * @return The image in selector.
     */
    @FXML
    public WritableImage getImage() {

        SnapshotParameters params = new SnapshotParameters();

        params.setFill(Color.TRANSPARENT);

        double _minX = boxSelector.getLocalToParentTransform().getTx();
        double _minY = boxSelector.getLocalToParentTransform().getTy();
        double _width = boxSelector.getPrefWidth();
        double _height = boxSelector.getPrefHeight();

        params.setViewport(new Rectangle2D(_minX, _minY, _width, _height));

        wrapper.setEffect(null);
        WritableImage wImage = new WritableImage((int) _width, (int) _height);

        WritableImage image = imageView.snapshot(params, wImage);
        wrapper.setEffect(new ColorAdjust(0,0,-0.50 ,0));

        File directory = new File(System.getProperty("user.dir")
                + System.getProperty("file.separator")+ "zoom3.png");

        try {

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", directory);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Replace the cursors if you don't have event dragged or scrolled
        boxSelector.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> boxSelector.setCursor(Cursor.MOVE));
        boxSelector.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> boxSelector.setCursor(Cursor.DEFAULT));
        screenSelector.addEventFilter(MouseEvent.MOUSE_MOVED, event -> screenSelector.getChildren().remove(plusSlider));
    }



}
