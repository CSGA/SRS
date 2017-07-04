package srs.domain;
// Transcript.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;


public class Transcript {

	private ArrayList<TranscriptEntry> transcriptEntries; 
	private Students studentOwner;

	public ArrayList<TranscriptEntry> getTranscriptEntries() {
		return transcriptEntries;
	}

	public void setTranscriptEntries(ArrayList<TranscriptEntry> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}

	public void setStudentOwner(Students s) {
		studentOwner = s;
	}

	public Students getStudentOwner() {
		return studentOwner;
	}
	
	public Transcript(Students s) {
		setStudentOwner(s);
		transcriptEntries = new ArrayList<TranscriptEntry>();
	}



	public boolean verifyCompletion(Course course) {
		
		boolean outcome = false;
		for (TranscriptEntry te : transcriptEntries) {
			Section s = te.getSection();
			
			// 判断班次是否属于课程
			if (s.isSectionOf(course)) {
				// 判断成绩是否通过
			    if (TranscriptEntry.passingGrade(te.getGrade())) {
				outcome = true;
				break;
			    }
			}
		}
		return outcome;
	}


	public void addTranscriptEntry(TranscriptEntry te) {
		transcriptEntries.add(te);
	}


	public void display() {
		System.out.println("学生个人成绩单:  " +
				   getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(无成绩)");
		}
		else for (TranscriptEntry te : transcriptEntries) {
			Section sec = te.getSection();

			Course c = sec.getRepresentedCourse();

			ScheduleOfClasses soc = sec.getOfferedIn();

			System.out.println("\t学期:        " +
					   soc.getSemester());
			System.out.println("\t课程编号.:      " +
					   c.getCourseNo());
			System.out.println("\t学分:         " +
					   c.getCredits());
			System.out.println("\t获得成绩:  " +
					   te.getGrade());
			System.out.println("\t-----");
		}
	}
}
