package com.example.demo.Mapper;



import org.mapstruct.Mapper;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	Student toEntity(RegisterDTO registerDTO);
	StudentDTO mapStudentToStudentDTO(Student student);
}
