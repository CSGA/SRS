package srs.dao;

import java.util.ArrayList;
import java.util.List;

import srs.domain.Section;
import srs.domain.Students;

public interface SectionDao {	
	ArrayList<Section> getSectionByCourseNo(String courseNo);
	ArrayList<Students> findStudentBySectionNo(String sectionNo);
	ArrayList<Section> findSectionByProfessorId(String professorId);
	List<Section> findSectionByStudent(String id);
	Section findSectionByCSNo(String courseNo, String sectionNo);
	
	void addSection(Section section);
	void delete(String sectionNo);
	void resetSection(Section section);

	
}
