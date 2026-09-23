package com.example.demo.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
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
public ResponseEntity<AssignmentDTO> createAnAssignment(@PathVariable Long lessonId,@Valid @RequestBody AssignmentDTO assignmentDTO) {
	  
      return ResponseEntity.ok(assignmentService.createAssignment(lessonId, assignmentDTO));
	    
	}	
@GetMapping("get/{id}")
public ResponseEntity<AssignmentDTO> getAssignmentByAnId( @PathVariable Long id) {
    return ResponseEntity.ok(assignmentService.getAssignmentById(id));
}

@GetMapping("getByLesson/{lessonId}")
public ResponseEntity< List<AssignmentDTO>> getAssignmentsByLesson(@PathVariable Long lessonId) {
	List<AssignmentDTO> assignments=assignmentService.getAssignmentsByLesson(lessonId);	
    return ResponseEntity.ok(assignments);
}

@PutMapping("update/{id}")
public ResponseEntity<AssignmentDTO> updateAssignment (@PathVariable Long id,@Valid @RequestBody AssignmentDTO updatedAssignmentDTO) {
return ResponseEntity.ok(assignmentService.updateAssignment(id, updatedAssignmentDTO));
}
@DeleteMapping("delete/{id}")
public ResponseEntity<AssignmentDTO> deleteAssignment(@PathVariable Long id) {
	return ResponseEntity.ok(assignmentService.deleteAssignment(id));
  
}

	
}
