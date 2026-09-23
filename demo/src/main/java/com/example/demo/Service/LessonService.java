package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LessonDTO;
import com.example.demo.Entity.Lesson;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.LessonMapper;
import com.example.demo.Repository.LessonRepository;

@Service
public class LessonService {

private final LessonRepository lessonRep;
private final LessonMapper lessonMap;

public LessonService(LessonRepository lessonRep, LessonMapper lessonMap) {
  	this.lessonRep=lessonRep;
	this.lessonMap=lessonMap;
}

public LessonDTO getLesson(Long lessonId){
   LessonDTO lesson= lessonRep.findById(lessonId).map(lessonMap:: mapLessonToLessonDTO).orElseThrow(()-> 
   new  ResourceNotFoundException("No lesson found"+lessonId));
  
   return lesson;
	
}

public List<LessonDTO> getLessons(){
	List<Lesson> lessons= lessonRep.findAll();
	if(lessons.isEmpty()) {
		throw new ResourceNotFoundException("There is no lesson on system");
	}
	return lessons.stream().map(lessonMap:: mapLessonToLessonDTO).collect(Collectors.toList());
}

public LessonDTO saveLesson(LessonDTO lessonDTO) {
	if(lessonRep.findByLessonCode(lessonDTO.getLessonCode()).isPresent()) {
		throw new IllegalArgumentException("Already exists");
	}
	Lesson lesson=lessonMap.toEntity(lessonDTO);
	lessonRep.save(lesson);
    return  lessonMap.mapLessonToLessonDTO(lesson);
}
public String deleteLesson(Long lessonId) {
	if (lessonRep.findById(lessonId).isEmpty()) {
		throw new ResourceNotFoundException("Lesson couldn't be found");
	}
	lessonRep.deleteById(lessonId);
	return "deleted"; 
}

	
}
