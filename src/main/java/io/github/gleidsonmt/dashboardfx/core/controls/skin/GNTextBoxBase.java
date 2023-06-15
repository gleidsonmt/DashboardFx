/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.controls.skin;

import io.github.gleidsonmt.dashboardfx.core.controls.FieldType;
import io.github.gleidsonmt.dashboardfx.core.controls.GNTextBox;
import io.github.gleidsonmt.dashboardfx.core.controls.text_box.Editor;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.List;
import java.util.Objects;

/**
 * Base class to construtc input field with a editor.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/09/2022
 */
@DefaultProperty("children")
public class GNTextBoxBase extends Control {

    private final ObjectProperty<Editor> editor = new SimpleObjectProperty<>();
    private final ObjectProperty<Node> leftNode = new SimpleObjectProperty<>();
    private final ObjectProperty<Node> rightNode = new SimpleObjectProperty<>();

    private static final StyleablePropertyFactory<GNTextBoxBase> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableObjectProperty<FieldType> fieldType =
            new SimpleStyleableObjectProperty<>(FIELD_TYPE, this, "fieldType", FieldType.OUTLINED);

    private final StyleableObjectProperty<Boolean> animated =
            new SimpleStyleableObjectProperty<>(ANIMATED, this, "animated", false);

    private static final CssMetaData<GNTextBoxBase, FieldType> FIELD_TYPE =
            FACTORY.createEnumCssMetaData(
                    FieldType.class, "-gn-field-type",
                    g -> g.fieldType, FieldType.OUTLINED);

    private static final CssMetaData<GNTextBoxBase, Boolean> ANIMATED =
            FACTORY.createBooleanCssMetaData(
                    "-gn-animated",
                    g -> g.animated, true);


    private static final PseudoClass PSEUDO_CLASS_FILLED = PseudoClass.getPseudoClass("filled");
    private static final PseudoClass PSEUDO_CLASS_ANIMATED = PseudoClass.getPseudoClass("animated");

    private final ChangeListener<Boolean> retainFocus = (observable, oldValue, newValue) -> setFocused(newValue);

    private final BooleanProperty maskText = new SimpleBooleanProperty(true); // used for password text

    private final StringProperty text = new SimpleStringProperty();
    private final StringProperty promptText = new SimpleStringProperty();

    protected GNTextBoxBase() {
        this(false);
    }

    protected GNTextBoxBase(boolean mask) {

        maskText.set(mask);
        getStyleClass().add("gn-text-box-base");

        editorProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                newValue.focusedProperty().addListener(retainFocus);
                newValue.maskTextProperty().bindBidirectional(maskText);
                newValue.textProperty().bindBidirectional(text);
                newValue.promptTextProperty().bindBidirectional(promptText);

                if (oldValue != null)
                    oldValue.focusedProperty().removeListener(retainFocus);
            }

        });

    }

    @Override
    protected Skin<?> createDefaultSkin() {

//        if (animated.get()) {
//            editor.set(new FloatEditor());
//            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATED, true);
//        } else {
//            editor.set(new Editor());
//            pseudoClassStateChanged(PSEUDO_CLASS_ANIMATED, false);
//        }

        if (fieldType.get() == FieldType.FILLED) {
            pseudoClassStateChanged(PSEUDO_CLASS_FILLED, true);
            if (animated.get())
                return new GNTextBoxBaseFilledSkin(this);
            else
                return new GNTextBoxBaseSkin(this);
        } else {
            pseudoClassStateChanged(PSEUDO_CLASS_FILLED, false);
            return new GNTextBoxBaseSkin(this);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(GNTextBox.class.getResource("/io.github.gleidsonmt.gncontrols/agents/text_box_base.css")).toExternalForm();
//    }

    public ObservableValue<FieldType> fieldTypeProperty() {
        return fieldType;
    }

    public Editor getEditor() {
        return editor.get();
    }

    protected ObjectProperty<Editor> editorProperty() {
        return editor;
    }

    protected void setEditor(Editor editor) {
        this.editor.set(editor);
    }

    public Node getLeftNode() {
        return leftNode.get();
    }

    public ObjectProperty<Node> leftNodeProperty() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode.set(leftNode);
    }

    public Node getRightNode() {
        return rightNode.get();
    }

    public ObjectProperty<Node> rightNodeProperty() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode.set(rightNode);
    }

    public FieldType getFieldType() {
        return fieldType.get();
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType.set(fieldType);
    }

    public boolean isAnimated() {
        return animated.get();
    }

    public ObservableValue<Boolean> animatedProperty() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated.set(animated);
    }

    public boolean isMaskText() {
        return maskText.get();
    }

    public BooleanProperty maskTextProperty() {
        return maskText;
    }

    protected void setMaskText(boolean maskText) {
        this.maskText.set(maskText);
    }

    public final String getText() {
        return text.get();
    }

    public final void setText(String value) {
        text.set(value);
    }

    public final StringProperty textProperty() {
        return text;
    }

    public String getPromptText() {
        return promptText.get();
    }

    public StringProperty promptTextProperty() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText.set(promptText);
    }

}
