package srs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import srs.domain.Professor;
import srs.service.ProfessorService;


@Controller
@RequestMapping("/professor")
public class ProfessorController extends BaseController {
	
	@Resource(name="professorService")
	protected ProfessorService professorService;
	
	
	@RequestMapping(value="/checkLogin")
	@ResponseBody
	public Map<String, Object> checkLogin(@RequestParam(required=true, defaultValue="0")String id,
			@RequestParam(required=true, defaultValue="0")String password, HttpSession session){
		// 判断账号密码是否正确
		if (professorService.checkLogin(id, password)) {
			session.setAttribute("professor", professorService.findProfessorById(id));
			return ajaxSuccessResponse();
		}
		else return ajaxFailureResponse();	
	}
	
	@RequestMapping("/sendLogin")
	@ResponseBody
	public ModelAndView sendLogin(HttpSession session){
		Professor professor = (Professor)session.getAttribute("professor");
		if (professor!=null)return new ModelAndView("redirect:/teachermain.jsp");
		else return new ModelAndView("redirect:/professorlogin.jsp");
	}
}
