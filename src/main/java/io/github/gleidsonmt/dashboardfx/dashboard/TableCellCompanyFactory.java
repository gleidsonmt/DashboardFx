package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Hardware;
import io.github.gleidsonmt.dashboardfx.model.Company;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import static javafx.scene.layout.GridPane.REMAINING;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
public class TableCellCompanyFactory implements Callback<TableColumn<Hardware, String>, TableCell<Hardware, String>> {
    @Override
    public TableCell<Hardware, String> call(TableColumn<Hardware, String> activityCompanyTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                if (item != null) {
//                    GridPane grid = new GridPane();
//                    grid.setHgap(15);
//                    Text title = new Text(item.getName());
//                    title.getStyleClass().addAll("h5", "bold");
//                    Text legend = new Text(item.getAddress());
//                    legend.getStyleClass().addAll("h5");
//                    AvatarView avatar = new AvatarView(item.getAvatar(), 0);
//
//                    avatar.setWidth(25);
//                    avatar.setHeight(25);
//
//                    grid.add(avatar, 0,0);
//                    grid.add(title, 1,0);
//                    grid.add(legend, 1,1);
//                    GridPane.setRowSpan(avatar, REMAINING);
//                    setGraphic(grid);
                    setText(item);
                } else {
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
