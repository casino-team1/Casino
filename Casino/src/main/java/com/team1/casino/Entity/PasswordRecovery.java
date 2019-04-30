/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.database.Connection.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nick Flückiger
 */
public class PasswordRecovery {

    private String username;

    public PasswordRecovery(String username) {
        this.username = username;
    }

    private String getEmailAdress() throws SQLException {
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        String userEmail = query.runQueryWithReturn("SELECT email FROM user WHERE username = ? ", this.username).get(0);
        return userEmail;
    }

    private String randomPassword(int length) {
        String newPasswordstring = "";
        while (newPasswordstring.length() < length - 3) {
            newPasswordstring += "" + String.valueOf((char) (97 + new Random().nextInt(26)));
        }
        while (newPasswordstring.length() < length) {
            newPasswordstring += String.valueOf(new Random().nextInt(100));
        }
        return newPasswordstring;
    }

    public void handlePasswordRecovery() {
        String passwordPlainText = randomPassword(9);
        String newPasswordHash = UserUtil.getHashedPassword(passwordPlainText);
        String statement = "Update user SET password = ? WHERE username = ?";
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        query.runQueryWithoutReturn(statement, newPasswordHash + ";-" + this.username);
        sendMail(passwordPlainText);
    }

    private void sendMail(String newPlainTextPassword) {
        Thread thread = new Thread(() -> {
            Session session = MailConfig.getSessionByProperty(MailConfig.getMailProperties());
            try {
                String emailAdresse = getEmailAdress();
                if (emailAdresse.equals("") || emailAdresse.isEmpty()) {
                    System.out.println("Invalid username");
                } else {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("mountainviewcasino@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(emailAdresse));
                    message.setSubject("Ihr neues Passwort für das MountainView Casino");
                    message.setText(String.format("Sehr geehrter %s,\n da Sie angegeben haben, das Sie ihr Passwort vergessen haben, senden wir ihnen nun ein neues.\n\nIhr Passwort: %s", this.username, newPlainTextPassword));
                    Transport.send(message);
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                Logger.getLogger(PasswordRecovery.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        thread.start();
    }
}
