/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import com.team1.casino.User.UserCentral;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nick Flückiger
 */
public class MailUtil {

    private String emailAdress;
    private String secureCode;
    private String preparedMessage;

    private String signature = "Wilkommen zum MountainnView Casino in Los Angeles.\n Wir sind erreichbar unter:\nEmail: mountainviewcasino@gmail.com \nTel: In Arbeit \n Wir bieten Ihnen die besten Spiele und die besten Gewinnchangen der ganzen Stadt. Bei uns sitzen Sie nie auf dem Trocknen.";

    public MailUtil(String emailAdress, String secureCode, String preparedMessage) {
        this.emailAdress = emailAdress;
        this.secureCode = secureCode;
        this.preparedMessage = preparedMessage;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public String getPreparedMessage() {
        return preparedMessage;
    }

    public void sendRegistrationMail() {
        Thread thread = new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("mountainviewcasino@gmail.com", "V/Em]dy3`?n\\nW;;");
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("mountainviewcasino@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(emailAdress));
                message.setSubject("Welcome to Casino MountainView");
                message.setText("Sehr geehrter " + UserCentral.getInstance().getUser().getUsername() + "\n Wir freuen Sie auf unseren kleinen Platform wilkommen zu heissen\nIn diesem Email ist ihr Zugangscode mitgelegt.\n Wir danken für Ihr vertrauen und wünschen Ihnen einen schönen Tag. Ihr Zugangscode lautate:" + secureCode + "\n\n" + signature);
                Transport.send(message);
                System.out.println("Message sent");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
