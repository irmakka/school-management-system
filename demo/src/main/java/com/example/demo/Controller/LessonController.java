package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LessonDTO;
import com.example.demo.Service.LessonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("lessons")
public class LessonController {

	private final LessonService lessonServ;

	public LessonController(LessonService lessonServ) {
		this.lessonServ = lessonServ;
	}
	
	@GetMapping("get/{lessonId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
	public ResponseEntity<LessonDTO> getLesson(@PathVariable Long lessonId) {
		return ResponseEntity.ok(lessonServ.getLesson(lessonId));
	}
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
	public ResponseEntity< List<LessonDTO>> getLessons() {
		return ResponseEntity.ok(lessonServ.getLessons());
	}
	@PostMapping("save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LessonDTO> saveLesson(@Valid @RequestBody LessonDTO lessonDTO){
	    LessonDTO lessonSaved= lessonServ.saveLesson(lessonDTO);
		return ResponseEntity.ok(lessonSaved);
	}
	
	@DeleteMapping("delete/{lessonId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteLesson(@PathVariable Long lessonId){
		return ResponseEntity.ok(lessonServ.deleteLesson(lessonId));
		
	}
	
	
	
	
	

	
	
}
