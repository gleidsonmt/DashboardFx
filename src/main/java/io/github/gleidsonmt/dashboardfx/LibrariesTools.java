package io.github.gleidsonmt.dashboardfx;

import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/08/2025
 */
public class LibrariesTools {

    public static void showScenicView(Scene scene) {
        if (scene == null) return;
        try {
            // ... inside a method or class
            File jarFile = new File("./vendor/scenicview.jar");
            URL[] urls = { jarFile.toURI().toURL() };
            URLClassLoader classLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            String className = "org.scenicview.ScenicView"; // Fully qualified class name

            Platform.runLater(() -> {
                ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
                Thread.currentThread().setContextClassLoader(classLoader);
                try {
                    Class<?> cls = Class.forName(className, true, classLoader);
                    cls.getMethod("show", Scene.class).invoke(null, scene);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                         ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    Thread.currentThread().setContextClassLoader(originalCtx);
                }
            });
            System.out.println("[ScenicView] Aberto com sucesso.");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listenCss(Scene scene) {
        if (scene == null) return;
        try {

            // ... inside a method or class
            File jarFile = new File("./vendor/cssfx-11.5.1.jar");
            URL[] urls = {jarFile.toURI().toURL()};
            URLClassLoader classLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            String className = "fr.brouillard.oss.cssfx.CSSFX"; // Fully qualified class name
            Class<?> cls = Class.forName(className, true, classLoader);

            cls.getMethod("start", Scene.class).invoke(null, scene);
        } catch (ReflectiveOperationException e) {
            System.out.println("[ScenicView] Error on invoking ScenicView.show(Scene): " + e.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
