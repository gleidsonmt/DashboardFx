package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Hardware;
import io.github.gleidsonmt.dashboardfx.model.Status;
import io.github.gleidsonmt.dashboardfx.utils.StringUtils;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
public class TableCellStatusFactory implements Callback<TableColumn<Hardware, Status>, TableCell<Hardware, Status>> {
    @Override
    public TableCell<Hardware, Status> call(TableColumn<Hardware, Status> activityCompanyTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(Status item, boolean empty) {
                if (item != null) {
                    Label label = new Label(StringUtils.capitalize(item.name()));
                    label.getStyleClass().addAll(item.equals(Status.BUSY) ? "bg-danger" : "bg-success", "text-white", "padding-5", "rounded", "bold");

                    setGraphic(label);
                    setText(null);
                } else {
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
