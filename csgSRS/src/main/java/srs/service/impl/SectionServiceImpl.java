package srs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srs.dao.CourseDao;
import srs.dao.SectionDao;
import srs.dao.StudentsDao;
import srs.dao.TranscriptDao;
import srs.domain.Course;
import srs.domain.EnrollmentStatus;
import srs.domain.Section;
import srs.domain.Students;
import srs.domain.Transcript;
import srs.domain.TranscriptEntry;
import srs.service.SectionService;


@Service("sectionService")
public class SectionServiceImpl implements SectionService{
        @Autowired
        protected SectionDao sectionDao;
        @Autowired
        protected StudentsDao studentsDao;
        @Autowired
        protected CourseDao courseDao;
        @Autowired
        protected TranscriptDao transcriptDao;
        
        
        
        
        
        
        
        
        ArrayList<Section> listSections = new ArrayList<Section>();
    	HashMap<String, Section> mapSections= new HashMap<String, Section>();
    	HashMap<String, Students> mapStudents = new HashMap<String, Students>();
	@Override
	public void addSection(Section section) {
		// TODO Auto-generated method stub
		sectionDao.addSection(section);
	}

	@Override
	public void delete(String sectionNo) {
		// TODO Auto-generated method stub
		sectionDao.delete(sectionNo);
	}

	@Override
	public void resetSection(Section section) {
		// TODO Auto-generated method stub
		sectionDao.resetSection(section);
	}

	@Override
	public List<Section> getSectionByCourseNo(String courseNo) {
		// TODO Auto-generated method stub
		ArrayList<Section> sections = sectionDao.getSectionByCourseNo(courseNo);
		return  sections;
	}

	@Override
	public List<Students> findStudentBySectionNo(String sectionNo) {
		// TODO Auto-generated method stub
		List<Students> students =sectionDao.findStudentBySectionNo(sectionNo);
		return students;
	}

	@Override
	public List<Section> findSectionByProfessorId(String professorId) {
		// TODO Auto-generated method stub
		ArrayList<Section> sections=sectionDao.findSectionByProfessorId(professorId);
		return sections;
	}
	@Override
	public List<Section> findSectionByStudent(String id) {
		
		List<Section> sections=sectionDao.findSectionByStudent(id);
		// TODO Auto-generated method stub
		return sections;
	}
	//学生选课

	@Override
	public EnrollmentStatus selectCourse(Students student, String sectionNo, String courseNo) {
		// TODO Auto-generated method stub
		Section section=sectionDao.findSectionByCSNo(courseNo, sectionNo);//得到section（course，section，老师，precourse）
		ArrayList<Students> students= studentsDao.findStudentBySCNo(sectionNo, courseNo);//得到student（成绩和学生）
		ArrayList<Course> courses = courseDao.findPreCourse(courseNo);//得到course和preCourse
		ArrayList<TranscriptEntry> transcriptEntries = transcriptDao.findTranscriptForStudent(student);//
		Transcript transcript = new Transcript(student);
		transcript.setTranscriptEntries(transcriptEntries);
		student.setTranscript(transcript);// 给学生加载课程表
		for (Students student2 : students) {
			mapStudents.put(student2.getId(), student2);
		}
		student.setAttends((ArrayList<Section>) sectionDao.findSectionByStudent(student.getId()));//学生的其他班次
		section.setEnrolledStudents(mapStudents);//section里的学生
		section.getRepresentedCourse().setPrerequisite(courses);//先修课程
		section.setStudents(student);; 
		return section.enroll(student);
	}

	@Override
	public void addTranscript(String id, String sectionNo, String courseNo) {
		// TODO Auto-generated method stub
		transcriptDao.addTranscript(id,sectionNo,courseNo);
	}

	@Override
	public HashMap<String, Section> findBySemester(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
