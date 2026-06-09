package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
public class ListViewPres extends CustomizablePresentation {

    public ListViewPres() {
        super("ListView");
    }

    public Presentation create() {
        return new Tutorial()
                .h3("ListView")
                .text("""
                        A ListView displays a horizontal or vertical list of items from which the user may select, or with which the user may interact.
                        A ListView is able to have its generic type set to represent the type of data in the backing model.
                        Doing this has the benefit of making various methods in the ListView, as well as the supporting classes (mentioned below), type-safe. In addition,
                        making use of the generic type supports substantially simplified development of applications making use of ListView, as all modern IDEs are able to auto-complete far more successfully with the additional type information.
                        """)
                .legend("javafx.scene.control.ListView")
                .demo(createDemo())

                .h4("Install")
                .code(TutorialUtils.installExample(Css.LIST_VIEW) + """
                        
                        // Building
                        ListView<String> listView = new ListView<>();
                        listView.setItems(FXCollections.observableArrayList(
                              "Item 01", "Item 02", "Item 03", "Item 04"
                        ));
                        """)

//                .code("""
//                        ListView<String> listView = new ListView<>();
//                        listView.setItems(FXCollections.observableArrayList(
//                              "Item 01", "Item 02", "Item 03", "Item 04"
//                        ));
//                        """)
                ;
    }

    private Node createDemo() {
        ListView<String> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(
                "Item 01", "Item 02", "Item 03", "Item 04"
        ));
        return listView;
    }
}
