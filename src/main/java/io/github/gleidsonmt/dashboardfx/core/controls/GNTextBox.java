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

package io.github.gleidsonmt.dashboardfx.core.controls;

import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.controls.skin.GNTextBoxBase;
import io.github.gleidsonmt.dashboardfx.core.controls.skin.GNTextBoxSkin;
import javafx.beans.DefaultProperty;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.Node;
import javafx.scene.control.Skin;

import java.util.List;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2022
 */
@DefaultProperty("control")
public class GNTextBox extends GNTextBoxBase {

    private final StyleableProperty<Boolean> action =
            new SimpleStyleableBooleanProperty(ACTION, this, "action");

    private final StyleableObjectProperty<Icons> icon =
            new SimpleStyleableObjectProperty<>(ICON, this, "icon", Icons.NONE);

    private static final StyleablePropertyFactory<GNTextBox> FACTORY =
            new StyleablePropertyFactory<>(GNTextBoxBase.getClassCssMetaData());

    private final StyleableIntegerProperty count =
            new SimpleStyleableIntegerProperty(COUNT, this, "count", 0);

    private static final CssMetaData<GNTextBox, Number> COUNT =
            FACTORY.createSizeCssMetaData( "-gn-count", g -> g.count);

    private final StyleableBooleanProperty visibleHelperText =
            new SimpleStyleableBooleanProperty(VISIBLE_HELPER_TEXT, this, "helperText", false);

    private static final CssMetaData<GNTextBox, Boolean> VISIBLE_HELPER_TEXT =
            FACTORY.createBooleanCssMetaData("-gn-helper-text", f -> f.visibleHelperText, false);

    private static final CssMetaData<GNTextBox, Boolean> ACTION =
            FACTORY.createBooleanCssMetaData("-gn-action",
                    g -> g.action,true, true);

    private static final CssMetaData<GNTextBox, Icons> ICON =
            FACTORY.createEnumCssMetaData(Icons.class, "-gn-icon",
                    g -> g.icon, Icons.NONE, true);

    private static final PseudoClass PSEUDO_CLASS_ERROR = PseudoClass.getPseudoClass("error");

    private final ObjectProperty<Node>  actionIcon  = new SimpleObjectProperty<>();

    private final StringProperty        helperText  = new SimpleStringProperty();
    private final BooleanProperty       valid       = new SimpleBooleanProperty();

    public GNTextBox() {
        this(null, null, false, false);
    }

    public GNTextBox(String text) {
        this(text, Icons.NONE, false, false);
    }

    public GNTextBox(Icons _icon) {
        this(null, _icon, false, false);
    }

    public GNTextBox(boolean animated) {
        this(null, null, animated, false);
    }

    public GNTextBox(String text, boolean animated) {
        this(text, null, animated, false);
    }

    public GNTextBox(String text, Icons _icon) {
        this(text, _icon, false, false);
    }

    public GNTextBox(Icons _icon, boolean animated) {
        this(null, _icon, animated, false);
    }

    public GNTextBox(String text, Icons _icon, boolean animated, boolean mask) {

        setMaskText(mask);
        getStyleClass().add("gn-text-box");
        setText(text);
        setIcon(_icon);
        setAnimated(animated);

        valid.addListener((observable, oldValue, newValue) -> {
            pseudoClassStateChanged(PSEUDO_CLASS_ERROR, !newValue);
        });

//        GNIconButton action = new GNIconButton(Icons.CLEAR);
//        action.getStyleClass().add("action-button");
//        setActionIcon(action);


    }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNTextBoxSkin(this);
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(GNTextBox.class.getResource("/io.github.gleidsonmt.gncontrols/agents/text_box.css")).toExternalForm();
//    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }


    public ObservableValue<Boolean> actionProperty() {
        return (ObservableValue<Boolean>) action;
    }

    public void setAction(boolean value) {
        action.setValue(value);
    }

    public boolean isAction() {
        return action.getValue();
    }

    protected void setActionIcon(Node actionIcon) {
        actionIcon.setFocusTraversable(false);
        this.actionIcon.set(actionIcon);
    }

    public void setIcon(Icons icon) {
        this.icon.set(icon);
    }

    public StyleableObjectProperty<Icons> iconProperty() {
        return icon;
    }

    public Icons getIcon() {
        return icon.get();
    }

    public int getCount() {
        return count.get();
    }

    public StyleableIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public boolean isVisibleHelperText() {
        return visibleHelperText.get();
    }

    public StyleableBooleanProperty visibleHelperTextProperty() {
        return visibleHelperText;
    }

    public void setVisibleHelperText(boolean visibleHelperText) {
        this.visibleHelperText.set(visibleHelperText);
    }

    public String getHelperText() {
        return helperText.get();
    }

    public StringProperty helperTextProperty() {
        return helperText;
    }

    public void setHelperText(String helperText) {
        this.helperText.set(helperText);
    }

    public Node getActionIcon() {
        return actionIcon.get();
    }

    public ObjectProperty<Node> actionIconProperty() {
        return actionIcon;
    }

    public boolean isValid() {
        return valid.get();
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }

    public void validate() {

        pseudoClassStateChanged(PSEUDO_CLASS_ERROR, !this.isValid());

        ((GNTextBoxSkin) getSkin()).validate();
//        setVisibleHelperText(!this.isValid());
    }


}
