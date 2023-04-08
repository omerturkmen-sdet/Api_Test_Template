package utils;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class Gmailer {

    public void sendMail(String subject, String mailBody, String firstPathName, String secondPathName) throws Exception {
        String username = "your email address";

        //You need to allow 2 steps verification and use application password not your gmail password
        String password = "your password";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("FROM email address"));
        String address2 = "TO email address";
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address2));

        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mailBody, "text/plain; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        addAttachment(multipart, firstPathName);
        addAttachment(multipart, secondPathName);

        message.setContent(multipart);

        Transport.send(message);
    }

    private static void addAttachment(Multipart multipart, String filename) throws Exception {
        DataSource source = new FileDataSource(filename);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new jakarta.activation.DataHandler(source));

        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
    }


}
