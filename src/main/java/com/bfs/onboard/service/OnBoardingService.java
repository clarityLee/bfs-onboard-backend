package com.bfs.onboard.service;

import com.bfs.onboard.dao.UserDao;
import com.bfs.onboard.domain.*;
import com.bfs.onboard.domain.requestDto.OnBoardingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OnBoardingService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public boolean save(String username, OnBoardingDto form) {
        User user = userDao.findByName(username);
        if (user.getId() == null)
            return false;

        // Todo: set data to domain object and save them to database
        Person person = new Person();
        Employee employee = new Employee();
        Address address = new Address();
        Contact emergencyContact = new Contact();

        return true;
    }
}
