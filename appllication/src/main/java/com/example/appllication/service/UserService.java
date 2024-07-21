package com.example.appllication.service;


import com.example.appllication.entity.Users;
import com.example.appllication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Users registerUser(Users users) {
        try {
            Users savedUser = userRepository.save(users);
            sendRegistrationEmail(savedUser);
            return savedUser;
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            throw new RuntimeException("Failed to register user", e);
        }
    }


    private void sendRegistrationEmail(Users user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Registration Successful");

        StringBuilder emailContent = new StringBuilder("Dear " + user.getUsername() + ",\n\n");
        emailContent.append("Thank you for registering.\n");

        if (user.getFirstName() == null || user.getLastName() == null) {
            emailContent.append("Please complete your profile by providing the following information:\n");
            if (user.getFirstName() == null) {
                emailContent.append("- First Name\n");
            }
            if (user.getLastName() == null) {
                emailContent.append("- Last Name\n");
            }
        }

        message.setText(emailContent.toString());
        mailSender.send(message);
    }
}
