package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	Optional<Teacher> findByEmail(String email);

}