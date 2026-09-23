package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.StudentAssignment;


public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long>{
     public List<StudentAssignment>  findByStudentId(Long studentId);
}
