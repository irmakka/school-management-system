
package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.TeacherRegisterDTO;
import com.example.demo.Entity.Teacher;
import com.example.demo.Service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Teacher> createTeacher(
            @Valid @RequestBody TeacherRegisterDTO teacherRegisterDTO) {

        Teacher teacher =
                teacherService.createTeacher(teacherRegisterDTO);

        return ResponseEntity.ok(teacher);
    }
}