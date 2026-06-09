package io.github.gleidsonmt.dashboardfx.presentation;

public class Author {

    private final String name;
    private final String gitUrl;
    private final String documentation;

    public Author(String name) {
        this(name, null);
    }

    public Author(String name, String gitUrl) {
        this(name, gitUrl, null);
    }

    public Author(String name, String gitUrl, String documentation) {
        this.name = name;
        this.gitUrl = gitUrl;
        this.documentation = documentation;
    }

    public String getName() {
        return name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getDocumentation() {
        return documentation;
    }
}
