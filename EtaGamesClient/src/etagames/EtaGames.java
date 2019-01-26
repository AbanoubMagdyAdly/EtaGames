/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames;

import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author a-tarek
 */
public class EtaGames extends Application {
    public static BorderPane bp;
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer newMediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        bp = new BorderPane();
        SplashScreenPane Ss = new SplashScreenPane();
        bp.setCenter(Ss);
        
//        URL resource = getClass().getResource("StayTheNight.mp3");
//        Media media = new Media(resource.toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
     
        primaryStage.setScene(new Scene(bp, 750, 640));
        primaryStage.setResizable(false);
        primaryStage.show();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException ex) {
//        }
        SigninPane s = new SigninPane();
        s.setPadding(new Insets(0, 10, 0, 10));
        bp.setLeft(s);
        ModeSelectionPane fs = new ModeSelectionPane();
        bp.setCenter(fs);
        BaseTurn baseTurn=new BaseTurn();
        bp.setBottom(baseTurn);
        
//        SigninPane sb = new SigninPane();
//        XOGame xo = new XOGame(new OfflinePlayer(true),new OfflinePlayer(false));
//        BorderPane bp = new BorderPane();
//        bp.setCenter(xo.getGameBoard().getVisualGameBoard());
//        bp.setLeft(sb);        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
