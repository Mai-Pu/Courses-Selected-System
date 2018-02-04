package com.maipu.model;

public class Student_Course {
	private String sno;//CHAR(9) PRIMARY KEY
	private String cno;// CHAR(4),PRIMARY KEY
	private String grade;//SMALLINT,
	public Student_Course(String sno,String cno,String grade)
	{
		if(sno == null)
			this.sno = sno;
		else if(sno.length()<=9)
			this.sno = sno;
		else
			this.sno = sno.substring(0, 9);
		
		if(cno == null)
			this.cno = cno;
		else if(cno.length()<=4)
			this.cno = cno;
		else
			this.cno = cno.substring(0, 4);
		
		this.grade = grade;
	}
	public String getSno() {
		return sno;
	}
	public String getCno() {
		return cno;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setSno(String sno) {
		if(sno == null)
			this.sno = sno;
		else if(sno.length()<=9)
			this.sno = sno;
		else
			this.sno = sno.substring(0, 9);
	}
	public void setCno(String cno) {
		if(cno == null)
			this.cno = cno;
		else if(cno.length()<=4)
			this.cno = cno;
		else
			this.cno = cno.substring(0, 4);
	}
	
}
