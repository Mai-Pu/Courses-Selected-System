package com.maipu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.maipu.model.*;
import com.maipu.uti.ConnOracle;

public class Datadao {
	private Connection conn;
	public Datadao()
	{
		conn = ConnOracle.getcon();
	}
	public void close(){
			try {
				if(conn != null)
					conn.close();
				System.out.println("连接断开");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	/*
	 *执行select查询操作 
	 */	
	public boolean select(String sql,DefaultTableModel model){
		PreparedStatement pre = null;
		ResultSet result = null;
		//String sql = "select * from user_tables where table_name=?";
		try {
			pre = conn.prepareStatement(sql);
			//pre.setString(1, "DEPT");
			result = pre.executeQuery();
			boolean setColumn = false;
			while(result.next()){
				Vector<String>vector = new Vector<>();
				int num = result.getMetaData().getColumnCount();
				if(!setColumn){
					for(int i=1;i<=num;i++){
						model.addColumn(result.getMetaData().getColumnName(i));
					}
					setColumn = true;
				}
				for(int i=1;i<=num;i++){
					vector.add(result.getString(i));
				}
				model.addRow(vector);
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
				try {
					if(result != null)
						result.close();
					if(pre != null)
						pre.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
	}
	

	
	private boolean selecttable(String table){
		PreparedStatement pre = null;
		ResultSet result = null;
		String sql = "select table_name from user_tables where table_name=?";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, table);
			result = pre.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(result != null)
						result.close();
					if(pre != null)
						pre.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
		return false;
	}
	
	
	/*
	 * 创建S表，SC表，C表
	 */
	public void create()
	{
		PreparedStatement pre=null;
		String sql_s = "CREATE TABLE S(Sno CHAR(9) PRIMARY KEY,Sname CHAR(20),Sgender CHAR(20),Sage SMALLINT,Sdept CHAR(20))";
		String sql_c = "create table C(Cno CHAR(4) primary key,Cname CHAR(20),Cpno CHAR(4),Ccredit SMALLINT,foreign key(Cpno) references C(Cno))";
		String sql_sc = "create table SC(Sno CHAR(9),Cno CHAR(4),Grade SMALLINT,primary key(Sno,Cno),FOREIGN KEY(Sno) references S(Sno),foreign key(Cno) references C(Cno))";
		if(!selecttable("S")){
			try {
				pre = conn.prepareStatement(sql_s);
				int re=pre.executeUpdate();
				if(re>0)
					System.out.println("已创建S表");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
					try {
						if(pre != null)
							pre.close();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			}
		}
		if(!selecttable("C")){
			try {
				pre = conn.prepareStatement(sql_c);
				int re=pre.executeUpdate();
				if(re>0)
					System.out.println("已创建C表");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				try {
					if(pre != null)
						pre.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		if(!selecttable("SC")){
			try {
				pre = conn.prepareStatement(sql_sc);
				int re=pre.executeUpdate();
				if(re>0)
					System.out.println("已创建SC表");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				try {
					if(pre != null)
						pre.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*
	 * 插入操作
	 */
	public boolean insert_into_S(Student student){
		PreparedStatement pre = null;
		String sql = "insert into S values(?,?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, student.getSno());
			pre.setString(2, student.getSname());
			pre.setString(3, student.getSgender());
			pre.setString(4, student.getSage());
			pre.setString(5, student.getSdept());
			int re=pre.executeUpdate();
			if(re>0){
				System.out.println("插入成功");
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(pre != null)
					pre.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean insert_into_C(Course course){
		PreparedStatement pre = null;
		String sql = "insert into C values(?,?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, course.getCno());
			pre.setString(2, course.getCname());
			pre.setString(3, course.getCpno());
			pre.setString(4, course.getCcredit());
			int re=pre.executeUpdate();
			if(re>0){
				System.out.println("插入成功");
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(pre != null)
					pre.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean insert_into_SC(Student_Course student_course){
		PreparedStatement pre = null;
		String sql = "insert into SC values(?,?,?)";
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, student_course.getSno());
			pre.setString(2, student_course.getCno());
			pre.setString(3, student_course.getGrade());
			int re=pre.executeUpdate();
			if(re>0){
				System.out.println("插入成功");
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(pre != null)
					pre.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/*
	 * 删除操作
	 */
	public boolean delete_from_S(String sno){
		PreparedStatement ps=null;
		String sql="delete from S where Sno = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,sno);
			int a=ps.executeUpdate();
			System.out.println(1);
			if(a>0)
			{
				System.out.println("删除成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean delete_from_C(String cno){
		PreparedStatement ps=null;
		String sql="delete from C where cno = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,cno);
			int a=ps.executeUpdate();
			if(a>0)
			{
				System.out.println("删除成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean delete_from_SC(String sno,String cno){
		PreparedStatement ps=null;
		String sql="delete from SC where Sno = ? and Cno = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,sno);
			ps.setString(2,cno);
			int a=ps.executeUpdate();
			if(a>0)
			{
				System.out.println("删除成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean alter_from_S(int al_where,String al_value,String sno){
		PreparedStatement ps=null;
		String sql = null;
		switch(al_where){
			case 0:
				sql="update S set Sno = ? where Sno = ?";
				break;
			case 1:
				sql="update S set Sname = ? where Sno = ?";
				break;
			case 2:
				sql="update S set Sgender = ? where Sno = ?";
				break;
			case 3:
				sql="update S set Sage = ? where Sno = ?";
				break;
			case 4:
				sql="update S set Sdept = ? where Sno = ?";
				break;
		}
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,al_value);
			ps.setString(2,sno);
			int a=ps.executeUpdate();
			if(a>0)
			{
				System.out.println("修改成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean alter_from_C(int al_where,String al_value,String cno){
		PreparedStatement ps=null;
		String sql=null;
		switch(al_where){
		case 0:
			sql="update C set Cno = ? where Cno = ?";
			break;
		case 1:
			sql="update C set Cname = ? where Cno = ?";
			break;
		case 2:
			sql="update C set Cpno = ? where Cno = ?";
			break;
		case 3:
			sql="update C set Ccredit = ? where Cno = ?";
			break;
		}
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,al_value);
			ps.setString(2,cno);
			int a=ps.executeUpdate();
			if(a>0)
			{
				System.out.println("修改成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean alter_from_SC(int al_where,String al_value,String sno,String cno){
		PreparedStatement ps=null;
		String sql=null;
		switch(al_where){
		case 0:
			sql="update SC set Sno = ? where Sno = ? and Cno = ?";
			break;
		case 1:
			sql="update SC set Cno = ? where Sno = ? and Cno = ?";
			break;
		case 2:
			sql="update SC set Grade = ? where Sno = ? and Cno = ?";
			break;
		}
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,al_value);
			ps.setString(2,sno);
			ps.setString(3, cno);
			int a=ps.executeUpdate();
			if(a>0)
			{
				System.out.println("修改成功");
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
