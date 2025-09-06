package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.glad.base.Module;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class ModuleSeparator implements Module {

    private String text;
    private Node icon;

    public ModuleSeparator(Node icon, String text) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Node getIcon() {
        return icon;
    }

    @Override
    public ObservableList<Module> getModules() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Node getGraphic() {
        return null;
    }

    @Override
    public void setParent(Module parent) {

    }

    @Override
    public Module getParent() {
        return null;
    }

    @Override
    public void setContainer(Pane container) {

    }

    @Override
    public Pane getContainer() {
        return null;
    }

    @Override
    public boolean isAnimated() {
        return false;
    }
}
