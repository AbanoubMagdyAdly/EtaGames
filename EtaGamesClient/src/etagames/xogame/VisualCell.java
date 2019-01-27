/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 *
 * @author a-tarek
 */
public class VisualCell extends Button {
public static Timeline timeline;
    public VisualCell(String id) {
        System.out.println("etagames.xogame.VisualCell.<init>()");
        this.setId(id);
        this.setLayoutX(52.0);
        this.setLayoutY(124.0);
        this.setMnemonicParsing(false);
        this.setPrefHeight(200.0);
        this.setPrefWidth(200.0);
        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(2);
        KeyValue startKeyValue = new KeyValue(this.translateXProperty(), 600);
        KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
        KeyValue endKeyValue = new KeyValue(this.translateXProperty(), 0.0 * 600);
        KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);
        timeline = new Timeline(startKeyFrame, endKeyFrame);      
        timeline.setCycleCount(1);
    }
}
