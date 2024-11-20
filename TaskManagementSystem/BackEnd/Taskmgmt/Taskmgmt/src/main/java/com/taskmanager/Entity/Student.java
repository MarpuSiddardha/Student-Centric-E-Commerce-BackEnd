package com.taskmanager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studentdetails")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "CNFMPassword", nullable = false)
    private String cnfpwd;

    public String getCnfpwd() {
        return cnfpwd;
    }

    public void setCnfpwd(String cnfpwd) {
        this.cnfpwd = cnfpwd;
    }
}
