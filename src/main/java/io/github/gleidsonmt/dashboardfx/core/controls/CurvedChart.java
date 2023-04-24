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

package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.shape.*;
import javafx.util.Pair;

public class CurvedChart<X, Y> extends AreaChart<Number, Number> {

        public CurvedChart() {
            this(new NumberAxis(), new NumberAxis());
        }

        public CurvedChart(@NamedArg("xAxis") Axis<Number> numberAxis, @NamedArg("yAxis") Axis<Number> numberAxis2) {
            super(numberAxis, numberAxis2);
        }

        protected void layoutPlotChildren() {
            super.layoutPlotChildren();

            for(int seriesIndex = 0; seriesIndex < this.getDataSize(); ++seriesIndex) {
                Series<Number, Number> series = (Series)this.getData().get(seriesIndex);
                Path seriesLine = (Path)((Group)series.getNode()).getChildren().get(1);
                Path fillPath = (Path)((Group)series.getNode()).getChildren().get(0);
                smooth(seriesLine.getElements(), fillPath.getElements());
            }

        }

        private int getDataSize() {
            ObservableList<Series<Number, Number>> data = this.getData();
            return data != null ? data.size() : 0;
        }

        private static void smooth(ObservableList<PathElement> strokeElements, ObservableList<PathElement> fillElements) {
            Point2D[] dataPoints = new Point2D[strokeElements.size()];

            for(int i = 0; i < strokeElements.size(); ++i) {
                PathElement element = (PathElement)strokeElements.get(i);
                if (element instanceof MoveTo) {
                    MoveTo move = (MoveTo)element;
                    dataPoints[i] = new Point2D(move.getX(), move.getY());
                } else if (element instanceof LineTo) {
                    LineTo line = (LineTo)element;
                    double x = line.getX();
                    double y = line.getY();
                    dataPoints[i] = new Point2D(x, y);
                }
            }

            double zeroY = ((MoveTo)fillElements.get(0)).getY();
            strokeElements.clear();
            fillElements.clear();
            Pair<Point2D[], Point2D[]> result = calcCurveControlPoints(dataPoints);
            Point2D[] firstControlPoints = (Point2D[])result.getKey();
            Point2D[] secondControlPoints = (Point2D[])result.getValue();
            strokeElements.add(new MoveTo(dataPoints[0].getX(), dataPoints[0].getY()));
            fillElements.add(new MoveTo(dataPoints[0].getX(), zeroY));
            fillElements.add(new LineTo(dataPoints[0].getX(), dataPoints[0].getY()));

            for(int i = 1; i < dataPoints.length; ++i) {
                int ci = i - 1;
                strokeElements.add(new CubicCurveTo(firstControlPoints[ci].getX(), firstControlPoints[ci].getY(), secondControlPoints[ci].getX(), secondControlPoints[ci].getY(), dataPoints[i].getX(), dataPoints[i].getY()));
                fillElements.add(new CubicCurveTo(firstControlPoints[ci].getX(), firstControlPoints[ci].getY(), secondControlPoints[ci].getX(), secondControlPoints[ci].getY(), dataPoints[i].getX(), dataPoints[i].getY()));
            }

            fillElements.add(new LineTo(dataPoints[dataPoints.length - 1].getX(), zeroY));
            fillElements.add(new ClosePath());
        }

        public static Pair<Point2D[], Point2D[]> calcCurveControlPoints(Point2D[] dataPoints) {
            int n = dataPoints.length - 1;
            Point2D[] firstControlPoints;
            Point2D[] secondControlPoints;
            if (n == 1) {
                firstControlPoints = new Point2D[]{new Point2D((2.0 * dataPoints[0].getX() + dataPoints[1].getX()) / 3.0, (2.0 * dataPoints[0].getY() + dataPoints[1].getY()) / 3.0)};
                secondControlPoints = new Point2D[]{new Point2D(2.0 * firstControlPoints[0].getX() - dataPoints[0].getX(), 2.0 * firstControlPoints[0].getY() - dataPoints[0].getY())};
                return new Pair(firstControlPoints, secondControlPoints);
            } else {
                double[] rhs = new double[n];

                for(int i = 1; i < n - 1; ++i) {
                    rhs[i] = 4.0 * dataPoints[i].getX() + 2.0 * dataPoints[i + 1].getX();
                }

                rhs[0] = dataPoints[0].getX() + 2.0 * dataPoints[1].getX();
                rhs[n - 1] = (8.0 * dataPoints[n - 1].getX() + dataPoints[n].getX()) / 2.0;
                double[] x = GetFirstControlPoints(rhs);

                for(int i = 1; i < n - 1; ++i) {
                    rhs[i] = 4.0 * dataPoints[i].getY() + 2.0 * dataPoints[i + 1].getY();
                }

                rhs[0] = dataPoints[0].getY() + 2.0 * dataPoints[1].getY();
                rhs[n - 1] = (8.0 * dataPoints[n - 1].getY() + dataPoints[n].getY()) / 2.0;
                double[] y = GetFirstControlPoints(rhs);
                firstControlPoints = new Point2D[n];
                secondControlPoints = new Point2D[n];

                for(int i = 0; i < n; ++i) {
                    firstControlPoints[i] = new Point2D(x[i], y[i]);
                    if (i < n - 1) {
                        secondControlPoints[i] = new Point2D(2.0 * dataPoints[i + 1].getX() - x[i + 1], 2.0 * dataPoints[i + 1].getY() - y[i + 1]);
                    } else {
                        secondControlPoints[i] = new Point2D((dataPoints[n].getX() + x[n - 1]) / 2.0, (dataPoints[n].getY() + y[n - 1]) / 2.0);
                    }
                }

                return new Pair(firstControlPoints, secondControlPoints);
            }
        }

        private static double[] GetFirstControlPoints(double[] rhs) {
            int n = rhs.length;
            double[] x = new double[n];
            double[] tmp = new double[n];
            double b = 2.0;
            x[0] = rhs[0] / b;

            int i;
            for(i = 1; i < n; ++i) {
                tmp[i] = 1.0 / b;
                b = (i < n - 1 ? 4.0 : 3.5) - tmp[i];
                x[i] = (rhs[i] - x[i - 1]) / b;
            }

            for(i = 1; i < n; ++i) {
                x[n - i - 1] -= tmp[n - i] * x[n - i];
            }

            return x;
        }
    }
