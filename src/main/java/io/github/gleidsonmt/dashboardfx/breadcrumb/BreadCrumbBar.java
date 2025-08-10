package io.github.gleidsonmt.dashboardfx.breadcrumb;

import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2024
 */
@ApiStatus.Experimental
public class BreadCrumbBar extends FlowPane {

    private final ObjectProperty<Module> currentModule;
    private final ObjectProperty<BreadSeparator> separator = new SimpleObjectProperty<>(BreadSeparator.BAR);
//
    public BreadCrumbBar() {
        getStyleClass().add("breadcrumb");
        setAlignment(Pos.CENTER_LEFT);
        this.currentModule = new SimpleObjectProperty<>();
//        this.currentModule.bindBidirectional(currentModule);

        this.currentModule.addListener((observableValue, module, newValue) -> {
            getChildren().clear();
            recur(newValue);
            if (getChildren().size() > 1) {

                if (!separator.get().equals(BreadSeparator.NONE) ){
                    getChildren().remove(getLastChild());
                }
                Hyperlink first = (Hyperlink) getFirstChild();
                first.getStyleClass().add("first");
                Hyperlink last = (Hyperlink) getLastChild();
                last.setStyle(null);
                last.getStyleClass().add("last");
                last.requestFocus();
                last.setVisited(true);
            }

//            if (getChildren().size() == 1) {
//                getChildren().getFirst().getStyleClass().remove("last");
//                if (getChildren().getFirst() instanceof Hyperlink h) {
//                    h.setVisited(false);
//                }
//                getChildren().getFirst().setMouseTransparent(true);
//            }
        });

////        getStylesheets().add(getClass().getResource("breadcrumb.css").toExternalForm());
    }
//
//    public void setVariant(String number) {
//        getStylesheets().add(Objects.requireNonNull(getClass().getResource("variant_" + number + ".css")).toExternalForm());
//        if (number.equals("four")) {
//            setSeparator(BreadSeparator.NONE);
//            this.setHgap(0);
//        }
//    }
//
    public Node getLastChild() {
        return getChildren().getLast();
    }

    public Node getFirstChild() {
        return getChildren().getFirst();
    }

//    public void setSeparator(BreadSeparator separator) {
//        this.separator.set(separator);
//    }
//
    public void recur(Module module) {
        if (module != null) {
//            if (moduleImpl instanceof View view) {
//                getChildren().add(0, createLink(moduleImpl));
//            } else {
//                getChildren().add(0, createText(moduleImpl));
//            }

            getChildren().add(0, createText(module));

            if (separator.get() != BreadSeparator.NONE) {
                getChildren().add(1, createSeparator());
            }
            recur(module.getParent());
        }
    }

    private Hyperlink createLink(Module moduleImpl) {
        Hyperlink text = new Hyperlink(moduleImpl.getName());

        if (moduleImpl.getGraphic() != null) {
            SVGIcon ct = (SVGIcon) moduleImpl.getGraphic();
            text.setGraphic(new SVGIcon(ct.getIcon()));
        }

        text.setOnAction(e -> {
            currentModule.set(moduleImpl);
        });
        text.setStyle("-fx-font-weight: bold;");
        return text;
    }

    private Hyperlink createText(Module moduleImpl) {
        Hyperlink text = new Hyperlink(moduleImpl.getName());
        text.getStyleClass().addAll("h5");

        if (moduleImpl instanceof View) {
            text.setOnMouseClicked(e -> {
                currentModule.set(moduleImpl);
            });
        } else {
            text.setMouseTransparent(true);
        }

//        text.setStyle("-fx-text-fill: -text-color;");

        if (moduleImpl.getGraphic() != null) {
            SVGIcon ct = (SVGIcon) moduleImpl.getGraphic();
            if ((boolean) moduleImpl.getGraphic().getUserData()) {
//                text.setGraphic(new SVGIcon(ct.getIcon(), (boolean) moduleImpl.getGraphic().getUserData()));
                text.setGraphic(new SVGIcon(ct.getIcon()));
            } else {
                text.setGraphic(new SVGIcon(ct.getIcon()));
            }

        }

        text.setAlignment(Pos.CENTER_LEFT);

        return text;
    }

    private Label createSeparator() {

        Label label = new Label();
        switch (separator.get()) {
            case BAR -> label.setText("/");
            case DOT -> {
                Circle circle = new Circle();
                circle.getStyleClass().add("separator");
                label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                circle.setRadius(2);
                label.setGraphic(circle);
            }
            case ARROW -> {
                SVGIcon ct = new SVGIcon(Icon.CHEVRON_RIGHT);
                label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                label.setGraphic(ct);
            }

        }
        return label;
    }

    public Module getCurrentModule() {
        return currentModule.get();
    }

    public ObjectProperty<Module> currentModuleProperty() {
        return currentModule;
    }

    public void setCurrentModule(Module currentModuleImpl) {
        this.currentModule.set(currentModuleImpl);
    }
}
