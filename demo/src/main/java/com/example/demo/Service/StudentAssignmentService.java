package com.example.demo.Service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
            StudentAssignmentRepository studentAssignmentRepository,
            StudentAssignmentMapper studentAssignmentMapper,
            StudentRepository stRepo) {
        super();
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.studentAssignmentMapper = studentAssignmentMapper;
        this.stRepo = stRepo;
    }


    public List<StudentAssignmentDTO> getStudentAssignments(
            Long studentId,
            Authentication authentication) {

        StudentAssignment firstAssignment = null;

        List<StudentAssignment> studentAssignments =
                studentAssignmentRepository.findByStudentId(studentId);

        if (!studentAssignments.isEmpty()) {
            firstAssignment = studentAssignments.get(0);
        }

        checkTeacherOrAdminAccess(firstAssignment, authentication);

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
            boolean delivered,
            Authentication authentication) {

        StudentAssignment studentAssignment =
                studentAssignmentRepository.findById(studentAssignmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student assignment not found"));

        String studentEmail = authentication.getName();

        if (!studentAssignment.getStudent()
                .getEmail()
                .equals(studentEmail)) {

            throw new AccessDeniedException(
                    "You can only update your own assignment");
        }

        studentAssignment.setDelivered(delivered);

        StudentAssignment savedStudentAssignment =
                studentAssignmentRepository.save(studentAssignment);

        return studentAssignmentMapper
                .mapStudentToStudentAssignmentDTO(
                        savedStudentAssignment);
    }


    public StudentAssignmentDTO setAssignmentGrade(
            Long studentAssignmentId,
            Integer grade,
            Authentication authentication) {

        StudentAssignment studentAssignment =
                studentAssignmentRepository.findById(studentAssignmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student assignment not found"));

        checkTeacherOrAdminAccess(
                studentAssignment,
                authentication
        );

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
                studentAssignmentRepository.findByStudentId(
                        student.getId());

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


    private void checkTeacherOrAdminAccess(
            StudentAssignment studentAssignment,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority()
                                .equals("ROLE_ADMIN"));

        if (isAdmin) {
            return;
        }

        String teacherEmail = authentication.getName();

        if (studentAssignment == null) {
            throw new AccessDeniedException(
                    "There is no assignment to check");
        }

        if (studentAssignment
                .getAssignment()
                .getLesson()
                .getTeacher() == null) {

            throw new AccessDeniedException(
                    "This lesson has no assigned teacher");
        }

        String lessonTeacherEmail =
                studentAssignment
                        .getAssignment()
                        .getLesson()
                        .getTeacher()
                        .getEmail();

        if (!lessonTeacherEmail.equals(teacherEmail)) {

            throw new AccessDeniedException(
                    "You are not authorized to manage this assignment");
        }
    }
}

