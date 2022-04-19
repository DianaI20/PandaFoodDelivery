package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ro.utcluj.pandafooddelivery.model.Order;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;


@Getter
public class EmailComposer {


    private  String subject = "New order was placed!";
    private final String emailContent = "Order with id %d was placed at:\n";
    private Session session;


    public EmailComposer(Session session) {
        this.session = session;
    }

    private String createEmailContent(Order order) {

        StringBuilder emailBody = new StringBuilder(String.format(emailContent, order.getId(), LocalDateTime.now()));
        emailBody.append("Order details:\n");
        emailBody.append(order);
        return emailBody.toString();
    }

    public MimeMessage createMimeMessage(Order order, String from, String to) throws MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(this.createEmailContent(order));
        return  message;
    }
}
