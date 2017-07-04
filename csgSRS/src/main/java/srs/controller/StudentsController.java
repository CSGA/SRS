package srs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import srs.domain.Section;
import srs.domain.Students;
import srs.service.SectionService;
import srs.service.StudentsService;

@Controller
@RequestMapping("/student")
public class StudentsController extends BaseController{
	private static final int Section = 0;
	@Autowired
	protected StudentsService studentsService;
	@Autowired
	protected SectionService sectionService;
	
	Map<String, Object> data =new HashMap<String,Object>();
	ModelAndView mView=new ModelAndView();
	

	@RequestMapping(value="/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(String asc,
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize
            ) throws Exception{
		data.put("total", studentsService.count());
		data.put("rows", studentsService.queryByPage(pageNumber, pageSize));
		return data;
		} 
	

	@RequestMapping(value="/query")
	@ResponseBody
	public Map<String, Object> query(String asc,@RequestParam int page,@RequestParam int rows) throws Exception{
		data.put("rows", studentsService.queryByPage(page, rows));
		data.put("total", studentsService.count());
		return data;
		} 
	

	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String , Object> login(
			@RequestParam("students_id") String students_id ,			
			@RequestParam("password") String password,
			HttpSession session
			) throws Exception{
		
		Boolean login=studentsService.checkLogin(students_id,password);
		if (login) {
			 session.setAttribute("student", studentsService.queryById(students_id));
			 return ajaxSuccessResponse();
		} 
		else {
			return ajaxFailureResponse();
		}
	} 
	
	// 检验登录并跳转页面

		@SuppressWarnings("unused")
		@RequestMapping(value="/sendLogin") 
		@ResponseBody
		public ModelAndView sendLogin(HttpSession session){
			 // 从session中获取students
			Students student = (Students)session.getAttribute("student"); 
			session.setAttribute("stuSection", sectionService.findSectionByStudent(student.getId()));
			// 判断session中的student是否为空
			if (student!=null)
				return new ModelAndView("redirect:/studentmain.jsp"); // 跳转至首页
			else
				return new ModelAndView("redirect:/login.jsp"); // 跳转至login页
		}
		
	

	@RequestMapping(value="/exitLogin")
	@ResponseBody
	public ModelAndView exitLogin(HttpSession session) throws Exception{
		session.invalidate();
		mView.setViewName("login");
		return mView;
	}
	
	

	@RequestMapping(value="/selectCourse")
	@ResponseBody
	public ModelAndView selectCourse(HttpSession session) throws Exception{
		
		return new ModelAndView("redirect:/selectCourse.jsp");
	}
	
	

	@RequestMapping(value="/addStudents")
	@ResponseBody
	public Map<String,Object > addStudents(String asc,Students students) throws Exception{
		try {
			studentsService.addStudents(students);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
		
		
	}


	
	
	
	
}
