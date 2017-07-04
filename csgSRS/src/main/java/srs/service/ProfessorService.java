package srs.service;

import srs.domain.Professor;

public interface ProfessorService {

	boolean checkLogin(String id, String password);
	Professor findProfessorById(String id);

}
