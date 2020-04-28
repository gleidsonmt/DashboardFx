/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.global.plugin;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * This class add responsive mode from grids.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/08/2019
 */
public class GridFx {

    // Map of the views
    private static HashMap<String, ChangeListener<Number>> listeners = new HashMap<>();
    // Simple log
    private static boolean log = false;
    // The stage for listen
    private static Stage stage;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void addStage(Stage stage){
        GridFx.stage = stage;
    }

    /**
     * The type is a width of screen.
     */
    public enum Type{
        XS(576), SM(768), MD(992), LG(1200), XL(1201);

        private double value;

        Type(double value) {
            this.value = value;
        }

        public double getValue(){
            return this.value;
        }
    }


    /**
     * Add grid responsive.
     * @param grid grid for change.
     * @param name name of grid.
     */
    public static void add(GridPane grid, String name){

        double value = stage.getWidth();
        switchListener(grid, value);

        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
            switchListener(grid, newValue.doubleValue());
        };

        listeners.put(name, listener);

        stage.widthProperty().addListener(listeners.get(name));

        if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_CYAN + "ViewController added in the map [" + name + "]");
    }

    private static void switchListener(GridPane grid, double newValue){

        if (newValue < Type.XS.getValue()) {
            if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_GREEN + "ViewController changed size to [" + Type.XS + "]");
            organize(grid, Type.XS);
        } else if (newValue > Type.XS.getValue() && newValue < Type.SM.getValue()) {
            if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_GREEN + "ViewController changed size to [" + Type.SM + "]");
            organize(grid, Type.SM);
        } else if (newValue > Type.SM.getValue() && newValue < Type.MD.getValue()) {
            if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_GREEN + "ViewController changed size to [" + Type.MD + "]");
            organize(grid, Type.MD);
        } else if (newValue  > Type.MD.getValue() && newValue  < Type.LG.getValue()) {
            if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_GREEN + "ViewController changed size to [" + Type.LG + "]");
            organize(grid, Type.LG);
        } else {
            if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_GREEN + "ViewController changed size to [" + Type.XL + "]");
            organize(grid, Type.XL);
        }
    }

    public static void remove(String name){
        stage.widthProperty().removeListener(listeners.get(name));
        if(log) System.out.println(ANSI_RESET + "ViewController Manager : " + ANSI_RED + "ViewController removed from the map [" + name + "]");
    }

    /**
     * Visualize in prompt the change of size.
     */
    public static void log(boolean _log){
        log = _log;
    }

    /**
     * When application resize width, add a minimun size scroll pane resize.
     * @param node node for listen
     * @param num num max height.
     */
    public static void defineMin(Region node, double... num){

        final double[] minXs = new double[1];
        final double[] minSm = new double[1];
        final double[] minMed = new double[1];
        final double[] minLg = new double[1];
        final double[] minEl = new double[1];

        Platform.runLater(() -> {
            if(num.length > 4){
                minXs[0] = num[0];
                minSm[0] = num[1];
                minMed[0] = num[2];
                minLg[0] = num[3];
                minEl[0] = num[4];
            } else if(num.length > 3){
                minXs[0] = num[0];
                minSm[0] = num[1];
                minMed[0] = num[2];
                minLg[0] = num[3];

                minEl[0] = num[3];
            } else if(num.length > 2){
                minXs[0] = num[0];
                minSm[0] = num[1];
                minMed[0] = num[2];

                minLg[0] = num[2];
                minEl[0] = num[2];

            } else if(num.length > 1){
                minXs[0] = num[0];
                minSm[0] = num[1];

                minMed[0] = num[1];
                minLg[0] = num[1];
                minEl[0] = num[1];
            } else {
                minXs[0] = num[0];

                minSm[0] = num[0];
                minMed[0] = num[0];
                minLg[0] = num[0];
                minEl[0] = num[0];
            }
            defineMin(node, minXs[0], minSm[0], minMed[0], minLg[0], minEl[0]);
        });
    }

    private static void defineMin(Region node, double minXs, double minSm, double minMed, double minLg, double minEl){
        double newValue = stage.getWidth();

        switchMin(node, minXs, minSm, minMed, minLg, minEl, newValue);

        Platform.runLater(() -> {
            stage.widthProperty().addListener((observable, oldValue, newValue1) -> {
                switchMin(node, minXs, minSm, minMed, minLg, minEl, newValue1.doubleValue());
            });
        });
    }

    private static void switchMin(Region node, double minXs, double minSm, double minMed, double minLg, double minEl, double newValue){

        if(newValue < Type.XS.getValue()) {
            node.setMinHeight(minXs);
        } else if (newValue > Type.XS.getValue() && newValue < Type.SM.getValue()) {
            node.setMinHeight(minSm);
        } else if (newValue >  Type.SM.getValue() && newValue < Type.MD.getValue()) {
            node.setMinHeight(minMed);
        } else if (newValue > Type.MD.getValue() && newValue < Type.LG.getValue()) {
            node.setMinHeight(minLg);
        } else {
            node.setMinHeight(minEl);
        }
    }

    private synchronized static void organize(GridPane grid, Type wid) {
        int col = 0;
        int row = 0;

        for (Node node : grid.getChildren()) {

            for (int i = 1; i <= 12; i++) {
                if (node.getStyleClass().contains("col-" + wid.toString().toLowerCase() + "-" + i)) {
                    if (col + i > 12) {
//                        col = 0;
                        row += 1;
                        GridPane.setColumnSpan(node, i);
                        GridPane.setColumnIndex(node, 0);
                        GridPane.setRowIndex(node, row);
                        col = GridPane.getColumnIndex(node) != 0 ? 2 : i ;
                    } else {
                        GridPane.setColumnSpan(node, i);
                        GridPane.setColumnIndex(node, col);
                        GridPane.setRowIndex(node, row);
                        col += i;
                    }
                }
            }

            for(int z = 1; z <= 12; z++ ){

                if(node.getStyleClass().contains("align-" + wid.toString().toLowerCase() + "-" + "center")) {
                    alignItems(Pos.CENTER, node);
                } else if(node.getStyleClass().contains("align-" + wid.toString().toLowerCase() + "-" + "center-right")) {
                    alignItems(Pos.CENTER_RIGHT, node);
                } else if(node.getStyleClass().contains("align-" + wid.toString().toLowerCase() + "-" + "center-left")) {
                    alignItems(Pos.CENTER_LEFT, node);
                } else if(node.getStyleClass().contains("align-" + wid.toString().toLowerCase() + "-" + "bottom-right")) {
                    alignItems(Pos.BOTTOM_RIGHT, node);
                } else if(node.getStyleClass().contains("align-" + wid.toString().toLowerCase() + "-" + "top-left")) {
                    alignItems(Pos.TOP_LEFT, node);
//                        if (children[i][k].getStyleClass().contains("span-" + width + "-" + z)){
//                            GridPane.setColumnSpan(children[i][k], 2);
//                            GridPane.setConstraints(children[i][k], k + z ,i );
                } else {
                    if(node.getStyleClass().contains("align-center")) {
                        alignItems(Pos.CENTER, node);
                    } else if(node.getStyleClass().contains("align-center-right")) {
                        alignItems(Pos.CENTER_RIGHT, node);
                    } else if(node.getStyleClass().contains("align-center-left")) {
                        alignItems(Pos.CENTER_LEFT, node);
                    }

                    //no alignment return top_left as a default
                    else {
                        alignItems(Pos.TOP_LEFT, node);
                    }
                }
            }

            for (int k = 1; k < 12; k++) {
                if (node.getStyleClass().contains("offset-" + wid.toString().toLowerCase() + "-" + k)) {
//                    GridPane.clearConstraints(node);
//                    GridPane.setColumnSpan(node, GridPane.getColumnSpan(node));
                    GridPane.setRowIndex(node, row);
                    GridPane.setColumnIndex(node, GridPane.getColumnIndex(node) + k);

//                    GridPane.setColumnSpan(node, k);
//                    GridPane.setColumnIndex(node, col);
//                    GridPane.setRowIndex(node, row);
//                    col += k;

                }

//                GridPane.setVgrow(node, Priority.SOMETIMES);
//                GridPane.setHgrow(node, Priority.SOMETIMES);

//                if(k == 1){
//                    GridPane.setVgrow(node, Priority.ALWAYS);
//                    GridPane.setHgrow(node, Priority.ALWAYS);
//                }




            }
        }
    }

    private static void alignItems(Pos pos, Node node){
        if(node instanceof VBox) {
            ((VBox) node).setAlignment(pos);
        } else if(node instanceof HBox){
            ((HBox) node).setAlignment(pos);
        } else if(node instanceof Pagination){   // hack
            HBox pane = (HBox) node.lookup(".control-box");
            pane.setStyle("-fx-alignment : " + pos.toString()); // Force alignment
            pane.setAlignment(pos);
        } else if(node instanceof Control){
            if(pos == Pos.CENTER) {
                GridPane.setHalignment(node, HPos.CENTER);
                GridPane.setValignment(node, VPos.CENTER);
            } else if(pos == Pos.CENTER_LEFT){
                GridPane.setHalignment(node, HPos.LEFT);
                GridPane.setValignment(node, VPos.CENTER);
            } else if(pos == Pos.CENTER_RIGHT){
                GridPane.setHalignment(node, HPos.RIGHT);
                GridPane.setValignment(node, VPos.CENTER);
            }
        }
    }
}