package org.geekhub.kukotin.coursework.service.email;

import org.geekhub.kukotin.coursework.service.exceptions.EmailSendingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@bookwise.com"); // This won't work without a dedicated SMTP server
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MailException e) {
            String errorMessage = "Failed to send email to " + Arrays.toString(message.getTo()) + ": " + e.getMessage();
            throw new EmailSendingException(errorMessage, e);
        }
    }
}
