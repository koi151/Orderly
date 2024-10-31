package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getAllByDeleted(Boolean deleted);

    Boolean existsByEmailOrPhone(String email, String phone);


}
