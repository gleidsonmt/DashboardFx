package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.dashboardfx.dashboard.Dashboard;
import io.github.gleidsonmt.dashboardfx.presentation.about.AboutPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.BuildingPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.CarouselPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.InteractivityPres;
import io.github.gleidsonmt.dashboardfx.presentation.core.IntroductionPres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.charts.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.components.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.containers.TabPanePres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.containers.TitledPanePres;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.controls.*;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.pages.AboutPageView;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.pages.HomePageView;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.pages.LoginPageView;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.styled.AvatarCropExample;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.styled.CircularLoaders;
import io.github.gleidsonmt.dashboardfx.presentation.presentations.styled.ToggleGroupPres;
import io.github.gleidsonmt.dashboardfx.presentation.shapes.CirclePres;
import io.github.gleidsonmt.dashboardfx.presentation.shapes.RectanglePres;
import io.github.gleidsonmt.dashboardfx.presentation.shapes.TextPres;
import io.github.gleidsonmt.dashboardfx.presentation.util.ColorPickerPres;
import io.github.gleidsonmt.dashboardfx.presentation.util.ColorsPres;
import io.github.gleidsonmt.dashboardfx.presentation.util.TipsPres;
import io.github.gleidsonmt.dashboardfx.utils.pages.BuildingPage;
import io.github.gleidsonmt.glad.base.drawer.*;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2025
 */
public class Nav extends SideNav {

    public Nav() {
        super(
                        new Dashboard(),
                        new ModuleSeparator(new SVGIcon(Icon.HUB), "Project"),
                        new ViewGroup("Core",
                                new IntroductionPres(),
                                new BuildingPres(),
                                new InteractivityPres()
                        ),
                        new ModuleSeparator(new SVGIcon(Icon.DESIGN_SERVICES), "Theme"),
                        new ViewGroup("Shapes",
                                new TextPres(),
                                new CirclePres(),
                                new RectanglePres()
                        ),
//                        new View("Circle", new BuildingPage())),
                        new ViewGroup("Controls",
                                new RegionPres(),
                                new LabeledPres(),
                                new ChoiceBoxPres(),
                                new ComboBoxBasePres(),
                                new TextInputPres(),
                                new ListViewPres(),
                                new TableViewPres(),
                                new TreeViewPres(),
                                new ProgressBarPres(),
                                new ScrollPane(),
                                new ScrollBarPres(),
                                new SliderPres()

                        ),
                        new ViewGroup("Containers",
                                new TitledPanePres(),
                                new TabPanePres()
                        ),
                        new ViewGroup("Charts",
                                new BarChartPres(),
                                new AreaChartPres(),
                                new StackedAreaChartPres(),
                                new StackedBarChartPres(),
                                new DonutChartPres(),
                                new LineChartPres()),
                        new ModuleSeparator(new SVGIcon(Icon.STACK), "Examples"),
                        new ViewGroup("Components",
                                new ToggleSwitchPres(),
                                new SVGIconPres(),
                                new AvatarViewPres(),
                                new BadgeExample(),
                                new CarouselPres()

                        ),
                        new ViewGroup("Styled",
//                        new View("DrawerOld", new BuildingPage()),
//                        new View("BreadCrumb", new BuildingPage()),
//                        new View("Tree View", new TreeViewExample()),
                                new ToggleGroupPres(),
                                new CircularLoaders(),
                                new LabelExamplePres(),
                                new AvatarCropExample(),
                                new CardsPres()
                        ),
                        new ViewGroup("Pages",
                                new LoginPageView(),
                                new HomePageView(),
                                new AboutPageView()
//                        new View("Error Page 404")
                        ),
                        new ModuleSeparator(new SVGIcon(Icon.HELP), "Theme"),
                        new ViewGroup("Extras",
                                new ColorsPres(),
                                new ColorPickerPres(),
                                new TipsPres()

//                        new View("Alignment", new BuildingPage()))
                        ),
                        new AboutPres()
                );

        setFooter(new DrawerFooter());
        setHeader(new DrawerHeader(this));

        this.select(getItems().getFirst());
    }
}
