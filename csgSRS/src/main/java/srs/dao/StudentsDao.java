package srs.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import srs.domain.Section;
import srs.domain.Students;

public interface StudentsDao {
	int count(); 
	List<Students> queryAll();	
	List<Students> checkLogin(@Param("students_id")String students_id, @Param("password")String password);
	Students queryById(String id);
	ArrayList<Students> findStudentBySCNo(String sectionNo, String courseNo);
	
	void addStudents(Students students);
	void deleteById(String id);
	
	
}
