/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.UserAccount;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import shared.GameInvitationRequest;
import static shared.UserAccount.UserStatus.ONLINE;
import static shared.UserAccount.UserStatus.OFFLINE;
import static shared.UserAccount.UserStatus.BUSY;

/**
 *
 * @author a-tarek
 */
class FriendsListVBox extends VBox {

    FriendsListVBox(ArrayList<UserAccount> friends) {
        for (UserAccount friend : friends) {
            System.out.println(friend.toString());

            // ** Label should be converted to a string and button 
            // ** invite button should be disabled if userState is busy 
            Label nameLabel = new Label(friend.getUserName());

            this.getChildren().add(nameLabel);

            Button inviteButton = new Button("+");
            this.getChildren().add(inviteButton);
            if (friend.getUserStatus() == ONLINE) {
                inviteButton.setDisable(false);
                inviteButton.setStyle("-fx-background-color: GREENYELLOW");

            } else if (friend.getUserStatus() == BUSY) {
                inviteButton.setDisable(true);
                inviteButton.setStyle("-fx-background-color: ORANGERED");

            } else {
                inviteButton.setDisable(true);
                nameLabel.setTextFill(Color.DARKGREY);
                inviteButton.setStyle("-fx-background-color: DARKGRAY");
            }
            inviteButton.setOnAction((e) -> {
                ServerConnection sc = new ServerConnection();
                sc.sendRequest(new GameInvitationRequest(nameLabel.getText()));
            });
        }
    }

}
