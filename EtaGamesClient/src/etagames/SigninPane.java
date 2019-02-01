package etagames;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;
import shared.GetFriendsListRequest;
import shared.GetFriendsListResponse;
import shared.Response;
import shared.ResponseType;
import shared.SignInRequest;
import shared.UserAccount;

public class SigninPane extends VBox {

    protected final ImageView imageView;

    protected final Label nameLabel;
    protected final TextField nameField;
    protected final Label passwordLabel;
    protected final PasswordField passwordField;
    protected final Button loginButton;
    protected final Button registerButton;
    protected final Label statusLabel;
    protected final FriendsListVBox friendsVbox;

    public SigninPane() {
        imageView = new ImageView();
        nameLabel = new Label();
        nameField = new TextField();
        passwordLabel = new Label();
        passwordField = new PasswordField();;
        loginButton = new Button();
        registerButton = new Button();
        statusLabel = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(150.0);

        nameLabel.setText("Player Name :");

        nameField.setPrefHeight(25.0);
        nameField.setPrefWidth(286.0);

        passwordLabel.setLayoutX(10.0);
        passwordLabel.setLayoutY(10.0);
        passwordLabel.setText("Password :");

        passwordField.setLayoutX(10.0);
        passwordField.setLayoutY(27.0);
        passwordField.setPrefHeight(25.0);
        passwordField.setPrefWidth(286.0);

        loginButton.setMnemonicParsing(false);
        loginButton.setText("Login");
        loginButton.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

        registerButton.setMnemonicParsing(false);
        registerButton.setText("Signup");
        registerButton.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);-fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;");

//        button.setOnAction(e->{
//                   SignIn.ps.println(nameField.getText());
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

        getChildren().add(nameLabel);
        getChildren().add(nameField);
        getChildren().add(passwordLabel);
        getChildren().add(passwordField);
        getChildren().add(loginButton);
        getChildren().add(registerButton);
        getChildren().add(statusLabel);
        setBackground(Background.EMPTY);
        setStyle("Color.TRANSPARENT");

        loginButton.setOnAction((e) -> {

            ServerConnection sc = new ServerConnection();
            sc.sendRequest(new SignInRequest(nameField.getText(), passwordField.getText()));
            Response loginResponse = sc.getResponse();
            System.out.println("20");
            System.out.println(loginResponse.getType());
            String message;

            // if the user logged in succesfully display his friends list 
            if (loginResponse.getType() == ResponseType.SUCCESS) {
                message = "Welcome " + nameField.getText();

                // Start a new connection to the server to retrieve the list of the friends
                ServerConnection sc2 = new ServerConnection();

                // giving the GetFriendsListRequest the cuurent user name. 
                sc2.sendRequest(new GetFriendsListRequest(nameField.getText()));

                GetFriendsListResponse getFriendsResponse = (GetFriendsListResponse) sc2.getResponse();
                
                
                // putting list in friendsVBox 
                friendsVbox = new FriendsListVBox(getFriendsResponse.getFriends());
                // Swapping the vbox view to the friendslist view 
                
                //EtaGames.bp.setLeft(friendsVbox);
                
                
            } else {
                message = "Name or password do not match";
            }
            statusLabel.setText(message);

        });
//        if (isReachable()) {
//            setDisable(false);
////        } else {
//            setDisable(true);
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Test Connection");
//            alert.setHeaderText("Connection : Failed");
//            alert.setContentText("No internet connection!");
//            alert.showAndWait();
//
//        }

    friendsVbox = null;
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
