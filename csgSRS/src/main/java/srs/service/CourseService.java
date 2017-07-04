package srs.service;

import java.util.ArrayList;
import java.util.List;

import srs.domain.Course;

public interface CourseService {

	int getTotal();
	ArrayList<Course> fuzzyFindCourse(String searchCourse, int pageNumber, int pageSize);
	List<Course> findAllCourse(int pageNumber, int pageSize);
	
	void addCourse(Course course);
	void delete(String courseNo);
	void resetCourse(Course course);

}
