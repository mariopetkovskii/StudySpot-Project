package com.example.studyspotbackend.helperfunctinos;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.exceptions.CourseNotFoundException;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.quiz.QuizResultsRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.example.studyspotbackend.security.SecurityConstants.SECRET;

public class HelperFunction {
    private final static String host = "localhost";
    private final static int port = 1025;
    private final static String sender = "sender@example.com";

    public static String decodeJwtToGetEmail(String jwtToken) {
        String token = jwtToken.substring(7); // remove "Bearer " prefix
        String email = null;


        try {
            email = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            ;
        } catch (JWTVerificationException e) {
            // handle verification exception
        }
        return email;
    }

    public static void sendRegistrationEmail(String recipient, String tokenValue) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, null);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Account registration");
            message.setText("Click here to confirm your account : " + "" +
                    "http://localhost:8080/rest/user/confirm-account?token=" + tokenValue);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
