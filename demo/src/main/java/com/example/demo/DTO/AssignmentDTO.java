package com.example.demo.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

 
public class AssignmentDTO {
	    Long id;
	
	   @NotBlank
	    private String name;
	    
	    @NotNull
	    @FutureOrPresent
	    private LocalDateTime dueDate;
	   
	 
	public AssignmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AssignmentDTO(Long id, String name,LocalDateTime dueDate) {
		super();
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDateTime getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
   
     
    
}
