package com.example.demo.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class LessonDTO {
         Long id;   
	     @NotBlank
	    private String name;
	    
	    private String lessonCode;
	    
	    @PositiveOrZero
	    @Max(40)
	    private int weeklyHour;
	    

	public LessonDTO() {
	
	}

	public LessonDTO(Long id, String name, String lessonCode, int weeklyHour) {
		this.id = id;
		this.name = name;
		this.lessonCode = lessonCode;
		this.weeklyHour = weeklyHour;
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

	
	
	
}
