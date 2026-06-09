package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class TreeViewExample extends StackPane {

    public TreeViewExample() {
        getChildren().setAll(
                new Tutorial()
                        .h3("TreeView")
                        .text("Button style examples.")
                        .h3("Check List")
                        .demo(createTreeCheck())
                        .code("""
                                CheckBoxTreeItem<String> jonathanGiles = new CheckBoxTreeItem<>("Jonathan");
                                jonathanGiles.setIndeterminate(true);
                                CheckBoxTreeItem<String> juliaGiles = new CheckBoxTreeItem<>("Julia");
                                CheckBoxTreeItem<String> mattGiles = new CheckBoxTreeItem<>("Matt");
                                CheckBoxTreeItem<String> sueGiles = new CheckBoxTreeItem<>("Sue");
                                CheckBoxTreeItem<String> ianGiles = new CheckBoxTreeItem<>("Ian");
                        
                                CheckBoxTreeItem<String> gilesFamily = new CheckBoxTreeItem<>("Giles Family");
                                gilesFamily.setExpanded(true);
                                gilesFamily.getChildren().addAll(jonathanGiles, juliaGiles, mattGiles, sueGiles, ianGiles);
                        
                                // create the treeView
                                final TreeView<String> treeView = new TreeView<>();
                                treeView.setRoot(gilesFamily);
                        
                                // set the cell factory
                                treeView.setCellFactory(CheckBoxTreeCell.forTreeView());
                                """)
                        .build()
                        .getRoot()
        );
    }

    private TreeView<String> createTreeCheck() {
        CheckBoxTreeItem<String> jonathanGiles = new CheckBoxTreeItem<>("Jonathan");
        jonathanGiles.setIndeterminate(true);
        CheckBoxTreeItem<String> juliaGiles = new CheckBoxTreeItem<>("Julia");
        CheckBoxTreeItem<String> mattGiles = new CheckBoxTreeItem<>("Matt");
        CheckBoxTreeItem<String> sueGiles = new CheckBoxTreeItem<>("Sue");
        CheckBoxTreeItem<String> ianGiles = new CheckBoxTreeItem<>("Ian");

        CheckBoxTreeItem<String> gilesFamily = new CheckBoxTreeItem<>("Giles Family");
        gilesFamily.setExpanded(true);
        gilesFamily.getChildren().addAll(jonathanGiles, juliaGiles, mattGiles, sueGiles, ianGiles);

        // create the treeView
        final TreeView<String> treeView = new TreeView<>();
        treeView.setRoot(gilesFamily);

        // set the cell factory
        treeView.setCellFactory(CheckBoxTreeCell.forTreeView());
        return treeView;
    }
}
