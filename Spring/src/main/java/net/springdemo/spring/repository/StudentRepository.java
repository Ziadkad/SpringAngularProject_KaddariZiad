package net.springdemo.spring.repository;

import net.springdemo.spring.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findBycode(String code);
    List<Student> findByProgramId(String programId);
}
