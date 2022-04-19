package ro.utcluj.pandafooddelivery.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.Order;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Slf4j
public class EmailSender {

    private EmailComposer emailComposer;
    private Properties properties;
    private Session session;

    private void setProperties(){

        String host = "smtp.gmail.com";
        this.properties = System.getProperties();
        this.properties.put("mail.smtp.host", host);
        this.properties.put("mail.smtp.port", "465");
        this.properties.put("mail.smtp.ssl.enable", "true");
        this.properties.put("mail.smtp.auth", "true");
        this.session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("libraryproject40@gmail.com", "csfNyLxYA7bU7Wv");
            }
        });
        this.session.setDebug(true);
        this.emailComposer = new EmailComposer(session);
    }
    public void sendEmail(Order order, String to) {

        String from = "noreply@gmail.com";
        this.setProperties();
        try {
            MimeMessage message = emailComposer.createMimeMessage(order,from,to);
            Transport.send(message);
        } catch (MessagingException mex) {
            log.error("Error when trying to send the message.");
        }
        log.info(String.format("Email sent successfully to %s", to));

    }

}