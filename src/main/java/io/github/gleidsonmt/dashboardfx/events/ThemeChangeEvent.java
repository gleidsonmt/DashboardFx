package io.github.gleidsonmt.dashboardfx.events;

import io.github.gleidsonmt.dashboardfx.dashboard.Theme;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 17/06/2026
 */
public class ThemeChangeEvent extends Event {

    public static final EventType<ThemeChangeEvent> ANY = new EventType<>(Event.ANY, "THEME_CHANGE_EVENT");

    private final Theme theme;

    public ThemeChangeEvent(EventType<? extends Event> eventType, Theme theme) {
        super(eventType);
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }
}
