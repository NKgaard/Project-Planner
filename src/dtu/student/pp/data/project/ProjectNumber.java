package dtu.student.pp.data.project;

import java.io.Serializable;

import org.junit.Ignore;

public class ProjectNumber implements Serializable{
	
	final int year;
	final int projectNumber;
	
	public ProjectNumber(int year, int projectNumber) {
		this.year = year;
		this.projectNumber = projectNumber;
	}
	
	@Override
	public String toString() {
		//Format: YYNNNN - Last 2 digits of year, plus minimum four digits of projectCnt.
		return (year%100)+String.format("%04d", this.projectNumber);
	}
	
	@Override
	public int hashCode() {
		//Perfect hashing as long as year doesn't go over 9999 (Y10K bug!)
		return projectNumber * 10000 + year;
	}
	
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
	
}
