package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.dashboardfx.dashboard.Dashboard;
import io.github.gleidsonmt.dashboardfx.presentation.about.AboutPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.CarouselPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.InteractivityPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.IntroductionPres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.charts.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.components.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.containers.TabPanePres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.containers.TitledPanePres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.controls.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.styled.CircularLoaders;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.styled.ToggleGroupPres;
import io.github.gleidsonmt.dashboardfx.presentation.shapes.TextPres;
import io.github.gleidsonmt.dashboardfx.presentation.util.ColorsPres;
import io.github.gleidsonmt.glad.base.ModuleView;
import io.github.gleidsonmt.glad.base.drawer.Drawer;
import io.github.gleidsonmt.glad.base.drawer.ModuleSeparator;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public class SideNav extends Drawer {

    public SideNav() {
        super(List.of(
                new Dashboard(),
                new ModuleSeparator(new SVGIcon(Icon.HUB), "Project"),
                new ModuleView("Core",
                        new IntroductionPres(),
                        new InteractivityPres()
                ),
                new ModuleSeparator(new SVGIcon(Icon.DESIGN_SERVICES), "Theme"),
                new ModuleView("Shapes",
                        new TextPres()
                ),
//                        new View("Circle", new BuildingPage())),
                new ModuleView("Controls",
                        new RegionPres(),
                        new LabeledPres(),
                        new ChoiceBoxPres(),
                        new ComboBoxBasePres(),
                        new TextInputPres(),
                        new ListViewPres(),
                        new TableViewPres(),
                        new TreeViewPres(),
                        new ProgressBarPres()
                ),
                new ModuleView("Containers",
                        new TitledPanePres(),
                        new TabPanePres()
                ),
                new ModuleView("Charts",
                        new BarChartPres(),
                        new AreaChartPres(),
                        new StackedAreaChartPres(),
                        new StackedBarChartPres(),
                        new DonutChartPres(),
                        new LineChartPres()),
                new ModuleSeparator(new SVGIcon(Icon.STACK), "Examples"),
                new ModuleView("Components",
                        new ToggleSwitchPres(),
                        new SVGIconPres(),
                        new AvatarViewPres(),
                        new BadgeExample(),
                        new CarouselPres()

                ),
                new ModuleView("Styled",
//                        new View("DrawerOld", new BuildingPage()),
//                        new View("BreadCrumb", new BuildingPage()),
//                        new View("Tree View", new TreeViewExample()),
                        new ToggleGroupPres(),
                        new CircularLoaders(),
                        new LabelExamplePres(),
                        new CardsPres()
                ),
//                new ModuleView("Pages",

//                        new View("Home Page", new HomePage()),
////                        new View("Login", new LoginPage()),
//                        new View("Error Page 404")
//                        ),
                new ModuleSeparator(new SVGIcon(Icon.HELP), "Theme"),
                new ModuleView("Extras",
                        new ColorsPres()
//                        new View("Alignment", new BuildingPage()))
                ),
                new AboutPres()
        ));

        setFooter(new DrawerFooter());
        setHeader(new DrawerHeader(this));
    }
}
