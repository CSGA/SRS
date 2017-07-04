package srs.dao;

import java.util.ArrayList;

import srs.domain.Students;
import srs.domain.TranscriptEntry;

public interface TranscriptDao {

	ArrayList<TranscriptEntry> findTranscriptForStudent(Students student);

	void addTranscript(String id, String sectionNo, String courseNo);
 
}
