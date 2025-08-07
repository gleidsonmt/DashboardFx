package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Activity;
import io.github.gleidsonmt.dashboardfx.model.Company;
import io.github.gleidsonmt.glad.controls.avatar.AvatarCircleView;
import io.github.gleidsonmt.glad.controls.avatar.AvatarRectView;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.geometry.Insets;
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
public class TableCellCompanyFactory implements Callback<TableColumn<Activity, Company>, TableCell<Activity, Company>> {
    @Override
    public TableCell<Activity, Company> call(TableColumn<Activity, Company> activityCompanyTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(Company item, boolean empty) {
                if (item != null) {
                    GridPane grid = new GridPane();
                    grid.setHgap(15);
                    Text title = new Text(item.getName());
                    title.getStyleClass().addAll("h5", "bold");
                    Text legend = new Text(item.getAddress());
                    legend.getStyleClass().addAll("h5");
                    AvatarView avatar = new AvatarView(item.getAvatar(), 0);
                    avatar.setWidth(40);
                    avatar.setHeight(40);
//                    avatar.getStyleClass().addAll("stroke-light-gray-2");
                    grid.add(avatar, 0,0);
                    grid.add(title, 1,0);
                    grid.add(legend, 1,1);
                    GridPane.setRowSpan(avatar, REMAINING);
                    setGraphic(grid);
                    setText(null);
                } else {
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
