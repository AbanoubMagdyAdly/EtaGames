package etagames;

import etagames.xogame.AIPlayer;
import etagames.xogame.OfflinePlayer;
import etagames.xogame.XOGame;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class LevelSelectionPane extends AnchorPane {

    protected final ImageView imageView;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;

    public LevelSelectionPane() {

        imageView = new ImageView();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        button.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button0.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button1.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(600.0);
        imageView.setFitWidth(600.0);
        imageView.setImage(new Image(getClass().getResource("backgroud.jpg").toExternalForm()));

        AnchorPane.setBottomAnchor(button, 271.0);
        AnchorPane.setLeftAnchor(button, 154.0);
        AnchorPane.setRightAnchor(button, 309.0);
        AnchorPane.setTopAnchor(button, 271.0);
        button.setLayoutX(154.0);
        button.setLayoutY(271.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(58.0);
        button.setPrefWidth(137.0);
        button.setText("Easy");
        button.setFont(new Font("System Bold", 19.0));
        button.setOnAction(e -> {
            XOGame xo = new XOGame(new OfflinePlayer(true), new AIPlayer(false, AIPlayer.Level.EASY));
            EtaGames.bp.setCenter(xo.getVisualGameBoard());
        });

        AnchorPane.setLeftAnchor(button0, 312.0);
        button0.setLayoutX(312.0);
        button0.setLayoutY(271.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(58.0);
        button0.setPrefWidth(137.0);
        button0.setText("Medium");
        button0.setFont(new Font("System Bold", 18.0));
        button0.setOnAction(e -> {
            XOGame xo = new XOGame(new OfflinePlayer(true), new AIPlayer(false, AIPlayer.Level.MEDIUM));
            EtaGames.bp.setCenter(xo.getVisualGameBoard());
        });

        button1.setLayoutX(232.0);
        button1.setLayoutY(368.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(58.0);
        button1.setPrefWidth(137.0);
        button1.setText("Hard");
        button1.setFont(new Font("System Bold", 18.0));
        button1.setOnAction(e -> {
            XOGame xo = new XOGame(new OfflinePlayer(true), new AIPlayer(false, AIPlayer.Level.HARD));
            EtaGames.bp.setCenter(xo.getVisualGameBoard());

        });

        button2.setLayoutX(235.0);
        button2.setLayoutY(520.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(58.0);
        button2.setPrefWidth(137.0);
        button2.setText("Back");
        button2.setFont(new Font("System Bold", 18.0));
        button2.setOnAction(e -> {
            ModeSelectionPane Fs = new ModeSelectionPane();
            EtaGames.bp.setCenter(Fs.getFirstScene());
        });

        getChildren().add(imageView);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(button2);

    }

    public AnchorPane getAiScene() {
        return this;

    }
}
