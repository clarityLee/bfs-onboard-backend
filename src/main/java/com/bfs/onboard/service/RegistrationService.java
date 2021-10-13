package com.bfs.onboard.service;

import com.bfs.onboard.dao.RegistrationTokenDao;
import com.bfs.onboard.dao.RoleDao;
import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.dao.UserRoleDao;
import com.bfs.onboard.domain.RegistrationToken;
import com.bfs.onboard.domain.Role;
import com.bfs.onboard.domain.User;
import com.bfs.onboard.domain.UserRole;
import com.bfs.onboard.mail.MailService;
import com.bfs.onboard.response.RegistrationResponse;
import com.bfs.onboard.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.bfs.onboard.constant.Constant.REGISTRATION_TOKEN_VALID_DURATION_Days;

@Service
public class RegistrationService {


    private TokenGenerator tokenGenerator;
    private MailService mailService;

    private RegistrationTokenDao registrationTokenDao;
    private RoleDao roleDao;
    private UserDao userDao;
    private UserRoleDao userRoleDao;

    @Autowired
    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
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

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     *
     * @param userId HR id (who send the mail)
     * @param email email to receive registration token
     * @return an url linking to registration page
     */
    @Transactional
    public String sendRegisterToken(Integer userId, String email) {
        String token = tokenGenerator.registration();
        RegistrationToken rgsToken = new RegistrationToken();
        rgsToken.setToken(token);
        rgsToken.setValidDuration(LocalDateTime.now().plusDays(REGISTRATION_TOKEN_VALID_DURATION_Days));
        rgsToken.setEmail(email);
        rgsToken.setCreatedBy(userId);
        registrationTokenDao.save(rgsToken);

        return mailService.sendRegisterToken(email, token);
    }

    @Transactional
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
        userDao.save(user);

        Role role = roleDao.findByRole("candidate");

        UserRole ur = new UserRole();
        ur.setUser(user);
        ur.setRoleID(role.getId());
        ur.setActiveFlag(true);
        ur.setCreateDate(now);
        ur.setModificationDate(now);
        ur.setLastModificationUser(1);
        userRoleDao.save(ur);

        response.setSuccess(true);
        return response;
    }
}
