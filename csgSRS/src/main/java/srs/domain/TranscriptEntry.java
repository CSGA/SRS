package srs.domain;

public class TranscriptEntry {

	private String grade; 
	private Students student;
	private Section section;
	private Transcript transcript; 

	public void setStudent(Students s) {
		student = s;
	}

	public Students getStudent() {
		return student;
	}

	public void setSection(Section s) {
		section = s;
	}

	public Section getSection() {
		return section;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}
	
	public TranscriptEntry(Students s, String grade, Section se) {
		this.setStudent(s);
		this.setSection(se);
		this.setGrade(grade);

		Transcript t = s.getTranscript();
		this.setTranscript(t);
		t.addTranscriptEntry(this);
	}


	public static boolean validateGrade(String grade) {
		boolean outcome = false;

		if (grade.equals("F") ||
		    grade.equals("I")) {
			outcome = true;
		}

		if (grade.startsWith("A") ||
		    grade.startsWith("B") ||
		    grade.startsWith("C") ||
		    grade.startsWith("D")) {
			if (grade.length() == 1) outcome = true;
			else if (grade.length() == 2) {
				if (grade.endsWith("+") ||
				    grade.endsWith("-")) {
					outcome = true;
				}
			}
		}

		return outcome;
	}

	public static boolean passingGrade(String grade) {
		boolean outcome = false;

		// First, make sure it is a valid grade.

		if (validateGrade(grade)) {
			// Next, make sure that the grade is a D or better.

			if (grade.startsWith("A") ||
			    grade.startsWith("B") ||
			    grade.startsWith("C") ||
			    grade.startsWith("D")) {
				outcome = true;
			}
		}
		return outcome;
	}
}
