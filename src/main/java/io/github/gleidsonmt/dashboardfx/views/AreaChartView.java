/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.Container;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class AreaChartView extends Container {

    public AreaChartView(Context context) {
        super("name");
        VBox body = new VBox();
        body.setAlignment(Pos.CENTER);

        AreaChart<Number, Number> areaChart = new AreaChart<>(
                new NumberAxis(), new NumberAxis()
        );

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Legend 1");
        series.getData().add(new XYChart.Data<>(0, 2D));
        series.getData().add(new XYChart.Data<>(1, 8D));
        series.getData().add(new XYChart.Data<>(2, 5D));
        series.getData().add(new XYChart.Data<>(3, 3D));
        series.getData().add(new XYChart.Data<>(4, 6D));
        series.getData().add(new XYChart.Data<>(5, 8D));
        series.getData().add(new XYChart.Data<>(6, 5D));
        series.getData().add(new XYChart.Data<>(7, 6D));
        series.getData().add(new XYChart.Data<>(8, 5D));


        XYChart.Series<Number, Number> secondaySeries = new XYChart.Series<>();
        secondaySeries.setName("Legend 2");
        secondaySeries.getData().add(new XYChart.Data<>(0, 2D));
        secondaySeries.getData().add(new XYChart.Data<>(1, 2D));
        secondaySeries.getData().add(new XYChart.Data<>(2, 7D));
        secondaySeries.getData().add(new XYChart.Data<>(3, 8D));
        secondaySeries.getData().add(new XYChart.Data<>(4, 3D));
        secondaySeries.getData().add(new XYChart.Data<>(5, 2D));
        secondaySeries.getData().add(new XYChart.Data<>(6, 3D));
        secondaySeries.getData().add(new XYChart.Data<>(7, 4D));
        secondaySeries.getData().add(new XYChart.Data<>(8, 5D));


        XYChart.Series<Number, Number> thirdSeries = new XYChart.Series<>();
        thirdSeries.setName("Legend 3");
        thirdSeries.getData().add(new XYChart.Data<>(0, 0D));
        thirdSeries.getData().add(new XYChart.Data<>(1, 6D));
        thirdSeries.getData().add(new XYChart.Data<>(2, 3D));
        thirdSeries.getData().add(new XYChart.Data<>(3, 4D));
        thirdSeries.getData().add(new XYChart.Data<>(4, 3D));
        thirdSeries.getData().add(new XYChart.Data<>(5, 8D));
        thirdSeries.getData().add(new XYChart.Data<>(6, 3D));
        thirdSeries.getData().add(new XYChart.Data<>(7, 2D));
        thirdSeries.getData().add(new XYChart.Data<>(8, 1D));

        areaChart.getData().setAll(series, secondaySeries, thirdSeries);
        areaChart.setTitle("Area Chart Title");
        areaChart.setCreateSymbols(true);

        Author author = new Author("JavaFx (17) ",
                "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/chart/AreaChart.html");

        Hyperlink hp = new Hyperlink();
        hp.setGraphic(new IconContainer(Icons.ANALYTICS_FILLED));
        hp.setText(author.getName());
        hp.setGraphicTextGap(10);

        VBox cx = new VBox();
        cx.setAlignment(Pos.BOTTOM_CENTER);
        cx.getChildren().setAll(new Label("Author"), hp);

        BlockCode blockCode = new BlockCode(context, """
                   AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());
                            
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    series.setName("Legend 1");
                    series.getData().add(new XYChart.Data<>(0, 2D));
                    series.getData().add(new XYChart.Data<>(1, 8D));
                    series.getData().add(new XYChart.Data<>(2, 5D));
                    series.getData().add(new XYChart.Data<>(3, 3D));
                    series.getData().add(new XYChart.Data<>(4, 6D));
                    series.getData().add(new XYChart.Data<>(5, 8D));
                    series.getData().add(new XYChart.Data<>(6, 5D));
                    series.getData().add(new XYChart.Data<>(7, 6D));
                    series.getData().add(new XYChart.Data<>(8, 5D));
                            
                            
                    XYChart.Series<Number, Number> secondaySeries = new XYChart.Series<>();
                    secondaySeries.setName("Legend 2");
                    secondaySeries.getData().add(new XYChart.Data<>(0, 2D));
                    secondaySeries.getData().add(new XYChart.Data<>(1, 2D));
                    secondaySeries.getData().add(new XYChart.Data<>(2, 7D));
                    secondaySeries.getData().add(new XYChart.Data<>(3, 8D));
                    secondaySeries.getData().add(new XYChart.Data<>(4, 3D));
                    secondaySeries.getData().add(new XYChart.Data<>(5, 2D));
                    secondaySeries.getData().add(new XYChart.Data<>(6, 3D));
                    secondaySeries.getData().add(new XYChart.Data<>(7, 4D));
                    secondaySeries.getData().add(new XYChart.Data<>(8, 5D));
                            
                            
                    XYChart.Series<Number, Number> thirdSeries = new XYChart.Series<>();
                    thirdSeries.setName("Legend 3");
                    thirdSeries.getData().add(new XYChart.Data<>(0, 0D));
                    thirdSeries.getData().add(new XYChart.Data<>(1, 6D));
                    thirdSeries.getData().add(new XYChart.Data<>(2, 3D));
                    thirdSeries.getData().add(new XYChart.Data<>(3, 4D));
                    thirdSeries.getData().add(new XYChart.Data<>(4, 3D));
                    thirdSeries.getData().add(new XYChart.Data<>(5, 8D));
                    thirdSeries.getData().add(new XYChart.Data<>(6, 3D));
                    thirdSeries.getData().add(new XYChart.Data<>(7, 2D));
                    thirdSeries.getData().add(new XYChart.Data<>(8, 1D));
                            
                    areaChart.getData().setAll(series, secondaySeries, thirdSeries);
                    areaChart.setTitle("Area Chart Title");
                    areaChart.setCreateSymbols(true);
                """);

        blockCode.setMaxHeight(200);

        VBox.setMargin(cx, new Insets(20));
        body.getChildren().setAll(areaChart, cx, blockCode);
        this.getChildren().setAll(body);
    }

    private HBox createFooter(Author... authors) {
        HBox body = new HBox();
        for (Author author : authors) {
            Hyperlink hp = new Hyperlink();
            hp.setGraphic(new IconContainer(Icons.GITHUB));
            hp.setText(author.getName());
            hp.setGraphicTextGap(10);
//            hp.setOnAction(event -> context.openLink(author.getGitUrl()));
            body.getChildren().add(hp);

            if (authors.length > 1) {
                Separator sep = new Separator();
                sep.setOrientation(Orientation.VERTICAL);
                HBox.setMargin(sep, new Insets(0, 10, 0, 10));
                body.getChildren().add(sep);
            }
        }

        return body;
    }
}
