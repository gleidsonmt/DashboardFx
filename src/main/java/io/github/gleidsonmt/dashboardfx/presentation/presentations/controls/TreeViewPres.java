package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2025
 */
public class TreeViewPres extends CustomizablePresentation {

    public TreeViewPres() {
        super("TreeView");
    }

    public Tutorial create() {
        return   new Tutorial()
                        .h3("Tree View")
                        .text("""
                                The TreeView control provides a view on to a tree root (of type TreeItem).
                                By using a TreeView, it is possible to drill down into the children of a TreeItem, recursively until a TreeItem has no children (that is, it is a leaf node in the tree). 
                                To facilitate this, unlike controls like ListView, in TreeView it is necessary to only specify the root node.""")
                        .legend("javafx.scene.control.TreeView")
                        .h3("Default", "Tree View")
                        .demo(createDemo())
                        .code("""
                                TreeView<String> treeView = new TreeView<>();
                                TreeItem<String> root = new TreeItem<>("Root");
                                TreeItem<String> leafOne = new TreeItem<>("Leaf One");
                                TreeItem<String> leafTwo = new TreeItem<>("Leaf Two");
                        
                                root.getChildren().addAll(leafOne, leafTwo);
                                treeView.setRoot(root);
                                """)
                        .h3("Check List", "Tree View")
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
                                """);
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

    private TreeView<String> createDemo() {
        TreeView<String> treeView = new TreeView<>();
        TreeItem<String> root = new TreeItem<>("Root");
        TreeItem<String> leafOne = new TreeItem<>("Leaf One");
        TreeItem<String> leafTwo = new TreeItem<>("Leaf Two");

        root.getChildren().addAll(leafOne, leafTwo);
        treeView.setRoot(root);
        return treeView;
    }


}
