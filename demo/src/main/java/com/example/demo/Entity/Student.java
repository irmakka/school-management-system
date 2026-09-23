package com.example.demo.Entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;



@Entity
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;

String name;
String surname;
String classNo;
String password;


@Column(unique =true)
String email;




@OneToMany(mappedBy="student", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
List<StudentLesson> studentLessons;

@OneToMany(mappedBy="student", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
List<StudentAssignment> studentAssignments;


public Student() {
	
}



public Student(Long id, String name, String surname, String classNo, String password, String email,
		List<StudentLesson> studentLessons, List<StudentAssignment> studentAssignments) {
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.classNo = classNo;
	this.password = password;
	this.email = email;
	this.studentLessons = studentLessons;
	this.studentAssignments = studentAssignments;
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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getClassNo() {
	return classNo;
}

public void setClassNo(String classNo) {
	this.classNo = classNo;
}



public List<StudentLesson> getStudentLessons() {
	return studentLessons;
}



public void setStudentLessons(List<StudentLesson> studentLessons) {
	this.studentLessons = studentLessons;
}


public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public List<StudentAssignment> getStudentAssignments() {
	return studentAssignments;
}



public void setStudentAssignments(List<StudentAssignment> studentAssignments) {
	this.studentAssignments = studentAssignments;
}



}
