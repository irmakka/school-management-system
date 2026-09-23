package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class StudentLesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @Enumerated(EnumType.STRING)
	 private Grade grade;
	 private int absentism;
	
	@ManyToOne
	@JsonIgnore
	Student student;
	
	@ManyToOne
	@JsonIgnore
	Lesson lesson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public int getAbsentism() {
		return absentism;
	}

	public void setAbsentism(int absentism) {
		this.absentism = absentism;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	


	
	

}
