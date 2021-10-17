package com.bfs.onboard.dao.impl;

import com.bfs.onboard.dao.EmployeeDao;
import com.bfs.onboard.domain.Address;
import com.bfs.onboard.domain.Employee;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class EmployeeDaoHibernateImpl extends BasicTemplate implements EmployeeDao {

    @Override
    public Employee fetchDetail(Integer employeeId) {
        Employee employee = findById(employeeId, Employee.class);
        Hibernate.initialize(employee.getPerson());
        Hibernate.initialize(employee.getPerson().getUser());
        Hibernate.initialize(employee.getPerson().getAddresses());
        Hibernate.initialize(employee.getApplicationWorkFlow());
        Hibernate.initialize(employee.getVisaStatus());
        return employee;
    }

    @Override
    public Employee fetchForEmail(Integer employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = findById(employeeId, Employee.class);
        Hibernate.initialize(employee.getPerson());
        Hibernate.initialize(employee.getPerson().getUser());
        return employee;
    }
}
