package com.example.demo.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



public class StudentAssignmentDTO {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long id;
	    
	    public boolean delivered;

	    public Integer grade;
	    public String title;
	   
		public StudentAssignmentDTO() {
		
		}

		public StudentAssignmentDTO(Long id, boolean delivered, Integer grade) {
			this.id = id;
			this.delivered = delivered;
			this.grade=grade;
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

		public Integer getGrade() {
			return grade;
		}

		public void setGrade(Integer grade) {
			this.grade = grade;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		
}
