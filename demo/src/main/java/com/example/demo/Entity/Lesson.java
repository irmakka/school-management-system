package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String lessonCode;
    private int weeklyHour;
    
    @OneToMany(mappedBy="lesson")
    @JsonIgnore
    List<StudentLesson> studentLessons;
    
    
    @OneToMany(mappedBy="lesson")
    @JsonIgnore
    List<Assignment> assignments;
    
   
    public Lesson() {}
    

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
    
    public String getLessonCode() {
        return lessonCode;
    }
    
    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }
    
    public int getWeeklyHour() {
        return weeklyHour;
    }
    
    public void setWeeklyHour(int weeklyHour) {
        this.weeklyHour = weeklyHour;
    }






	public List<StudentLesson> getStudentLessons() {
		return studentLessons;
	}






	public void setStudentLessons(List<StudentLesson> studentLessons) {
		this.studentLessons = studentLessons;
	}






	public List<Assignment> getAssignments() {
		return assignments;
	}






	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	
    
    
}