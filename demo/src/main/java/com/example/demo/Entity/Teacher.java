package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Teacher {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
Long id;
String name;
String surname;
@Column(unique = true)
String email;
@OneToMany(mappedBy ="teacher")
List<Lesson> lessons;

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
public List<Lesson> getLessons() {
	return lessons;
}
public void setLessons(List<Lesson> lessons) {
	this.lessons = lessons;
} 



}
