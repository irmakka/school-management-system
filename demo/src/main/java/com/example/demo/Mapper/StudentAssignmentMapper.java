package com.example.demo.Mapper;

import org.mapstruct.Mapper;

import com.example.demo.DTO.StudentAssignmentDTO;
import com.example.demo.Entity.StudentAssignment;

@Mapper(componentModel ="spring")
public interface StudentAssignmentMapper {
StudentAssignment toEntity(StudentAssignmentDTO studentAssignmentDTO);
StudentAssignmentDTO mapStudentToStudentAssignmentDTO(StudentAssignment studentAssignment);


}
