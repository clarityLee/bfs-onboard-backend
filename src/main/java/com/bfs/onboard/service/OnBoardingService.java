package com.bfs.onboard.service;

import com.bfs.onboard.dao.*;
import com.bfs.onboard.dao.impl.BasicTemplate;
import com.bfs.onboard.domain.*;
import com.bfs.onboard.domain.requestDto.ContactDto;
import com.bfs.onboard.domain.requestDto.OnBoardingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OnBoardingService {

    private BasicTemplate template;
    private UnclaimedFileDao unclaimedFileDao;
    private UserDao userDao;

    @Autowired
    @Qualifier("hibernateTemplate")
    public void setTemplate(BasicTemplate daoTemplate) {
        this.template = daoTemplate;
    }
    @Autowired
    public void setUnclaimedFileDao(UnclaimedFileDao unclaimedFileDao) {
        this.unclaimedFileDao = unclaimedFileDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public boolean save(OnBoardingDto f) {
        User user = userDao.findByName(f.getUsername());
        if (user.getId() == null)
            return false;

        //TODO: check user role is candidate

        Person p = new Person();
        p.setFirstName(f.getFirstName());
        p.setLastName(f.getLastName());
        p.setMiddleName(f.getMiddleName());
        p.setPreferredName(f.getPreferredName());
        p.setEmail(f.getEmail());
        p.setCellPhone(f.getCeilPhone());
        // TODO: check alternatePhone = workPhone ?
        p.setAlternatePhone(f.getWorkPhone());
        p.setGender(f.getGender());
        p.setSsn(f.getSsn());
        p.setDateOfBirth(f.getDateOfBirth());
        template.save(p);

        // save user's current address
        template.save(f.getCurrentAddress().toAddress(p.getId()));
        //TODO: check if there are more than 1 address

        //TODO: PersonalDocument: DrivingLicense / WorkAuthorization Enum or Const
        Employee e = new Employee();
        e.setPersonId(p.getId());
        if (StringUtils.hasLength(f.getAvatar())) {
            e.setAvartar(f.getAvatar());
            unclaimedFileDao.deleteByPath(f.getAvatar());
        }
        e.setAvartar(f.getAvatar());
        e.setCar(f.getCar());
        e.setCitizen(f.getCitizen());
        e.setGreenCard(f.getGreenCard());
        if (f.getHasDriveLicense()) {
            e.setDriverLicense(f.getDriveLicenseNumber());
            e.setDriverLicence_ExpirationDate(f.getDriveLicenseExpireDate());

            PersonalDocument pd = new PersonalDocument();
            pd.setEmployeeID(e.getId());
            pd.setPath(f.getDriveLicensePath());
            pd.setTitle("DrivingLicense");
            pd.setCreatedDate(LocalDateTime.now());
            pd.setCreatedBy(user.getId());
            template.save(pd);
        }
        template.save(e);

        String workAuthorization = f.getWorkAuthorization();
        if (StringUtils.hasLength(workAuthorization)) {
            VisaStatus v = new VisaStatus();
            v.setActive(true);
            v.setCreateUser(user.getId());
            v.setVisaType(workAuthorization);
            v.setModificationDate(LocalDateTime.now());
            template.save(v);

            PersonalDocument pd = new PersonalDocument();
            pd.setEmployeeID(e.getId());
            pd.setPath(f.getWorkAuthUploadPath());
            pd.setTitle("WorkAuthorization");
            pd.setCreatedDate(LocalDateTime.now());
            pd.setCreatedBy(user.getId());
            template.save(v);
        }

        // save user's reference contact
        ContactDto ref = f.getReference();
        if (ref != null) {
            Person pc = ref.toPerson();
            template.save(pc);
            template.save(ref.getAddress().toAddress(pc.getId()));
            template.save(ref.toRefContact(pc.getId()));
        }

        // save user's emergency contacts
        List<ContactDto> el = f.getEmergencyList();
        if (el != null && !el.isEmpty()) {
            for (ContactDto c : el) {
                Person pe = c.toPerson();
                template.save(pe);
                template.save(c.getAddress().toAddress(pe.getId()));
                template.save(c.toEmergencyContact(pe.getId()));
            }
        }

        return true;
    }
}
