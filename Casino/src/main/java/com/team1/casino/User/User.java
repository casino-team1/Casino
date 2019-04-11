/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import java.util.Random;

/**
 *
 * @author Nick Flückiger
 */
public abstract class User {

    private int ID;
    private String role;
    private String username;
    private String password;
    private double currentBalance;
    private boolean validated = false;
    private String validationCode = "";
    private String emailAdress;

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public int getID() {
        return ID;
    }

    public abstract void addStat(String gameName, double bet, String result, double amount);

    public String getRole() {
        return role;
    }

    public boolean isValidated() {
        return validated;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isValidUser() {
        boolean isValid = new UserUtil().isValidUser(this);
        this.validated = isValid;
        if (isValid == true) {
            UserUtil util = new UserUtil();
            this.role = util.getUserRoleByUsername(this.username);
            this.ID = util.getIDFromUserByUsername(username);
            UserCentral.getInstance().setUser(this);
        }
        return isValid;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void writeBalanceToDatabase() {

    }

    public boolean isValidValidationCode(String code) {
        return this.validationCode.equals(code);
    }

    public String getValidationCode() {
        if (this.validationCode.equals("")) {
            String secureCode = "";
            while (secureCode.length() < 10) {
                secureCode += String.valueOf(((char) (97 + new Random().nextInt(26))));
            }
            this.validationCode = secureCode;
        }
        return this.validationCode;
    }

    public void setRole(String userRole) {
        this.role = userRole;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public abstract void writeUserToDatabase();

    public void loadUserInformation() {
        UserUtil util = new UserUtil();
        this.currentBalance = util.loadCurrentBalanceFromGivenUsername(username);
        this.ID = util.getIDFromUserByUsername(username);
    }

}
