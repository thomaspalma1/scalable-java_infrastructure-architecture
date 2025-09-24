package br.com.alura.codechella.infra.email;

import br.com.alura.codechella.domain.email.EmailSender;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class RealEmailSender implements EmailSender {

    private final JavaMailSender emailSender;

    public RealEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void send(String recipient, String subject, String message) {
        try {
            var email = new SimpleMailMessage();
            email.setFrom("no-reply@codechella.com.br");
            email.setTo(recipient);
            email.setSubject(subject);
            email.setText(message);
            emailSender.send(email);
        } catch (Exception e) {
            throw new RuntimeException("Error sending email!", e);
        }
    }

}
