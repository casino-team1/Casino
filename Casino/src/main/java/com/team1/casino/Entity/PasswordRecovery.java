/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import com.team1.casino.User.UserUtil;

/**
 *
 * @author Nick Flückiger
 */
public class PasswordRecovery {

    private String username;

    public PasswordRecovery(String username) {
        this.username = username;
    }

    private String getEmailAdress() {
        return "";
    }

    private String randomPassword(int length) {
        return "";
    }

    private void writePasswordToDatabase(String newPassword) {
        String newPasswordHash = UserUtil.getHashedPassword(newPassword);
    }

    public void sendMail() {

    }
}
