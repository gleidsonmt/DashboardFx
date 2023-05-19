package io.github.gleidsonmt.dashboardfx.core.view;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  11/04/2023
 */
public class ViewComposer {

    private String title;
    private String name;
    private String folder;
    private String fxml;

    public ViewComposer() {

    }

    public ViewComposer(String title, String name) {
        this.title = title;
        this.name = name;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFxml() {
        return fxml;
    }

    public void setFxml(String fxml) {
        this.fxml = fxml;
    }

    @Override
    public String toString() {
        return "ViewComposer{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", folder='" + folder + '\'' +
                ", fxml='" + fxml + '\'' +
                '}';
    }
}
