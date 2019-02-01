package etagames;

import etagames.xogame.LocalOnlinePlayer;
import etagames.xogame.OfflinePlayer;
import etagames.xogame.RemoteOnlinePlayer;
import etagames.xogame.XOGame;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ModeSelectionPane extends AnchorPane {

    protected final ImageView imageView;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    public static BorderPane XOBP;

    public ModeSelectionPane() {

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
        button.setText("SinglePlayer");
        button.setFont(new Font("System Bold", 19.0));
        button.setOnAction(e -> {
            LevelSelectionPane As = new LevelSelectionPane();
            EtaGames.bp.setCenter(As.getAiScene());
        });

        AnchorPane.setLeftAnchor(button0, 312.0);
        button0.setLayoutX(312.0);
        button0.setLayoutY(271.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(58.0);
        button0.setPrefWidth(137.0);
        button0.setText("MultiPlayer");
        button0.setFont(new Font("System Bold", 18.0));
        button0.setOnAction(e -> {
            XOGame xo = new XOGame(new OfflinePlayer(true), new OfflinePlayer(false));
            EtaGames.bp.setCenter(xo.getVisualGameBoard());

        });

        button1.setLayoutX(232.0);
        button1.setLayoutY(368.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(58.0);
        button1.setPrefWidth(137.0);
        button1.setText("Online");
        button1.setFont(new Font("System Bold", 18.0));

        button1.setOnAction(e -> {
            try {
                Socket socket = new Socket("172.16.1.66", 9090);
                BaseTurn.label.setText("Waiting....");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String token = dis.readLine();
                
                    System.out.println(token);
                if ("X".equals(token)) {
                    BaseTurn.label.setText("Turn  Player : X");
                    System.out.println("ْْْْْْ");
                    XOGame xo = new XOGame(new LocalOnlinePlayer(true, socket), new RemoteOnlinePlayer(false, socket));
                    EtaGames.bp.setCenter(xo.getVisualGameBoard());
                } else if ("O".equals(token)){
                    XOGame xo = new XOGame(new RemoteOnlinePlayer(true, socket), new LocalOnlinePlayer(false, socket));
                    EtaGames.bp.setCenter(xo.getVisualGameBoard());
                }
                else{
                    
                }

            } catch (IOException ex) {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                            Optional<ButtonType> option;
                            alert.setHeaderText("The server is out");
                            alert.setContentText("Please Retry LATER !!!");
                            option = alert.showAndWait();
//                            if (option.get() == null) {
//                                Platform.exit();
//                            } else if (option.get() == ButtonType.OK) {
//                                ModeSelectionPane fs = new ModeSelectionPane();
//                                EtaGames.bp.setCenter(fs);
//                            } else {
//                                Platform.exit();
//                            }
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        button2.setLayoutX(50.0);
        button2.setLayoutY(520.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(58.0);
        button2.setPrefWidth(150.0);
        button2.setText("Reconnect֍");
        button2.setFont(new Font("System Bold", 18.0));
        button2.setOnAction(e -> {
            if (SigninPane.isReachable()) {
                EtaGames.bp.getLeft().setDisable(false);
                button2.setVisible(false);
            }
        });

        getChildren().add(imageView);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        if (EtaGames.bp.getLeft().isDisabled()) {
            getChildren().add(button2);
        }

    }

    public AnchorPane getFirstScene() {
        return this;

    }
}
