package dtu.student.pp.data.project;

import java.io.Serializable;


public class ProjectNumber implements Serializable, Comparable<ProjectNumber> {
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	private static final long serialVersionUID = -24181437662297555L;
	final int year;
	final int projectNumber;
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	public ProjectNumber(int year, int projectNumber) {
		this.year = year;
		this.projectNumber = projectNumber;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public String toString() {
		//Format: YYNNNN - Last 2 digits of year, plus minimum four digits of projectCnt.
		return (year%100)+String.format("%04d", this.projectNumber);
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public int hashCode() {
		//Perfect hashing as long as year doesn't go over 9999 (Y10K bug!)
		return projectNumber * 10000 + year;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof ProjectNumber))
			return false;
		ProjectNumber other = (ProjectNumber) obj;
		if (projectNumber != other.projectNumber)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/**
	 * @Author Noah Reinert Sturis (s154407)
	 */
	@Override
	public int compareTo(ProjectNumber other) {
		int last = Integer.compare(this.year, other.year);
		return last == 0 ? Integer.compare(this.projectNumber, other.projectNumber) : last;
	}
	
}
