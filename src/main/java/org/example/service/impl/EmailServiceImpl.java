package org.example.service.impl;

import org.example.domain.Users;
import org.example.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class EmailServiceImpl implements EmailService {
    static Scanner scanner = new Scanner(System.in);

    static {
        Users users = new Users("Abdulaziz", "abdulaziz1410", "ismoiljonovabdulaziz1410@gmail.com");
    }

    @Override
    public boolean sendEmail(String to, String code) {

        String host = "smtp.gmail.com";
        String port = "587";
        Random random = new Random();

        String randomNumber = String.valueOf(random.nextInt(600000, 999999));

        String username = "ismoiljonovabdulaziz97@gmail.com";
        String password = "bpax xmfb mkgt gnro";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Welcome to our website !");

            message.setText(randomNumber);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            if (randomNumber.equals(code)) {
                return true;
            } else {
                System.out.print("Code is incorrect ❌ Please try again ‼");
                return false;
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}