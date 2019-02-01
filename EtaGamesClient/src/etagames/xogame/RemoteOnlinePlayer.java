/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package etagames.xogame;

import etagames.EtaGames;
import etagames.ModeSelectionPane;
import static etagames.xogame.BotPlayer.count;
import static etagames.xogame.BotPlayer.replayArray;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import simpleshare.Move;

/**
 *
 * @author abano
 */
public class RemoteOnlinePlayer extends Player {

//    ObjectInputStream ois;
    DataInputStream dis;
    XOGame game;
    private final GameBoard.CellValue shape;

    public RemoteOnlinePlayer(boolean isCross, Socket remoteSocket) {
        super(isCross);
        shape = (isCross) ? GameBoard.CellValue.x : GameBoard.CellValue.o;
        try {
            dis = new DataInputStream(remoteSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void playTurn(XOGame game) {
        this.game = game;

        Platform.runLater(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String move = dis.readLine();
                        System.out.println("Remote Position: " + move);
                        if (move != null) {
                            game.tick(shape, Integer.parseInt(move));
                            break;
                        } else if (move == null) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            Optional<ButtonType> option;
                            alert.setHeaderText("The other player is offline");
                            alert.setContentText("please rematch with other online");
                            option = alert.showAndWait();
                            if (option.get() == null) {
                                Platform.exit();
                            } else if (option.get() == ButtonType.OK) {
                                ModeSelectionPane fs = new ModeSelectionPane();
                                EtaGames.bp.setCenter(fs);
                            } else {
                                Platform.exit();
                            }
                            break;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        );

    }
}
