package com.example.demo.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.DTO.StudentLessonDTO;
import com.example.demo.Entity.StudentLesson;




@Mapper(componentModel = "spring")
public interface StudentLessonMapper {
	  
	@Mapping(source = "lesson.name", target = "lessonName")
	    StudentLessonDTO mapStudentLessonToStudentLessonDTO(
	            StudentLesson studentLesson);

	 StudentLesson toEntity(StudentLessonDTO studentLessonDTO);
}
