/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author Nick Flückiger
 */
public class MailConfig {

    public static Properties getMailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    public static Session getSessionByProperty(Properties properties) {
        Session session = Session.getInstance(MailConfig.getMailProperties(),
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mountainviewcasino@gmail.com", "V/Em]dy3`?n\\nW;;");
            }
        });
        return session;
    }
}
