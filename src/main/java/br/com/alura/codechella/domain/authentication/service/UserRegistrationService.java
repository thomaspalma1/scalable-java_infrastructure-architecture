package br.com.alura.codechella.domain.authentication.service;

import br.com.alura.codechella.domain.BusinessRuleException;
import br.com.alura.codechella.domain.authentication.entity.AccessProfile;
import br.com.alura.codechella.domain.authentication.entity.User;
import br.com.alura.codechella.domain.authentication.repository.AccessProfileRepository;
import br.com.alura.codechella.domain.authentication.repository.UserRepository;
import br.com.alura.codechella.domain.authentication.vo.UserRegistrationData;
import br.com.alura.codechella.domain.authentication.vo.UserData;
import br.com.alura.codechella.domain.email.EmailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final AccessProfileRepository accessProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    public UserRegistrationService(
            UserRepository userRepository,
            AccessProfileRepository accessProfileRepository,
            PasswordEncoder passwordEncoder,
            EmailSender emailSender) {
        this.userRepository = userRepository;
        this.accessProfileRepository = accessProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    public UserData registerUser(UserRegistrationData data) {
        if (userRepository.existsByEmailOrCpf(data.email(), data.cpf())) {
            throw new BusinessRuleException("Email and/or CPF already registered!");
        }

        var randomPassword = generateRandomNumericPassword();
        var hashedPassword = passwordEncoder.encode(randomPassword);
        var buyerProfile = loadBuyerProfile();
        var user = new User(data, hashedPassword, Set.of(buyerProfile));

        userRepository.save(user);
        sendEmail(user, randomPassword);

        return new UserData(user);
    }

    private String generateRandomNumericPassword() {
        return new Random()
                .ints(0, 10)
                .limit(6)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
    }

    private AccessProfile loadBuyerProfile() {
        return accessProfileRepository.findByName(AccessProfile.BUYER);
    }

    private void sendEmail(User user, String password) {
        var recipient = user.getEmail();
        var subject = "Welcome to CodeChella!";
        var message = "Your registration was completed! Access the site with your email and this password: " + password;
        emailSender.send(recipient, subject, message);
    }

}
