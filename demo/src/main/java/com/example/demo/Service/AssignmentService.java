package com.example.demo.Service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Assignment;
import com.example.demo.Entity.Lesson;
import com.example.demo.Entity.StudentAssignment;
import com.example.demo.Entity.StudentLesson;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.AssignmentMapper;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.Repository.StudentAssignmentRepository;
import com.example.demo.Repository.StudentLessonRepository;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.DTO.AssignmentDTO;

@Service
public class AssignmentService {
	
	private final LessonRepository lessonRepository;
	private final AssignmentRepository assignmentRepository;
	private final AssignmentMapper assignmentMapper;
	private final StudentLessonRepository studentLessonRepository;
	private final StudentAssignmentRepository studentAssignmentRepository;
		
	public AssignmentService(LessonRepository lessonRepository, AssignmentRepository assignmentRepository,
			AssignmentMapper assignmentMapper,StudentLessonRepository studentLessonRepository,StudentAssignmentRepository studentAssignmentRepository) {
		super();
		this.lessonRepository = lessonRepository;
		this.assignmentRepository = assignmentRepository;
		this.assignmentMapper = assignmentMapper;
		this.studentLessonRepository=studentLessonRepository;
		this.studentAssignmentRepository=studentAssignmentRepository;
	}


	public AssignmentDTO createAssignment(
	        Long lessonId,
	        AssignmentDTO assignmentDTO) {

	    Lesson lesson = lessonRepository.findById(lessonId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Lesson not found"));

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
	
	public AssignmentDTO getAssignmentById(Long id) {
	    return assignmentRepository.findById(id).map(assignmentMapper:: mapAssignmentToAssignmentDTO)
	            .orElseThrow(() -> new RuntimeException("Assignment not found"));
	}
	
	public List<AssignmentDTO> getAssignmentsByLesson(Long lessonId) {
		List<Assignment> assignments=assignmentRepository.findByLessonId(lessonId);
		if(assignments.isEmpty()) {
			throw new ResourceNotFoundException("There is no assignment on system for this lesson");
		}
	    return assignments.stream().map(assignmentMapper::mapAssignmentToAssignmentDTO).collect( Collectors.toList());
	}
	
	
	public AssignmentDTO updateAssignment(Long id, AssignmentDTO updatedAssignmentDTO) {

	    Assignment assignment = assignmentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Assignment not found"));

	    assignment.setName(updatedAssignmentDTO.getName());
	    assignment.setDueDate(updatedAssignmentDTO.getDueDate());

	    Assignment savedAssignment = assignmentRepository.save(assignment);

	    return assignmentMapper.mapAssignmentToAssignmentDTO(savedAssignment);
	}
	
	public AssignmentDTO deleteAssignment(Long id) {

	    Assignment assignment = assignmentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Assignment not found"));
	   
        AssignmentDTO assignmentDTO= assignmentMapper.mapAssignmentToAssignmentDTO(assignment);
	    assignmentRepository.delete(assignment);
	    return assignmentDTO;
	}
	

}
