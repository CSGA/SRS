package srs.dao.impl;

import java.util.ArrayList;

import srs.dao.CourseDao;
import srs.domain.Course;

public class CourseDaoImpl implements CourseDao{

	@Override
	public ArrayList<Course> findAllCourse() {
		// TODO Auto-generated method stub
		// 创建新的hashMap容器
				ArrayList<Course> listAllCourse = new ArrayList<Course>();

				// 创建Course 对象
				Course c1 = new Course("ABC-101", "信息系统分析与设计（上）", 2.5);
				Course c2 = new Course("ABC-102", "信息系统分析与设计（下）", 2.5);
				Course c3 = new Course("ABC-103", "网络营销", 2.5);
				Course c4 = new Course("ABC-104", "高等数学（上）", 2.5);
//				
//				// 为course对象添加前置课程
//				c1.addPrerequisite(c4);
//				c2.addPrerequisite(c1);
//				c3.addPrerequisite(c2);
				
				// 为hashmap对象加载数据
				listAllCourse.add(c1);
				listAllCourse.add(c2);
				listAllCourse.add(c3);
				listAllCourse.add(c4);
				return listAllCourse;
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String courseNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Course> fuzzyFindCourse(String searchCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Course> findPreCourse(String courseNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
