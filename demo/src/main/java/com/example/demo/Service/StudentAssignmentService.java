package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentAssignmentDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.StudentAssignment;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.StudentAssignmentMapper;
import com.example.demo.Repository.StudentAssignmentRepository;
import com.example.demo.Repository.StudentRepository;

@Service
public class StudentAssignmentService {
	
	
	private final StudentAssignmentRepository studentAssignmentRepository;
	private final StudentAssignmentMapper studentAssignmentMapper;
	private final StudentRepository stRepo;
	
	
	public StudentAssignmentService(
			StudentAssignmentRepository studentAssignmentRepository, StudentAssignmentMapper studentAssignmentMapper,StudentRepository stRepo) {
		super();
		this.studentAssignmentRepository = studentAssignmentRepository;
		this.studentAssignmentMapper = studentAssignmentMapper;
		this.stRepo=stRepo;
	}


	public List<StudentAssignmentDTO> getStudentAssignments(Long studentId) {

	    List<StudentAssignment> studentAssignments =
	            studentAssignmentRepository.findByStudentId(studentId);

	    return studentAssignments.stream()
	            .map(studentAssignment -> {

	             
					StudentAssignmentDTO dto =
	                        studentAssignmentMapper
	                                .mapStudentToStudentAssignmentDTO(
	                                        studentAssignment);

	                dto.setTitle(
	                		studentAssignment
                            .getAssignment()
                            .getName()
	                		);
	               
	                return dto;
	            })
	            .toList();
	}
	
	
	public StudentAssignmentDTO setAssignmentDelivered(
	        Long studentAssignmentId,
	        boolean delivered) {

	    StudentAssignment studentAssignment =
	            studentAssignmentRepository.findById(studentAssignmentId)
	                    .orElseThrow(() ->
	                            new ResourceNotFoundException(
	                                    "Student assignment not found"));

	    studentAssignment.setDelivered(delivered);

	    StudentAssignment savedStudentAssignment =
	            studentAssignmentRepository.save(studentAssignment);

	    return studentAssignmentMapper
	            .mapStudentToStudentAssignmentDTO(
	                    savedStudentAssignment);
	}
	
	public StudentAssignmentDTO setAssignmentGrade(
	        Long studentAssignmentId,
	        Integer grade) {

	    StudentAssignment studentAssignment =
	            studentAssignmentRepository.findById(studentAssignmentId)
	                    .orElseThrow(() ->
	                            new ResourceNotFoundException(
	                                    "Student assignment not found"));

	    studentAssignment.setGrade(grade);

	    StudentAssignment savedStudentAssignment =
	            studentAssignmentRepository.save(studentAssignment);

	    return studentAssignmentMapper
	            .mapStudentToStudentAssignmentDTO(
	                    savedStudentAssignment);
	}
	public List<StudentAssignmentDTO> getMyAssignments(String email) {

	    Student student = stRepo.findByEmail(email)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Student not found"));

	    List<StudentAssignment> studentAssignments =
	            studentAssignmentRepository.findByStudentId(student.getId());

	    return studentAssignments.stream()
	            .map(studentAssignment -> {

	                StudentAssignmentDTO dto =
	                        studentAssignmentMapper
	                                .mapStudentToStudentAssignmentDTO(
	                                        studentAssignment);

	                dto.setTitle(
	                        studentAssignment
	                                .getAssignment()
	                                .getName()
	                );

	                return dto;
	            })
	            .toList();
	}
	
}


