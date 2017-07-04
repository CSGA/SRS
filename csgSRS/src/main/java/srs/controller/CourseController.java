package srs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import srs.domain.Course;
import srs.service.CourseService;


@Controller
@RequestMapping("/course")
public class CourseController extends BaseController{
	
	@Resource(name="courseService")
	 protected CourseService courseService;
     
	
	Map<String, Object> data =new HashMap<String,Object>();
	ModelAndView mView=new ModelAndView();

	@RequestMapping(value="/listAllCourse")
	@ResponseBody
	public  Map<String,Object> listAllCourse(@RequestParam(required=true,defaultValue="0")int pageNumber,@RequestParam(required=true,defaultValue="0")int pageSize, HttpSession session,String search)throws Exception{
		
		if (search==null) {
			System.out.println(pageSize);
			data.put("rows", courseService.findAllCourse(pageNumber, pageSize));
			data.put("total", courseService.getTotal());
		}
		else{
			String searchCourse = new StringBuilder("%").append(search).append("%").toString();
			ArrayList<Course> courses = courseService.fuzzyFindCourse(searchCourse,pageNumber,pageSize);
			data.put("total", courseService.getTotal());
			data.put("rows", courses);
		}
		return data;
	}
	

	@RequestMapping(value="/skip1")
	@ResponseBody
	public ModelAndView skip1(HttpSession httpSession){
		return new ModelAndView("redirect:/course.jsp");
	}
	

	@RequestMapping(value="/skip2")
	@ResponseBody
	public ModelAndView skip2(HttpSession httpSession){
		return new ModelAndView("redirect:/section.jsp");
	}

	@RequestMapping(value="/addCourse")
	@ResponseBody
	public Map<String, Object> addCourse(Course course)throws Exception{
		try {
			courseService.addCourse(course);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
		return ajaxFailureResponse();
		}
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(@Param("courseNo") String courseNo)  throws Exception{
		try {
			courseService.delete(courseNo);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}

	@RequestMapping(value="resetCourse")
	 @ResponseBody
	public Map<String, Object> resetCourse(Course course) throws Exception{
		try {
			courseService.resetCourse(course);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}
	
	
}
