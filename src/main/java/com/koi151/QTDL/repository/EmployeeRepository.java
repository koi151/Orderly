package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Procedure(procedureName = "createEmployee")
    Long createEmployee(
        @Param("p_full_name") String fullName,
        @Param("p_email") String email,
        @Param("p_phone") String phone,
        @Param("p_password") String password,
        @Param("p_role_id") Long roleId
    );

    @Procedure(procedureName = "updateEmployee")
    void updateEmployee(
        @Param("p_employee_id") Long employeeId,
        @Param("p_full_name") String fullName,
        @Param("p_email") String email,
        @Param("p_phone") String phone,
        @Param("p_password") String password,
        @Param("p_role_id") Long roleId
    );

    List<Employee> getAllByDeleted(Boolean deleted);
    Optional<Employee> findByEmployeeIdAndDeleted(Long id, Boolean deleted);
    Boolean existsByEmailOrPhone(String email, String phone);
    Boolean existsByFullNameAndEmailAndEmployeeIdNot(String fullName, String email, Long id);
}
