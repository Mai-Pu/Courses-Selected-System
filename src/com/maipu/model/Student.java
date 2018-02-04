package com.maipu.model;

public class Student {
	private String sno;//CHAR(9) PRIMARY KEY,
	private String sname;// CHAR(20),
	private String sgender;// CHAR(20),
	private String sage;//SMALLINT,
	private String sdept;// CHAR(20)
	public Student(String sno,String sname,String sgender,String sage,String sdept)
	{
		if(sno == null)
			this.sno = sno;
		else if(sno.length()<=9)
			this.sno = sno;
		else
			this.sno = sno.substring(0, 9);
		
		if(sname == null)
			this.sname = sname;
		else if(sname.length()<=20)
			this.sname = sname;
		else
			this.sname = sname.substring(0, 20);
		
		if(sgender == null)
			this.sgender = sgender;
		else if(sgender.length()<=20)
			this.sgender = sgender;
		else
			this.sgender = sgender.substring(0, 20);
		
		this.sage = sage;
		
		if(sdept == null)
			this.sdept = sdept;
		else if(sdept.length()<=20)
			this.sdept = sdept;
		else
			this.sdept = sdept.substring(0, 20);
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		if(sno == null)
			this.sno = sno;
		else if(sno.length()<=9)
			this.sno = sno;
		else
			this.sno = sno.substring(0, 9);
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		if(sname == null)
			this.sname = sname;
		else if(sname.length()<=20)
			this.sname = sname;
		else
			this.sname = sname.substring(0, 20);
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		if(sgender == null)
			this.sgender = sgender;
		else if(sgender.length()<=20)
			this.sgender = sgender;
		else
			this.sgender = sgender.substring(0, 20);
	}
	public String getSage() {
		return sage;
	}
	public void setSage(String sage) {
		this.sage = sage;
	}
	public String getSdept() {
		return sdept;
	}
	public void setSdept(String sdept) {
		if(sdept == null)
			this.sdept = sdept;
		else if(sdept.length()<=20)
			this.sdept = sdept;
		else
			this.sdept = sdept.substring(0, 20);
	}
	
	
}
