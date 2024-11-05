package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleIdAndDeleted(Long id, Boolean deleted);
    Boolean existsByRoleName(String ten);
    Boolean existsByRoleNameAndRoleIdNot(String name, Long id);
    @Query("SELECT COUNT(e) FROM employee e WHERE e.role.roleId = :roleId")
    Long countEmployeesByRoleId(@Param("roleId") Long roleId);
}
