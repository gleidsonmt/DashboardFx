package io.github.gleidsonmt.dashboardfx;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Used only in runtime as tool to help development.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/08/2025
 */
public final class LibrariesTools {

    private static Class<?> loadClassJar(String jarDirectory, String fullClass) {
        Class<?> cls = null;
        try {
            File jarFile = new File(jarDirectory);
            if (!jarFile.exists()) return null;
            URL[] urls = {jarFile.toURI().toURL()};
            URLClassLoader classLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            Thread.currentThread().setContextClassLoader(classLoader);
            cls = Class.forName(fullClass, true, classLoader);
        } catch (MalformedURLException | ClassNotFoundException _) {

        }
        return cls;
    }

    public static void addTools(Scene scene) {
        showScenicView(scene);
        listenCss(scene);
    }

    private static void showScenicView(Scene scene) throws RuntimeException {
        if (scene == null) return;
        ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loadClassJar("./vendor/scenicview.jar", "org.scenicview.ScenicView");
        if (clazz == null) return;

        try {
            clazz.getMethod("show", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException _) {

        } finally {
            Thread.currentThread().setContextClassLoader(originalCtx);
        }
    }

    private static void listenCss(Scene scene) {
        if (scene == null) return;
        Class<?> clazz = loadClassJar("./vendor/cssfx-11.5.1.jar", "fr.brouillard.oss.cssfx.CSSFX");
        if (clazz == null) return;
        try {
            clazz.getMethod("start", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException _) {

        }
    }

}
