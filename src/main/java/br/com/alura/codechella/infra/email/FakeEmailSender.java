package br.com.alura.codechella.infra.email;

import br.com.alura.codechella.domain.email.EmailSender;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class FakeEmailSender implements EmailSender {

    @Async
    public void send(String recipient, String subject, String message) {
        System.out.println("===== Simulating email sending =====");
        System.out.println("Recipient: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }

}
