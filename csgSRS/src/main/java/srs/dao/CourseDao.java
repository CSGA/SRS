package srs.dao;

import java.util.ArrayList;

import srs.domain.Course;



public interface CourseDao {
	int getTotal();
	ArrayList<Course> findAllCourse();
	ArrayList<Course> fuzzyFindCourse(String searchCourse);
	ArrayList<Course> findPreCourse(String courseNo);
	
	void addCourse(Course course);	
	void delete(String courseNo);
	void resetCourse(Course course);

}
