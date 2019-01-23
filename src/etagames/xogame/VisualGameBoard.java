/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import etagames.xogame.GameBoard.CellValue;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author a-tarek
 */
public class VisualGameBoard extends FlowPane {

    public static VisualCell vCells[] = new VisualCell[9];

    public VisualGameBoard() {
        System.out.println("etagames.xogame.VisualGameBoard.<init>()");
        this.setMinHeight(-1.0);
        this.setMinWidth(-1.0);
        this.setPrefHeight(00.0);
        this.setPrefWidth(600.0);

        for (Integer i = 0; i < 9; i++) {
            VisualCell vCell = new VisualCell(i.toString());
            vCell.setStyle("-fx-font: 75 arial;-fx-background-color:#ecebe9,rgba(0,0,0,0.05),linear-gradient(#dcca8a, #c7a740),linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),linear-gradient(#f6ebbe, #e6c34d);-fx-background-insets: 0,9 9 8 9,9,10,11;-fx-background-radius: 50;-fx-padding: 15 30 15 30;-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1)");
            vCells[i] = vCell;
            this.getChildren().add(vCell);
            VisualCell.timeline.play();

        }
    }

    public void setVCellOnBoard(CellValue cv, int position) {

        String v = (cv == CellValue.x) ? "X" : "O";
        System.out.println("etagames.xogame.VisualGameBoard.setVCellOnBoard()");
        String style = vCells[position].getStyle();
        if ("X".equals(v)) {
            vCells[position].setStyle("-fx-text-fill: red;" + style);
        } else {
            vCells[position].setStyle("-fx-text-fill: blue;" + style);
        }
        vCells[position].setText(v);
        vCells[position].setDisable(true);
    }

    public VisualCell[] getVisualCells() {
        return vCells;
    }
}
