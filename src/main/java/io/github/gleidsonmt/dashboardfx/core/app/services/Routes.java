/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.app.services;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import io.github.gleidsonmt.dashboardfx.core.app.WindowDecorator;
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IRotes;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.PathView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/08/2022
 */
@SuppressWarnings("all")
//public final class Routes implements IRotes {
public final class Routes implements IRotes {

    private final ViewManager manager = new ViewManager();
    private final WindowDecorator decorator;
    private final PathView path;

    public Routes(WindowDecorator _decorator, PathView _path) {
        decorator = _decorator;
        this.path = _path;
    }

    @Override
    public void setContent(String view) throws NavigationException {
        work(view, true);
    }

    @Override
    public void setView(String view) throws NavigationException {
        work(view, false);
    }

    @Override
    public IView getView(String view) {
        return manager.get(view);
    }

    @Override
    public IView getCurrent() {
        return manager.getCurrent();
    }

    @Override
    public IView getPrevious() {
        return manager.getPrevious();
    }

    @Override
    public void addView(IView iView) {
        manager.add(iView);
    }

    private void work(String view, boolean content) throws NavigationException {

        if (view == null) {
            throw new NavigationException("NAVIGATION", "The view not found.");
        }

        IView iView = manager.get(view);
        ActionView controller = iView.getController();
        IView previous = manager.getCurrent(); // Pega a view atual

        if (previous != null) { // Se view diferente de nulo
            if (previous.getController() != null) // se controlador diferente de nulo
                previous.getController().onExit(); // executa a acao da visao antes de sair
        }

        if (controller != null) { // verifica se a nova view possui controlador
            controller.onEnter(); // executa a primeira acao
        }

        manager.setCurrent(iView); // atualiza a visao corrent na controladora de views.

        updateAndAnimate(previous, iView, content);

    }

    private void updateAndAnimate(IView previous, IView iView, boolean content) {

        if (previous != null) {
            FadeOut fadeOut = new FadeOut(previous.getRoot());
            fadeOut.setSpeed(8);
            fadeOut.play();

            fadeOut.getTimeline().setOnFinished(event -> {
                previous.getRoot().setOpacity(1);
                FadeIn fadeIn = new FadeIn(iView.getRoot());
                fadeIn.setSpeed(8);

                iView.getRoot().setOpacity(0);

                if (!content) {
                    setViewContent(iView);
                } else {
                    setContent(iView);
                }

                fadeIn.getTimeline().setOnFinished(e -> iView.getRoot().setOpacity(1));
                fadeIn.play();
            });
        } else {
            FadeIn fadeIn = new FadeIn(iView.getRoot());
            fadeIn.setSpeed(8);

            iView.getRoot().setOpacity(0);
            fadeIn.getTimeline().setOnFinished(e -> iView.getRoot().setOpacity(1));
            fadeIn.play();

            if (!content) {
                setViewContent(iView);
            } else {
                setContent(iView);
            }
        }
    }

    private void setViewContent(@NotNull IView iView) {
        decorator.setContent(iView.getRoot());
    }

    private void setContent(@NotNull IView iView) {
        decorator.getRoot().setContent(iView.getRoot()); // Configura o layout center
//        decorator.setModule(iView.getComposer().getTitle());
    }

    @Override
    public @NotNull IView load(String folder, String title, String name) {

        FXMLLoader loader = new FXMLLoader();

//        loader.setLocation(getClass().getResource(paths.getFromView(folder)));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (loader.getController() != null) {
//            if (loader.getController() instanceof ActionView) {
//                ( (ActionView) loader.getController()).onEnter();
//            }
//        }

        return new IView() {

            @Override
            public String getName() {
                return name;
            }

            @Override
            public ViewComposer getComposer() {
                return null;
            }

            @Override
            public ActionView getController() {
                return loader.getController();
            }

            @Override
            public Parent getRoot() {
                return loader.getRoot();
            }

            @Override
            public Charset getCharset() {
                return loader.getCharset();
            }

            @Override
            public URL getLocation() {
                return loader.getLocation();
            }
        };
    }

    @Override
    public void goHome() {
        if (!decorator.getContent().equals(decorator.getRoot())) {
            decorator.setContent((StackPane) decorator.getRoot());
        }
    }
}
