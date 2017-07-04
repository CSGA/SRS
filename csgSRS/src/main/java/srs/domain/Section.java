package srs.domain;

import java.util.HashMap;
import java.util.List;


public class Section {
     private int sectionNo; 
     private char dayOfWeek;
     private String timeOfDay; 
     private String Room;
     private int seatingCapacity; 
     private Course representedCourse; 
     private ScheduleOfClasses offeredIn; 
     private Professor instructor; 
     private HashMap<String, Students> enrolledStudents; 
 	 private HashMap<Students, TranscriptEntry> assignedGrades;
 	 private Students students;
	
 	 
 	 public int getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}
	public char getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(char dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public String getRoom() {
		return Room;
	}
	public void setRoom(String room) {
		Room = room;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public Course getRepresentedCourse() {
		return representedCourse;
	}
	public void setRepresentedCourse(Course representedCourse) {
		this.representedCourse = representedCourse;
	}
	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}
	public void setOfferedIn(ScheduleOfClasses offeredIn) {
		this.offeredIn = offeredIn;
	}
	public Professor getInstructor() {
		return instructor;
	}
	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
	}
     
	public Section(){
	}
	public HashMap<String, Students> addEnrolledStudents(List<Students> students){
		for (Students students2 : students) {
		enrolledStudents.put("rows", students2);
		}
		return enrolledStudents;
		 
	}
	public Section(int sectionId, char dayOfWeek, String timeOfDay,Course representedCourse, String room, int seatingCapacity
			) {
		super();
		this.setSectionNo(sectionId);
		this.setDayOfWeek(dayOfWeek);
		this.setTimeOfDay(timeOfDay);
		this.setRoom(room);
		this.setSeatingCapacity(seatingCapacity);
		this.setRepresentedCourse(representedCourse);
		this.setInstructor(null);
		enrolledStudents = new HashMap<String,Students>();
		assignedGrades = new HashMap<Students,TranscriptEntry>();
	}
	@Override
	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " +
		       getSectionNo() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}

	public String addFullSectionId() {
		return getRepresentedCourse().getCourseNo() + 
		       " - " + addFullSectionId();
	}

	public String getFullSectionInfo() {
		return getRepresentedCourse().getCourseName() + 
			   "-" + getDayOfWeek() + "-" +
		       "" + getTimeOfDay() +
		       "-" + this.getRoom();
	}

	public EnrollmentStatus enroll(Students student) {
		
		Transcript transcript = student.getTranscript();
		
		// 判断学生是否参加过类似的课程，或者参加过并没有通过考试的可以继续
		if (student.isCurrentlyEnrolledInSimilar(this) || transcript.verifyCompletion(this.getRepresentedCourse())) {
			return EnrollmentStatus.prevEnroll;
		}

		// 判断学生是否满足该课程的前置课程，过不过
		Course course = this.getRepresentedCourse();
		if (course.hasPrerequisites()) {
			for (Course pre : course.getPrerequisites()) {
				if (!transcript.verifyCompletion(pre)) {
					return EnrollmentStatus.prereq;
				}
			}
		}
		// 判断班次学生数量是否已满
				if (!this.confirmSeatAvailability()) {
					return EnrollmentStatus.secFull;
				}
				
		// 双向绑定
			enrolledStudents.put(student.getId(), student);
			student.addSection(this);
			return EnrollmentStatus.success;
			}
	
	 public HashMap<String, Students> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(HashMap<String, Students> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public HashMap<Students, TranscriptEntry> getAssignedGrades() {
		return assignedGrades;
	}
	public void setAssignedGrades(HashMap<Students, TranscriptEntry> assignedGrades) {
		this.assignedGrades = assignedGrades;
	}

	public boolean confirmSeatAvailability() {
		if (enrolledStudents.size() < getSeatingCapacity())
			return true;
		else
			return false;
	}
	

	public boolean drop(Students student) {
		// We may only drop a student if he/she is enrolled.
		if (!student.isEnrolledIn(this)) 
			return false;
		else {
			// Find the student in our HashMap, and remove it.
			// 从参加课程的学生中移除
			enrolledStudents.remove(student.getId());
			student.dropSection(this);
			return true;
		}
	}
	

	public int listTotalEnrollment() {
		return enrolledStudents.size();
	}	
 

	public void display() {
		System.out.println("班次信息:");
		System.out.println("\t学期:  " + getOfferedIn().getSemester());
		System.out.println("\t课程编号.:  " + 
				   getRepresentedCourse().getCourseNo());
		System.out.println("\t班次编号:  " + getSectionNo());
		System.out.println("\t时间与日期:  " + getDayOfWeek() + 
				   " at " + getTimeOfDay());
		System.out.println("\t教室:  " + getRoom());
		if (getInstructor() != null) 
			System.out.println("\t授课老师:  " + 
				   getInstructor().getName());
		displayStudentRoster();
	}
	

	public void displayStudentRoster() {
		
		System.out.print("\tTotal of " + listTotalEnrollment() + 
				   " students enrolled");
		if (listTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");
		// Iterate through all of the values stored in the HashMap.
		for (Students s : enrolledStudents.values()) {
			System.out.println("\t\t" + s.getName());
		}
	}

	// This method returns the value null if the Student has not
	// been assigned a grade.

	public String getGrade(Students student) {
		String grade = null;

		// Retrieve the associated TranscriptEntry object for this specific 
		// student from the assignedGrades HashMap, if one exists, and in turn 
		// retrieve its assigned grade.

		TranscriptEntry te = assignedGrades.get(student);
		if (te != null) {
			grade = te.getGrade(); 
		}
		// If we found no TranscriptEntry for this Student, a null value
		// will be returned to signal this.

		return grade;
	}
	
	

	public boolean postGrade(Students student, String grade) {
		// 判断成绩等级是否正确.
		if (!TranscriptEntry.validateGrade(grade)) return false;
		
		// 判断学生成绩是否已存在
		if (assignedGrades.get(student) != null) return false;

		// 生成成绩
		TranscriptEntry te = new TranscriptEntry(student, grade, this);

		// 添加成绩到assignedGrade
		assignedGrades.put(student, te);

		return true;
	}
	

	public boolean isSectionOf(Course course) {
		if (course == representedCourse) 
			return true;
		else
			return false;
	}
	public Students getStudents() {
		return students;
	}
	public void setStudents(Students students) {
		this.students = students;
	}
}
