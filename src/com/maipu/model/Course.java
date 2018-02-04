package com.maipu.model;

public class Course {
	private String cno;//CHAR(4) PRIMARY KEY,
	private String cname;// CHAR(20),
	private String cpno;// CHAR(4),
	private String ccredit;//SMALLINT,
	public Course(String cno,String cname,String cpno,String ccredit)
	{
		if(cno == null)
			this.cno = cno;
		else if(cno.length()<=4)
			this.cno = cno;
		else
			this.cno = cno.substring(0, 4);
		
		if(cname == null)
			this.cname = cname;
		else if(cname.length()<=20)
			this.cname = cname;
		else
			this.cname = cname.substring(0, 20);
		
		if(cpno == null)
			this.cpno = cpno;
		else if(cpno.length()<=4)
			this.cpno = cpno;
		else
			this.cpno = cpno.substring(0, 4);
		
		this.ccredit = ccredit;
	}
	public String getCno() {
		return cno;
	}
	public String getCname() {
		return cname;
	}
	public String getCpno() {
		return cpno;
	}
	public void setCno(String cno) {
		if(cno == null)
			this.cno = cno;
		else if(cno.length()<=4)
			this.cno = cno;
		else
			this.cno = cno.substring(0, 4);
	}
	public void setCname(String cname) {
		if(cname == null)
			this.cname = cname;
		else if(cname.length()<=20)
			this.cname = cname;
		else
			this.cname = cname.substring(0, 20);
	}
	public void setCpno(String cpno) {
		if(cpno == null)
			this.cpno = cpno;
		else if(cpno.length()<=4)
			this.cpno = cpno;
		else
			this.cpno = cpno.substring(0, 4);
	}
	public String getCcredit() {
		return ccredit;
	}
	public void setCcredit(String ccredit) {
		this.ccredit = ccredit;
	}
	
}
