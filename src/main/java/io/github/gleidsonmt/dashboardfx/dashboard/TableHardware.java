package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Hardware;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.math.BigDecimal;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class TableHardware extends TableView<Hardware> {
    public TableHardware() {
        getStyleClass().addAll("transparent-table", "bg-foreground");
        setMinHeight(300);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);


        getItems().addAll(
                new Hardware(
                        Assets.getImage("technology/chip.png", 40), "Intel Core i7-10700F", new BigDecimal("2199.88")
                ),
                new Hardware(
                        Assets.getImage("technology/motherboard.png", 70), "Asus TUF Gaming Z490-Plus (Wi-Fi)", new BigDecimal("1552.82")
                ),
                new Hardware(
                        Assets.getImage("technology/computer.png", 90), "Gamer Sharkoon Pure Steel White RGB", new BigDecimal("670.47")
                ),
                new Hardware(
                        Assets.getImage("technology/ram-memory.png", 80), "Patriot Viper Steel 16GB", new BigDecimal("588.12")
                )
        );

        TableColumn<Hardware, Image> avatarColumn = new TableColumn<>("#");
        avatarColumn.setMaxWidth(100);
        TableColumn<Hardware, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Hardware, BigDecimal> incomeColumn = new TableColumn<>("Value");
        incomeColumn.setMaxWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<>("avatar"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        nameColumn.setCellFactory(new TableCellCompanyFactory());
        avatarColumn.setCellFactory(_ -> new TableCell<>() {
            @Override
            protected void updateItem(Image item, boolean empty) {
                if (item != null && !empty) {
                    setGraphic(new AvatarView(item, 0, 40));
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        incomeColumn.setCellFactory(new MonetaryCellFactory<>());

        //noinspection unchecked
        getColumns().addAll(avatarColumn, nameColumn, incomeColumn);
        for (TableColumn<Hardware, ?> column : getColumns()) {
            column.setMinWidth(100);
        }
    }
}
