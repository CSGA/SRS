
package srs.service;

import java.util.List;

import srs.domain.Section;
import srs.domain.Students;


public interface StudentsService {
	int count();
	Boolean checkLogin(String students_id, String password);
	Students queryById(String students_id);
	List<Students> queryAll();
	List<Students> queryByPage(int pageNumber,int pageSize);
	
	void addStudents(Students students);
	void deleteById(String id);

	

	



}
