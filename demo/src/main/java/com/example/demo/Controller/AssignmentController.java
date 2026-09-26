package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AssignmentDTO;
import com.example.demo.Service.AssignmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {

        this.assignmentService = assignmentService;
    }


    @PostMapping("create/{lessonId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<AssignmentDTO> createAnAssignment(
            @PathVariable Long lessonId,
            @Valid @RequestBody AssignmentDTO assignmentDTO,
            Authentication authentication) {

        return ResponseEntity.ok(
                assignmentService.createAssignment(
                        lessonId,
                        assignmentDTO,
                        authentication));
    }


    @GetMapping("get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<AssignmentDTO> getAssignmentByAnId(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentById(
                        id,
                        authentication));
    }


    @GetMapping("getByLesson/{lessonId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByLesson(
            @PathVariable Long lessonId,
            Authentication authentication) {

        List<AssignmentDTO> assignments =
                assignmentService.getAssignmentsByLesson(
                        lessonId,
                        authentication);

        return ResponseEntity.ok(assignments);
    }


    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<AssignmentDTO> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody AssignmentDTO updatedAssignmentDTO,
            Authentication authentication) {

        return ResponseEntity.ok(
                assignmentService.updateAssignment(
                        id,
                        updatedAssignmentDTO,
                        authentication));
    }


    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<AssignmentDTO> deleteAssignment(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                assignmentService.deleteAssignment(
                        id,
                        authentication));
    }
}
