package srs.domain;

import java.util.HashMap;

public class PlanOfStudy {
	private Students student; 
	HashMap<String, Course> courseOffered; 
	HashMap<Students, PlanOfStudyEntry> planOfStudys; 

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}
	
	public PlanOfStudy(Students student){
		this.setStudent(student);
	}

	public boolean isInPlanOfStudy(Course representedCourse) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}