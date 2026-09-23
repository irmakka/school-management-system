package com.example.demo.Mapper;

import org.mapstruct.Mapper;

import com.example.demo.DTO.LessonDTO;
import com.example.demo.Entity.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper {
 LessonDTO mapLessonToLessonDTO(Lesson lesson);
 Lesson toEntity(LessonDTO lessonDTO);
 
}
