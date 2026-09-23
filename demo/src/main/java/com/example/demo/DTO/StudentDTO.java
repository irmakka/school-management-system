package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class StudentDTO {
	
	Long id;	   
	@NotBlank
	String name;
	@NotBlank
	String surname;
	
	String classNo;

	@Column(unique =true)
	@Email
	@NotBlank
	String email;

	
	public StudentDTO() {
		
	}
	
	
	public StudentDTO(Long id, String name, String surname, String classNo, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.classNo = classNo;
		this.email = email;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    
    


}
