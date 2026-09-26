package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Assignment;
import com.example.demo.Entity.Lesson;
import com.example.demo.Entity.StudentAssignment;
import com.example.demo.Entity.StudentLesson;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.AssignmentMapper;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.Repository.StudentAssignmentRepository;
import com.example.demo.Repository.StudentLessonRepository;
import com.example.demo.DTO.AssignmentDTO;

@Service
public class AssignmentService {

    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final StudentLessonRepository studentLessonRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;

    public AssignmentService(
            LessonRepository lessonRepository,
            AssignmentRepository assignmentRepository,
            AssignmentMapper assignmentMapper,
            StudentLessonRepository studentLessonRepository,
            StudentAssignmentRepository studentAssignmentRepository) {

        this.lessonRepository = lessonRepository;
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
        this.studentLessonRepository = studentLessonRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
    }

    public AssignmentDTO createAssignment(
            Long lessonId,
            AssignmentDTO assignmentDTO,
            Authentication authentication) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        checkLessonOwnership(lesson, authentication);

        Assignment assignment =
                assignmentMapper.toEntity(assignmentDTO);

        assignment.setLesson(lesson);

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        List<StudentLesson> studentLessons =
                studentLessonRepository.findByLessonId(lessonId);

        for (StudentLesson studentLesson : studentLessons) {

            StudentAssignment studentAssignment =
                    new StudentAssignment();

            studentAssignment.setStudent(
                    studentLesson.getStudent()
            );

            studentAssignment.setAssignment(
                    savedAssignment
            );

            studentAssignment.setDelivered(false);
            studentAssignment.setGrade(null);

            studentAssignmentRepository.save(studentAssignment);
        }

        return assignmentMapper
                .mapAssignmentToAssignmentDTO(savedAssignment);
    }
    
    public AssignmentDTO getAssignmentById(
            Long id,
            Authentication authentication) {

        Assignment assignment =
                assignmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Assignment not found"));

        checkLessonOwnership(
                assignment.getLesson(),
                authentication
        );

        return assignmentMapper
                .mapAssignmentToAssignmentDTO(assignment);
    }
    public List<AssignmentDTO> getAssignmentsByLesson(
            Long lessonId,
            Authentication authentication) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        checkLessonOwnership(lesson, authentication);

        List<Assignment> assignments =
                assignmentRepository.findByLessonId(lessonId);

        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException(
                    "There is no assignment on system for this lesson");
        }

        return assignments.stream()
                .map(assignmentMapper::mapAssignmentToAssignmentDTO)
                .collect(Collectors.toList());
    }


    public AssignmentDTO updateAssignment(
            Long id,
            AssignmentDTO updatedAssignmentDTO,
            Authentication authentication) {

        Assignment assignment =
                assignmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Assignment not found"));

        checkLessonOwnership(
                assignment.getLesson(),
                authentication
        );

        assignment.setName(
                updatedAssignmentDTO.getName()
        );

        assignment.setDueDate(
                updatedAssignmentDTO.getDueDate()
        );

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        return assignmentMapper
                .mapAssignmentToAssignmentDTO(savedAssignment);
    }


    public AssignmentDTO deleteAssignment(
            Long id,
            Authentication authentication) {

        Assignment assignment =
                assignmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Assignment not found"));

        checkLessonOwnership(
                assignment.getLesson(),
                authentication
        );

        AssignmentDTO assignmentDTO =
                assignmentMapper.mapAssignmentToAssignmentDTO(
                        assignment
                );

        assignmentRepository.delete(assignment);

        return assignmentDTO;
    }


   
    private void checkLessonOwnership(
            Lesson lesson,
            Authentication authentication) {

      
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return;
        }
        boolean isTeacher = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_TEACHER"));

        if (!isTeacher) {
            throw new AccessDeniedException(
                    "You are not authorized to manage this assignment");
        }

        String teacherEmail = authentication.getName();

        if (lesson.getTeacher() == null) {
            throw new AccessDeniedException(
                    "This lesson has no assigned teacher");
        }
        if (!lesson.getTeacher().getEmail().equals(teacherEmail)) {
            throw new AccessDeniedException(
                    "You are not authorized to manage this lesson");
        }
    }
}
