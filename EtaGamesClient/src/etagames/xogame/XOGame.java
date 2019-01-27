package etagames.xogame;

import etagames.EtaGames;
import etagames.ModeSelectionPane;
import etagames.xogame.GameBoard.CellValue;
import static etagames.xogame.XOGame.GameState.playable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a-tarek
 */
public class XOGame {

    public enum GameMode {
        OFFLINE_MP,
        OFFLINE_EASY,
        OFFLINE_MEDIUM,
        OFFLINE_HARD,
        ONLINE,
        REPLAY
    }

    public enum GameState {
        player1_win,
        player2_win,
        draw,
        playable,
    }
    private Player p1, p2, currentPlayer;
    private GameBoard gameBoard;
    private VisualGameBoard vGameBoard;
    private GameState gameState;
    private List<Image> images;
    private int turnsLeft = 9;
    WritableImage image = new WritableImage(750, 600);
    public static boolean isBot;

    public XOGame(Player p1, Player p2) {
        System.out.println("etagames.xogame.XOGame.<init>()");
        this.p1 = p1;
        this.p2 = p2;
        this.currentPlayer = this.p1;
        this.gameState = GameState.playable;
        if (p1 instanceof BotPlayer) {
            isBot = true;
        }
        else
            isBot=false;
        gameBoard = new GameBoard();
        vGameBoard = new VisualGameBoard();


        /*  
            GAME LOOP
        ------------------------------
            While !END OR NO ONE WINS
                Player 1 makes a move 
                Check if it is a winner move ? end : swit    Player 1 makes a move ch the players 
         */
 /*
            Player 
          -----------------------
                if turn is true 
               add events listeners on all cells
        
         */
        start();
    }

    public void tick(CellValue cv, int position) {

        BotPlayer.replayArray[9 - turnsLeft] = position;
        if (this.gameState == playable && turnsLeft > 0) {
            gameBoard.setCellOnBoard(cv, position);
            vGameBoard.setVCellOnBoard(cv, position);
            gameState = gameBoard.checkGameBoard(cv);
        }
        if (this.gameState == playable && turnsLeft == 0) {
            this.gameState = GameState.draw;
        }
        if (this.gameState != GameState.playable || turnsLeft == 0) {
            GameEnd();
        }
        //replay();
        turnsLeft--;
        if (this.gameState == playable) {
            currentPlayer = (currentPlayer == p1) ? p2 : p1;
//            turnsLeft--;
            gameState = gameBoard.checkGameBoard(cv);
            System.out.println(Integer.toString(turnsLeft) + "etagames.xogame.XOGame.tick()");
            if (turnsLeft == 0 && gameState == playable) {
                GameEnd();
                return;
            }
            currentPlayer.playTurn(this);
        }

    }

    private void start() {
        currentPlayer.playTurn(this);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public VisualGameBoard getVisualGameBoard() {
        return vGameBoard;
    }

    private void GameEnd() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        ButtonType rematch = new ButtonType("Rematch");
        ButtonType bTM = new ButtonType("Back To Menu");
        ButtonType replay = new ButtonType("Replay");
        alert.getButtonTypes().clear();
        if (!(p1 instanceof BotPlayer)) {
            alert.getButtonTypes().addAll(rematch, replay, bTM);
        } else {
            alert.getButtonTypes().addAll(bTM);
        }
        Optional<ButtonType> option;
        if (this.gameState == GameState.playable || this.gameState == GameState.playable) {
            alert.setHeaderText("We Don't Have A Winner");
            alert.setContentText("Draw");
        } else {
            alert.setHeaderText("We Have A Winner");
            alert.setContentText("The Winner is : " + this.gameState);
        }
        option = alert.showAndWait();
        if (option.get() == null) {
            Platform.exit();
        } else if (option.get() == rematch) {
            XOGame xo = new XOGame(this.p1, this.p2);
            EtaGames.bp.setCenter(xo.getVisualGameBoard());
        } else if (option.get() == bTM) {
            ModeSelectionPane fs = new ModeSelectionPane();
            EtaGames.bp.setCenter(fs);
        } else if (option.get() == replay) {
            XOGame xo = new XOGame(new BotPlayer(true), new BotPlayer(false));
            EtaGames.bp.setCenter(xo.getVisualGameBoard());
        } else {
            Platform.exit();
        }
    }

    public void replay() {
        WritableImage snapshot = EtaGames.bp.getCenter().snapshot(new SnapshotParameters(), image);
        File file = new File("Screen" + (9 - turnsLeft) + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
        }
        //images.add(image);
    }

//    public void showReplay() {
//        int i = 0;
//        while (i < 9 - turnsLeft) {
//
//            ImageView image = new ImageView();
//            File file = new File("Screen" + i + ".png");
//            Image img = new Image(file.toURI().toString());
//            image.setImage(img);
//            EtaGames.bp.setCenter(image);
//            Timeline timeline = new Timeline(new KeyFrame(
//                    Duration.millis(2500)));
//            timeline.play();
//            i++;
//        }
//
//    }
}
