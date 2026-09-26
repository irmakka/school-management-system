
package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.TeacherRegisterDTO;
import com.example.demo.Entity.Teacher;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public TeacherService(
            TeacherRepository teacherRepository,
            UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
    }

    public Teacher createTeacher(TeacherRegisterDTO teacherRegisterDTO) {

        if (teacherRepository
                .findByEmail(teacherRegisterDTO.getEmail())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "This email already exists: "
                    + teacherRegisterDTO.getEmail());
        }

        Teacher teacher = new Teacher();

        teacher.setName(teacherRegisterDTO.getName());
        teacher.setSurname(teacherRegisterDTO.getSurname());
        teacher.setEmail(teacherRegisterDTO.getEmail());

        Teacher savedTeacher = teacherRepository.save(teacher);

        userService.saveUser(
                teacherRegisterDTO.getEmail(),
                teacherRegisterDTO.getPassword(),
                "TEACHER"
        );

        return savedTeacher;
    }

    public Teacher getTeacherByEmail(String email) {

        return teacherRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found"));
    }
}