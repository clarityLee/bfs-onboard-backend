package com.bfs.onboard.mail;

import com.bfs.onboard.dao.RegistrationTokenDao;
import com.bfs.onboard.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${registration.page.url}")
    private String registrationUrl;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendRegisterToken(String email, String token) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Beaconfire registration token");

        String link = new StringBuffer(registrationUrl)
                .append("?email=").append(email)
                .append("&token=").append(token).toString();
        msg.setText(link);
        javaMailSender.send(msg);

        return link;
    }
}
