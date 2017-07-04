package srs.service;

import java.util.HashMap;
import java.util.List;

import srs.domain.EnrollmentStatus;
import srs.domain.Section;
import srs.domain.Students;


public interface SectionService {
    
	List<Section> getSectionByCourseNo(String courseNo);
    List<Students> findStudentBySectionNo(String sectionNo);
	List<Section> findSectionByProfessorId(String professorId);
	List<Section> findSectionByStudent(String id);
	EnrollmentStatus selectCourse(Students student, String sectionNo, String courseNo);
	
    void addSection(Section section);
    void delete(String sectionNo);
    void resetSection(Section section);
	void addTranscript(String id, String sectionNo, String courseNo);
	HashMap<String, Section> findBySemester(String string);

	
	
    
}
