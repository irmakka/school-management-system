package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
public List<Student> findByClassNo(String classNo);
public Optional<Student> findByEmail(String email);
}
