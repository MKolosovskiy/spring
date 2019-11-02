package com.shop.service.impl;

import com.shop.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Transport;

@Service
@Transactional
public class MailServiceImpl implements MailService {

    private final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendCode(String code, String email) {
        final String username = "temail884@gmail.com";
        final String password = "12Qwert12";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Single-use code to confirm the purchase");
            message.setText("Your confirmation code: " + code);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("The code wasn't send");
        }
    }
}
