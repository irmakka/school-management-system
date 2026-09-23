
package com.example.demo.Mapper;

import org.mapstruct.Mapper;

import com.example.demo.DTO.AssignmentDTO;
import com.example.demo.Entity.Assignment;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    AssignmentDTO mapAssignmentToAssignmentDTO(Assignment assignment);

    Assignment toEntity(AssignmentDTO assignmentDTO);
}