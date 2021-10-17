package com.bfs.onboard.dao;

import com.bfs.onboard.domain.Employee;

public interface EmployeeDao {
    Employee fetchDetail(Integer employeeId);
    Employee fetchForEmail(Integer employeeId);
}
