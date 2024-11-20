package com.taskmanager.Repository;

import com.taskmanager.Entity.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Repo extends JpaRepository<Student,Long> {

   <Optional>Student findByEmail(String emailAddress);

   @Query("SELECT s FROM Student s WHERE s.password = :password")
   Optional<Student> findByPassword(@Param("password") String password);

//
//    @Query("select * from studentdetails where emailAddress = ?1")
//    User findByEmail(String emailAddress);
}
