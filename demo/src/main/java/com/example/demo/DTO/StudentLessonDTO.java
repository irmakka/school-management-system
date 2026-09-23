package com.example.demo.DTO;

import com.example.demo.Entity.Grade;

public class StudentLessonDTO {
	Long id;
	 String lessonName;
     Grade grade;
	 int absentism;
	

	public StudentLessonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public StudentLessonDTO(Long id, String lessonName, Grade grade, int absentism) {
		super();
		this.id = id;
		this.lessonName=lessonName;
		this.grade = grade;
		this.absentism = absentism;
	}

	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
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
	
	
	
	

}
