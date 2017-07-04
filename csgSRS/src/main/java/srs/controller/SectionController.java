package srs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import srs.dao.SectionDao;
import srs.domain.EnrollmentStatus;
import srs.domain.Professor;
import srs.domain.Section;
import srs.domain.Students;
import srs.domain.Transcript;
import srs.service.SectionService;


@Controller
@RequestMapping("/section")
public class SectionController extends BaseController{
    @Resource(name="sectionService")
    protected SectionService sectionService;
    
    Map<String, Object> data =new HashMap<String,Object>();
    

	@RequestMapping(value="/addSection")
	@ResponseBody
    public Map<String,Object> addSection(Section section) throws Exception{
    	try {
			sectionService.addSection(section);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
    }
	

	@RequestMapping(value="/getSectionByCourseNo")
	@ResponseBody
	public Map<String, Object> getSectionByCourseNo(@RequestParam(required=true,defaultValue="0")String courseNo) throws Exception{
		data.put("rows", sectionService.getSectionByCourseNo(courseNo));
		System.out.println(data.size());
		return data;
	}
	

	@RequestMapping(value="/deleteSection")
	@ResponseBody
	public Map<String, Object>  deleteSection(@RequestParam(required=true,defaultValue="0")String sectionNo)throws Exception{
		try {
			sectionService.delete(sectionNo);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}

	@RequestMapping("/resetSection")
	@ResponseBody
	public Map<String, Object> resetSection(Section section)throws Exception{
	try {
		   sectionService.resetSection(section);
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}
	

	@RequestMapping("/appointProfessor")
	@ResponseBody
	public  Map<String, Object> appointProfessor()  throws Exception{
	try {
			
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}
	

	@RequestMapping("/inputGrade")
	@ResponseBody
	public Map<String, Object> inputGrade() throws Exception{
	try {
			
			return ajaxSuccessResponse();
		} catch (Exception e) {
			// TODO: handle exception
			return ajaxFailureResponse();
		}
	}

	@RequestMapping("/findStudentBySectionNo")
	@ResponseBody
	public Map<String, Object> findStudentBySectionNo(@RequestParam(required=true,defaultValue="0")String sectionNo) throws Exception{
		Map<String, Object> data1= new HashMap<String,Object>();
		data1.put("rows", sectionService.findStudentBySectionNo(sectionNo));
		return data1;
	}

	@RequestMapping("/findSectionByProfessorId")
	@ResponseBody
	public Map<String, Object> findSectionByProfessorId(HttpSession session){
		 Map<String, Object> map =new HashMap<String,Object>();
		 Professor professorId =(Professor)session.getAttribute("professor");
		 map.put("rows", sectionService. findSectionByProfessorId(professorId.getId()));
		 return map;
	}
	
	

	@RequestMapping(value="selectCourse")
	@ResponseBody
	public Map<String, Object> selectCourse(String sectionNo,String courseNo,HttpSession session){
		    Students student = (Students)session.getAttribute("student");
		    EnrollmentStatus enrollmentStatus=sectionService.selectCourse(student, sectionNo,courseNo);
		    if (enrollmentStatus.equals(EnrollmentStatus.success)) {
				sectionService.addTranscript(student.getId(),sectionNo,courseNo);
			}
			return ajaxSuccessResponse(enrollmentStatus.value());
	
	    }
	}
