package com.bfs.onboard.mail;

import com.bfs.onboard.dao.RegistrationTokenDao;
import com.bfs.onboard.domain.Employee;
import com.bfs.onboard.domain.Person;
import com.bfs.onboard.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Value("${registration.page.url}")
    private String registrationUrl;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     *
     * @param email
     * @param token
     * @return registration link if success; blank if failed
     */
    public String sendRegisterToken(String email, String token) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Beaconfire registration token");

        String link = new StringBuffer(registrationUrl)
                .append("?email=").append(email)
                .append("&token=").append(token).toString();
        msg.setText(link);
        try {
            javaMailSender.send(msg);
            return link;
        } catch(org.springframework.mail.MailException e) {
            return "";
        }
    }

    public void hireDecision(User user, Person person, Boolean accept, List<String> rejectReason) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Beaconfire onboarding notification");
        String body = "Hi " + person.getFirstName() + ",\n\n";
        if (accept) {
            body += "Congratulations. You're onboarding application has been accepted.";
        } else {
            body += "Your onboarding application was not accepted due the following reason(s):";
            for (String reason : rejectReason) {
                body += reason + "\n";
            }
        }
//        msg.setText(body);
//        try {
//            javaMailSender.send(msg);
//            return link;
//        } catch(org.springframework.mail.MailException e) {
//            return "";
//        }
    }

    public boolean hiringApprove(Employee employee) {
        Person person = employee.getPerson();
        User user = person.getUser();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Beaconfire onboarding notification");
        String body = "Hi " + person.getFirstName() + ",\n\n" +
            "Congratulations. You're onboarding application has been accepted.";
        msg.setText(body);
        try {
            javaMailSender.send(msg);
            return true;
        } catch(org.springframework.mail.MailException e) {
            return false;
        }
    }

    public boolean hiringReject(Employee employee, List<String> rejectReasons) {
        Person person = employee.getPerson();
        User user = person.getUser();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Beaconfire onboarding notification");
        StringBuilder body = new StringBuilder("Hi " + person.getFirstName() + ",\n\n" +
                "Congratulations. You're onboarding application has been accepted.");
        body.append("Your onboarding application was not accepted due the following reason(s):");
        for (String reason : rejectReasons) {
            body.append(reason).append("\n");
        }
        msg.setText(body.toString());
        try {
            javaMailSender.send(msg);
            return true;
        } catch(org.springframework.mail.MailException e) {
            return false;
        }
    }

}
