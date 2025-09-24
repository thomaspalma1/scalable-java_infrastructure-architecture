package br.com.alura.codechella.domain.email;

public interface EmailSender {

    void send(
            String recipient,
            String subject,
            String message
    );

}
