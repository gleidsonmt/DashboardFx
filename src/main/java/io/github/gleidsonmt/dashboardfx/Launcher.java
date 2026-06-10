package io.github.gleidsonmt.dashboardfx;

public class Launcher extends App {

    public static Mode mode = Mode.DEFAULT;

    public static void main(String[] args) {
        for (String arg : args) {
            if ("debug".equalsIgnoreCase(arg)) {
                mode = Mode.DEBUG;
            } else if ("log".equalsIgnoreCase(arg)) {
                mode = Mode.LOG;
            }
            else mode = Mode.DEFAULT;
        }
        launch(args);
    }
}

enum Mode {
    DEBUG, LOG, DEFAULT
}