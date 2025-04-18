package it.epicode.Gestione_viaggi_aziendali.common;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String[] recipients = to.split("\\s*[,;]\\s*");
        helper.setTo(recipients);

        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);

        System.out.println("Email inviata con successo a " + to);
    }

    public void sendEmailWhithAttachment(String to, String subject, String body, byte[] attachmentBytes, String attachmentName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String[] recipients = to.split("\\s*[,;]\\s*");
        helper.setTo(recipients);

        helper.setSubject(subject);
        helper.setText(body, true);
        helper.addAttachment(attachmentName, new ByteArrayResource(attachmentBytes));

        mailSender.send(message);

        System.out.println("Email inviata con successo a " + to + " con allegato: " + attachmentName);
    }
}
