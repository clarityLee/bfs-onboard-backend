package com.bfs.onboard.service;

import com.bfs.onboard.dao.ContactDao;
import com.bfs.onboard.dao.EmployeeDao;
import com.bfs.onboard.dao.PersonalDocumentDao;
import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.dao.impl.BasicTemplate;
import com.bfs.onboard.domain.*;
import com.bfs.onboard.domain.requestDto.AddressDto;
import com.bfs.onboard.domain.requestDto.EditEmploymentDto;
import com.bfs.onboard.domain.requestDto.EditPersonDto;
import com.bfs.onboard.domain.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class InfoService {

    private BasicTemplate template;
    private EmployeeDao employeeDao;
    private ContactDao contactDao;
    private PersonalDocumentDao personalDocumentDao;
    private UserDao userDao;
    @Autowired
    @Qualifier("hibernateTemplate")
    public void setTemplate(BasicTemplate template) {
        this.template = template;
    }
    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    @Autowired
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
    @Autowired
    public void setPersonalDocumentDao(PersonalDocumentDao personalDocumentDao) {
        this.personalDocumentDao = personalDocumentDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public EmployeeResponse getEmployeeDetailsByEmployeeId(Integer employeeId) {
        Employee e = employeeDao.fetchDetail(employeeId);
        Person p = e.getPerson();
        User u = p.getUser();
        return getEmployeeResponse(e, p, u);
    }

    public EmployeeResponse getEmployeeDetailsByUser(String username) {
        User u = userDao.findByName(username);
        u = userDao.fetchDetail(u.getId());
        Person p = u.getPerson();
        Employee e = p.getEmployee();
        return getEmployeeResponse(e, p, u);
    }

    public EmployeeResponse getEmployeeResponse(Employee e, Person p, User u) {
        List<Contact> contacts = contactDao.fetchListByOwner(p.getId());
        Iterator<Contact> it = contacts.iterator();
        Contact ref = null;
        while(it.hasNext()) {
            Contact c = it.next();
            if (c.getIsReference()) {
                ref = c;
                it.remove();
                break;
            }
        }

        EmployeeResponse h = new EmployeeResponse();
        h.setUser(u.removeMapping());
        h.setEmployee(e.removeMapping());
        h.setPerson(p.removeMapping());
        h.setApplicationWorkFlow(e.getApplicationWorkFlow().removeMapping());
        h.setVisaStatus(e.getVisaStatus().removeMapping());
        h.setReference(ref);
        h.setEmergencyList(contacts);
        h.setPersonalDocuments(personalDocumentDao.getWorkDocsByEmployee(e.getId()));
        h.setAddresses(p.getAddresses());
        return h;
    }

    public void editPerson(EditPersonDto editNameDto) {
        Person person = template.findById(editNameDto.getPersonId(), Person.class);
        person.setFirstName(editNameDto.getFirstName());
        person.setLastName(editNameDto.getLastName());
        person.setPreferredName(editNameDto.getPreferredName());
        person.setDateOfBirth(editNameDto.getDateOfBirth());
        person.setGender(editNameDto.getGender());
        person.setSsn(editNameDto.getSsn());
        template.save(person);
    }

    public void editEmployment(EditEmploymentDto e) {
        Employee employee = employeeDao.fetchDetail(e.getEmployeeId());
        employee.setTitle(e.getTitle());
        employee.setVisaStartDate(e.getVisaStartDate());
        employee.setVisaEndDate(e.getVisaEndDate());
        employee.setStartDate(e.getStartDate());
        employee.setEndDate(e.getEndDate());
        template.save(employee);

        VisaStatus v = employee.getVisaStatus();
        v.setVisaType(e.getWorkAuthorization());;
        template.save(v);
    }

    public void editEContact(
            Integer employeeId, String personalEmail, String workEmail,
            String cellphone, String workPhone) {
        Employee e = employeeDao.fetchDetail(employeeId);
        e.setWorkEmail(workEmail);
        e.setWorkPhone(workPhone);
        template.save(e);

        Person p = e.getPerson();
        p.setCellPhone(cellphone);
        template.save(p);

        User u = p.getUser();
        u.setEmail(personalEmail);
        template.save(u);
    }

    public void editAddress(AddressDto a) {
        Address address = template.findById(a.getAddressId(), Address.class);
        address.setAddressLine1(a.getAddressLine1());
        address.setAddressLine2(a.getAddressLine2());
        address.setCity(a.getCity());
        address.setZipcode(a.getZipcode());
        address.setStateName(a.getStateName());
        address.setStateAbbr(a.getStateAbbr());
        template.save(address);
    }

    
}
