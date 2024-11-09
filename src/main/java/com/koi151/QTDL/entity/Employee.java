package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends BaseEntityNoDefaultVal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "password", length = 100)
    private String password;
}
