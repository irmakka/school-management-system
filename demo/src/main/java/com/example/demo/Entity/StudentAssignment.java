package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    
    public boolean delivered;
    
 
    public Integer grade;
    @ManyToOne
	@JsonIgnore
	Student student;
	
	@ManyToOne
	@JsonIgnore
	Assignment assignment;

	public StudentAssignment() {
	
	}

	public StudentAssignment(Long id, boolean delivered,Integer grade, Student student, Assignment assignment) {
		this.id = id;
		this.delivered = delivered;
		this.grade=grade;
		this.student = student;
		this.assignment = assignment;
	}
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	
    
	
	
    
	
    
}
