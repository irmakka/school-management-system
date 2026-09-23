package com.example.demo.Controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.StudentAssignmentDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.DTO.StudentLessonDTO;
import com.example.demo.Service.StudentAssignmentService;
import com.example.demo.Service.StudentLessonService;
import com.example.demo.Service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

 private final StudentService stService;
 private final StudentLessonService stLessonService;
 private final StudentAssignmentService stAssignmentService;
 
 public StudentController(StudentService stService,StudentLessonService stLessonService,StudentAssignmentService stAssignmentService ) {
this.stService=stService; 
this.stLessonService=stLessonService;
this.stAssignmentService=stAssignmentService;
 }

@GetMapping()
public ResponseEntity<List<StudentDTO>> getStudents(){
	List<StudentDTO> students= stService.getAllStudents();
	return ResponseEntity.ok(students);
}
@GetMapping("{studentId}")
public ResponseEntity<StudentDTO> getAStudent(@PathVariable Long studentId){
	return ResponseEntity.ok(stService.getAStudent(studentId));
}
 
@PostMapping("save")
public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody RegisterDTO registerDTO){
    StudentDTO savedStudent=stService.saveStudent(registerDTO);
    return ResponseEntity.ok(savedStudent);
}

@GetMapping("class/{classNo}")
public ResponseEntity<List<StudentDTO>> getStudentsByClass(@PathVariable @NotBlank  String classNo ){
	List<StudentDTO> students= stService.getByStudentClasses(classNo);
    return ResponseEntity.ok(students);
}
@DeleteMapping("{studentId}")
public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
	return ResponseEntity.ok(stService.deleteStudent(studentId));	
}

@GetMapping("/me")
public ResponseEntity<StudentDTO> getMyStudent(Authentication authentication) {
    String email = authentication.getName();

    return ResponseEntity.ok(
        stService.getMyStudent(email)
    );
}
@GetMapping("/me/lessons")
public ResponseEntity<List<StudentLessonDTO>> getMyLessons(
        Authentication authentication) {

    String email = authentication.getName();

    return ResponseEntity.ok(
            stLessonService.getMyLessons(email)
    );
}
@GetMapping("/me/assignments")
public ResponseEntity<List<StudentAssignmentDTO>> getMyAssignments(
        Authentication authentication) {

    String email = authentication.getName();

    return ResponseEntity.ok(
            stAssignmentService.getMyAssignments(email)
    );
}
}
