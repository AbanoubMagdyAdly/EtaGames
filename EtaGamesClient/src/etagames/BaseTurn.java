package etagames;

import java.net.URL;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;

public class BaseTurn extends AnchorPane {

    protected final Button button;
    protected final Label label;
    protected final Label label0;

    public BaseTurn() {

        button = new Button();
        label = new Label();
        label0 = new Label();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(40.0);
        setPrefWidth(600.0);

        button.setLayoutX(620.0);
        button.setLayoutY(4.0);
        button.setMnemonicParsing(false);
        button.setText("Change Style");
        button.setFont(new Font(15.0));
        button.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button.setOnAction(e -> {
            if (!EtaGames.bp.getStyle().equals("-fx-background-color: black;")) {
                EtaGames.bp.setStyle("-fx-background-color: black;");
                URL resource = getClass().getResource("Highbeat.mp3");
                Media media = new Media(resource.toString());
                EtaGames.mediaPlayer.stop();
                EtaGames.newMediaPlayer =new MediaPlayer(media);
                EtaGames.newMediaPlayer.play();
            } else {
                EtaGames.bp.setStyle("-fx-background-color: gary;");
                EtaGames.newMediaPlayer.stop();
                EtaGames.mediaPlayer.play();
            }
        });

        label.setLayoutX(33.0);
        label.setLayoutY(6.0);
        label.setText("Player turn : ");
        label.setFont(new Font(20.0));

        label0.setLayoutX(89.0);
        label0.setLayoutY(12.0);
        label0.setText("");

        getChildren().add(button);
        getChildren().add(label);
        getChildren().add(label0);

    }
}
