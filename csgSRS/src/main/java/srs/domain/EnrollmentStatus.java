package srs.domain;

// EnrollmentStatus.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A SUPPORT enum.


// Used by the Section class to represent various possible outcomes of
// an attempt to enroll by a Student.

public enum EnrollmentStatus {
	// Enumerate the values that the enum can assume.
	success("选课成功!  :o)"), 
	secFull("选课失败;班次已排满.  :op"), 
	prereq("选课失败; 未满足前置课程.  :op"), 
	prevEnroll("选课失败; 已选过该课程.  :op");

	// 枚举实例的值
	private final String value;

	// A "constructor" of sorts (used above).
	EnrollmentStatus(String value) {
		this.value = value;
	}

	// Accessor for the value of an enum instance.

	public String value() {
		return value;
	}
}
