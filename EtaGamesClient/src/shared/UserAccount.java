/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;

/**
 *
 * @author a-tarek
 */
public class UserAccount implements Serializable{

    public enum UserStatus {
        BUSY,
        ONLINE,
        OFFLINE
    }
    private String userName;
    private UserStatus userStatus;

    public UserAccount() {

    }

    public UserAccount(String name, UserStatus status) {
        this.userName = name;
        this.userStatus = status;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    public void setUserState(UserStatus status) {
        this.userStatus = status;
    }

    public String toString() {
        return (this.userName + "  " + this.userStatus);
    }
}
