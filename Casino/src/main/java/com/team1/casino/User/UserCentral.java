/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

/**
 *
 * @author Nick Flückiger
 */
public class UserCentral {

    private static UserCentral userCentral;

    public static UserCentral getInstance() {
        if (userCentral == null) {
            userCentral = new UserCentral();
        }
        return userCentral;
    }

    private UserCentral() {

    }

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
