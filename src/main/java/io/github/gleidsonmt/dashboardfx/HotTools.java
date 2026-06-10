package io.github.gleidsonmt.dashboardfx;

import javafx.scene.Scene;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Tools to help to debug when the application is running.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/08/2025
 */
public final class HotTools {

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

    /**
     * Invoke scenic view to analyze nodes.
     * ScenicView is a amazing tool to see properties and nodes together in real time.
     * @param scene The scene to analyze.
     * @throws RuntimeException
     */
    public static void analyzeNodes(Scene scene) {
        if (scene == null) return;
        ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loadClassJar("./vendor/scenicview.jar", "org.scenicview.ScenicView");
        if (clazz == null) return;

        try {
            clazz.getMethod("show", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException _) {
            throw new RuntimeException("ScenicView not found.");
        } finally {
            Thread.currentThread().setContextClassLoader(originalCtx);
        }
    }

    /**
     * The CCSFX is a CSS engine that allows you to change CSS in JavaFX when is running and see imeditiatly.
     * @param scene The scene to listen.
     */
    public static void listenCss(Scene scene) {
        if (scene == null) return;
        ClassLoader originalCtx = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loadClassJar("./vendor/cssfx-11.5.1.jar", "fr.brouillard.oss.cssfx.CSSFX");
        if (clazz == null) return;
        try {
            clazz.getMethod("start", Scene.class).invoke(null, scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException _) {
            throw new RuntimeException("ScenicView not found.");
        } finally {
            Thread.currentThread().setContextClassLoader(originalCtx);
        }
    }

}
