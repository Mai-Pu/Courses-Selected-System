package com.maipu.uti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class ConnOracle {
	static public Connection getcon(){
		Scanner input = new Scanner(System.in);
		Connection conn = null;
		String username = null;
		String password = null;
		String oracle_url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		while(conn == null){
			System.out.println("开始连接数据库");
			username = JOptionPane.showInputDialog("请输入用户名");
			if(username == null)
				System.exit(0);
			//System.out.print("请输入用户名:");
			//username = input.next();
			//System.out.print("请输入密码:");
			//password = input.next();
			password = JOptionPane.showInputDialog("请输入密码");
			if(password == null)
				System.exit(0);
			try {
				conn = DriverManager.getConnection(oracle_url, username, password);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(conn == null)
				//System.out.println("数据库连接失败");
				JOptionPane.showMessageDialog(null, "账号或密码错误", "错误", JOptionPane.PLAIN_MESSAGE);
			else
				//System.out.println("数据库连接成功");
				JOptionPane.showMessageDialog(null, "数据库连接成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
		}
		return conn;
	}
	
	static public void closeCon(Connection con){
		if(con != null){
			try {
				con.close();
				System.out.println("数据库连接已关闭！");
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ConnOracle.closeCon(ConnOracle.getcon());
	}
}
