package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Hardware;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.avatar.StackedAvatar;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
public class TableCellUsersFactory implements Callback<TableColumn<Hardware, ObservableList<User>>, TableCell<Hardware, ObservableList<User>>> {
    @Override
    public TableCell<Hardware, ObservableList<User>> call(TableColumn<Hardware, ObservableList<User>> activityCompanyTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(ObservableList<User> item, boolean empty) {
                if (item != null) {
                    setAlignment(Pos.CENTER_LEFT);
                    AvatarView[] imgs = new AvatarView[item.size()];
                    for (int i = 0; i < item.size(); i++) {
                        imgs[i] = new AvatarView(item.get(i).getAvatar());
                    }
                    StackedAvatar avatar = new StackedAvatar();
                    avatar.setAvatarSize(40);
                    avatar.setMax(3);
                    avatar.getAvatarViews().addAll(imgs);

                    setGraphic(avatar);
                    setText(null);

                } else {
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
