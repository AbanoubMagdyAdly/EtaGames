package etagames;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;

public class SigninPane extends VBox {

    protected final ImageView imageView;

    protected final Label label;
    protected final TextField textField;
    protected final Label label0;
    protected final PasswordField textField0;
    protected final Button button;
    protected final Button button0;

    public SigninPane() {
        imageView = new ImageView();
        label = new Label();
        textField = new TextField();
        label0 = new Label();
        textField0 = new PasswordField();;
        button = new Button();
        button0 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(150.0);

        label.setText("Player Name :");

        textField.setPrefHeight(25.0);
        textField.setPrefWidth(286.0);

        label0.setLayoutX(10.0);
        label0.setLayoutY(10.0);
        label0.setText("Password :");

        textField0.setLayoutX(10.0);
        textField0.setLayoutY(27.0);
        textField0.setPrefHeight(25.0);
        textField0.setPrefWidth(286.0);

        button.setMnemonicParsing(false);
        button.setText("Login");
        button.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

        button0.setMnemonicParsing(false);
        button0.setText("Signin");
        button0.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

//        button.setOnAction(e->{
//                   SignIn.ps.println(textField.getText());
//                  SignIn S=new SignIn();
//            try {
//                System.out.println(SignIn.dis.readLine());
//            } catch (IOException ex) {
//                System.out.println("Not Welcome");
//            }
//            });
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(150.0);
        imageView.setImage(new Image(getClass().getResource("backgroud.jpg").toExternalForm()));
        
        
        getChildren().add(label);
        getChildren().add(textField);
        getChildren().add(label0);
        getChildren().add(textField0);
        getChildren().add(button);
        getChildren().add(button0);
        setBackground(Background.EMPTY);
        setStyle("Color.TRANSPARENT");
        if (isReachable()) {
            setDisable(false);
        } else {
            setDisable(true);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Connection : Failed");
            alert.setContentText("No internet connection!");
            alert.showAndWait();

        }

    }

    public static boolean isReachable() {
        try {
            Runtime run = Runtime.getRuntime();
            Process proc = run.exec("ping -n 1 208.67.222.222");
            int returnVal = proc.waitFor();
            boolean connected = (returnVal == 0);
            return connected;
        } catch (IOException | InterruptedException ex) {
            return false;
        }
    }
}
