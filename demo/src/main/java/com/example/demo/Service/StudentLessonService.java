package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentLessonDTO;
import com.example.demo.Entity.Grade;
import com.example.demo.Entity.Lesson;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.StudentLesson;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.StudentLessonMapper;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.Repository.StudentLessonRepository;
import com.example.demo.Repository.StudentRepository;


@Service
public class StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentLessonMapper studentLessonMapper;

    public StudentLessonService(
            StudentLessonRepository studentLessonRepository,
            StudentRepository studentRepository,
            LessonRepository lessonRepository,StudentLessonMapper studentLessonMapper) {

        this.studentLessonRepository = studentLessonRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.studentLessonMapper=studentLessonMapper;
    }

    public StudentLessonDTO assignLessonToStudent(
            Long studentId,
            Long lessonId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        StudentLesson studentLesson = new StudentLesson();

        studentLesson.setStudent(student);
        studentLesson.setLesson(lesson);
        studentLesson.setAbsentism(0);

        StudentLesson savedStudentLesson =
                studentLessonRepository.save(studentLesson);

        return studentLessonMapper
                .mapStudentLessonToStudentLessonDTO(savedStudentLesson);
    }
    public StudentLessonDTO setAbsentAndGrade(
            Long studentLessonId,
            Grade grade,
            int absent) {

        StudentLesson studentLesson =
                studentLessonRepository.findById(studentLessonId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student lesson not found"));

        studentLesson.setGrade(grade);
        studentLesson.setAbsentism(absent);

        StudentLesson savedStudentLesson =
                studentLessonRepository.save(studentLesson);

        return studentLessonMapper
                .mapStudentLessonToStudentLessonDTO(savedStudentLesson);
    }

    public List<StudentLessonDTO> getStudentLessons(Long studentId) {

        List<StudentLesson> studentLessons =
                studentLessonRepository.findByStudentId(studentId);

        return studentLessons.stream()
                .map(studentLessonMapper::mapStudentLessonToStudentLessonDTO)
                .toList();
    }

    public StudentLessonDTO getStudentLesson(
            Long studentId,
            Long lessonId) {

        StudentLesson studentLesson =
                studentLessonRepository
                        .findByStudentIdAndLessonId(studentId, lessonId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student is not enrolled in this lesson"));

        return studentLessonMapper
                .mapStudentLessonToStudentLessonDTO(studentLesson);
    }
    public List<StudentLessonDTO> getMyLessons(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        List<StudentLesson> studentLessons =
                studentLessonRepository.findByStudentId(student.getId());

        return studentLessons.stream()
                .map(studentLessonMapper::mapStudentLessonToStudentLessonDTO)
                .toList();
    }
}