package com.bfs.onboard.service;

import com.bfs.onboard.dao.RegistrationTokenDao;
import com.bfs.onboard.dao.RoleDao;
import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.dao.impl.BasicTemplate;
import com.bfs.onboard.domain.RegistrationToken;
import com.bfs.onboard.domain.Role;
import com.bfs.onboard.domain.User;
import com.bfs.onboard.domain.UserRole;
import com.bfs.onboard.mail.MailService;
import com.bfs.onboard.domain.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.bfs.onboard.constant.Constant.REGISTRATION_TOKEN_VALID_DURATION_Days;

@Service
@Transactional
public class RegistrationService {

    private MailService mailService;

    private BasicTemplate template;
    private RegistrationTokenDao registrationTokenDao;
    private RoleDao roleDao;
    private UserDao userDao;

    @Autowired
    @Qualifier("hibernateTemplate")
    public void setTemplate(BasicTemplate template) {
        this.template = template;
    }
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    @Autowired
    public void setRegistrationTokenDao(RegistrationTokenDao registrationTokenDao) {
        this.registrationTokenDao = registrationTokenDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     *
     * @param userId HR id (who send the mail)
     * @param email email to receive registration token
     * @return an url linking to registration page
     */
    public String sendRegisterToken(Integer userId, String email) {
        String token = UUID.randomUUID().toString();
        RegistrationToken rgsToken = new RegistrationToken();
        rgsToken.setToken(token);
        rgsToken.setValidDuration(LocalDateTime.now().plusDays(REGISTRATION_TOKEN_VALID_DURATION_Days));
        rgsToken.setEmail(email);
        rgsToken.setCreatedBy(userId);
        template.save(rgsToken);

        return mailService.sendRegisterToken(email, token);
    }

    public boolean sendRegisterToken(String username, String email) {
        User user = userDao.findByName(username);
        return !sendRegisterToken(user.getId(), email).isEmpty();
    }

    public RegistrationResponse register(String email, String token, String username, String password) {

        RegistrationResponse response = new RegistrationResponse();

        RegistrationToken registrationToken = registrationTokenDao.findByToken(token);
        if (registrationToken.getId() == null) {
            response.setToken("Invalid token");
            return response;
        }
        if (registrationToken.getValidDuration().isBefore(LocalDateTime.now())) {
            response.setToken("Token expired");
            return response;
        }
        if (!registrationToken.getEmail().equals(email)) {
            response.setEmail("Invalid email for token");
            return response;
        }
        if (userDao.exist(username)) {
            response.setUsername("Username has been taken.");
            return response;
        }

        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateDate(now);
        user.setModificationDate(now);
        template.save(user);

        Role role = roleDao.findByRole("candidate");

        UserRole ur = new UserRole();
        ur.setUser(user);
        ur.setRoleID(role.getId());
        ur.setActiveFlag(true);
        ur.setCreateDate(now);
        ur.setModificationDate(now);
        ur.setLastModificationUser(1);
        template.save(ur);

        response.setSuccess(true);
        return response;
    }
}
