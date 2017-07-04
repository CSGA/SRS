package srs.domain;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

public class Course {
   private String courseNo; 
   private String courseName; 
   private double credits; 
   private ArrayList<Section> offeredAsSection;
   private ArrayList<Course> prerequisites; 
   
 
    public String getCourseNo() {
	   return courseNo;
}

   public void setCourseNo(String courseNo) {
	this.courseNo = courseNo;
}



   public double getCredits() {
	return credits;
}

   public void setCredits(double credits) {
	this.credits = credits;
}
public Course(){}
public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);
		// Note that we're instantiating empty support Collection(s).

		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
		} 


public void display() {
	 System.out.println("Course Information:");
	 System.out.println("\tCourse No.:  " + getCourseNo());
	 System.out.println("\tCourse Name:  " + getCourseName());
	 System.out.println("\tCredits:  " + getCredits());
	 System.out.println("\tPrerequisite Courses:");

	for (Course course : prerequisites) {
		System.out.println("\t\t" + course.toString());
	}

	// Note use of print vs. println in next line of code.
	System.out.print("\tOffered As Section(s):  ");
	for (Section section : offeredAsSection) {
		System.out.print(section.getSectionNo() + " ");
	}

}


  @Override
    public String toString() {
	return getCourseNo() + ":  " + getCourseName();
 }

 
   public void setPrerequisite(ArrayList<Course> courses) {
	prerequisites.addAll(courses);
}



public boolean hasPrerequisites() {
	if (prerequisites.size() > 0) return true;
	else return false;
}


  public Collection<Course> getPrerequisites() {
	return prerequisites;
}
  
 
  public Section scheduleSection(char day, String time, String room,
		       int capacity) {
// Create a new Section (note the creative way in创建一个新的班次
// which we are assigning a section number) ...
     Section s = new Section(offeredAsSection.size() + 1, day, time, this, room, capacity);

// ... and then remember it!添加班次

        addSection(s);

        return s;
     }

    public void addSection(Section section) {
           offeredAsSection.add(section);
}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

   
   
}
