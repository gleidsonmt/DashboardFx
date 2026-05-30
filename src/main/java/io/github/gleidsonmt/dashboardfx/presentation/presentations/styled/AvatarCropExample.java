package io.github.gleidsonmt.dashboardfx.presentation.presentations.styled;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.controls.avatar_crop.AvatarCrop;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.io.File;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/08/2025
 */
public class AvatarCropExample extends CustomizablePresentation {

    public AvatarCropExample() {
        super("Avatar Crop");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .overview()
                .h3("Avatar Crop")
                .separator()
                .text("Custom region to crop images. (500x200 preferred)")
//                .text("If you want to learn more.")
//                .node(TutorialUtils.createCardLink("See this article", "https://gleidsonmt.github.io/#/blog/post/0"))
                .demo(new AvatarCrop(Assets.getImage("default_avatar.jpg")))

                .h3("Using", "Avatar Crop")
                .code("""
                        // Importing
                        import io.github.gleidsonmt.glad.controls.avatar_crop.AvatarCrop;
                        
                        // Creating loaders
                        AvatarCrop avatarCrop = new AvatarCrop(App.getImage("default_avatar.jpg");
                        
                        // calling as dialog
                        root
                            .behavior()
                            .dialog()
                            .content(avatarCrop)
                            .effect(WrapperEffect.GRAY)
                            .show();
                        """)


                .demo(TutorialUtils.createAction(node -> {
                    getRoot().behavior()
                            .dialog()
                            .content(createAvatarCrop())
                            .with(WrapperEffect.GRAY)
                            .show();
                }))

                .h3("Saving", "Avatar Crop")
                .code("""
                        avatarCrop.setOnSave(image -> {
                            // do something with image
                        });
                        """)
                .h3("Closing", "Avatar Crop")
                .code("""
                        avatarCrop.setOnSave(() -> {
                            // do something while closing
                        });
                        """)

                ;
    }

    private Region createAvatarCrop() {
        var avatarCrop = new AvatarCrop(Assets.getImage("default_avatar.jpg"));
        avatarCrop.getStyleClass().add("bg-white");
        avatarCrop.setOnClose(() -> {
            getRoot().behavior()
                    .dialog()
                    .hide();
        });
        avatarCrop.setOnSave(image -> {

        });
        return avatarCrop;
    }
}
