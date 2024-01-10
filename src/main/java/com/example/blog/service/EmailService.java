package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void confirmRegistrationMail(String receiverEmail,
                                        String receiverName){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("blogemailservice512@gmail.com");
        message.setTo(receiverEmail);
        message.setSubject("Confirmation of Registration");
        message.setText("Dear " + receiverName + ",\n" +
                "\n" +
                "I hope this email finds you well.\n" +
                "\n" +
                "We are delighted to inform you that your registration has been successfully processed. Thank you for choosing to be a part of our Blog.\n" +
                "\n" +
                "If you have any questions or need further assistance, feel free to reply to this email.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Blog Team");

        mailSender.send(message);
    }
}
