package srs.specification;

import srs.domain.Section;
import srs.domain.Students;

public interface Specification {
	public boolean enroll(Students student, Section section);
}
