package com.maipu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.maipu.dao.Datadao;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private boolean isStudent = false;
	private boolean isCourse = false;
	private boolean isSC = false;
	private DefaultTableModel model;
	private Datadao database = new Datadao();
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		database.create();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 417);
		setLocationRelativeTo(null); 
		addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {  
			super.windowClosing(e);  
			database.close();
			}  
		});
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u67E5\u8BE2");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u67E5\u8BE2\u5B66\u751F");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStudent = true;
				isCourse = false;
				isSC = false;
				String sql = "select * from S";
				model.setRowCount(0);
				model.setColumnCount(0);
				database.select(sql, model);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u67E5\u8BE2\u8BFE\u7A0B");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStudent = false;
				isCourse = true;
				isSC = false;
				String sql = "select * from C";
				model.setRowCount(0);
				model.setColumnCount(0);
				database.select(sql, model);
			}
		});
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u67E5\u8BE2\u9009\u8BFE");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStudent = false;
				isCourse = false;
				isSC = true;
				String sql = "select * from SC";
				model.setRowCount(0);
				model.setColumnCount(0);
				database.select(sql, model);
			}
		});
		menu.add(menuItem_2);
		
		JMenuItem mntmSql = new JMenuItem("SQL\u67E5\u8BE2");
		mntmSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = JOptionPane.showInputDialog("请输入SQL语句");
				if(sql != null){
					isStudent = false;
					isCourse = false;
					isSC = false;
					boolean success = false;
					model.setRowCount(0);
					model.setColumnCount(0);
					success = database.select(sql, model);
					if(!success)
						JOptionPane.showMessageDialog(null, "查询失败,请检查SQL语句是否正确", "警告", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		JMenuItem menuItem_6 = new JMenuItem("\u591A\u8868\u8FDE\u63A5\u67E5\u8BE2");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStudent = false;
				isCourse = false;
				isSC = false;
				model.setRowCount(0);
				model.setColumnCount(0);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ChoiceFrame frame = new ChoiceFrame(model,database);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menu.add(menuItem_6);
		menu.add(mntmSql);
		
		JMenu menu_1 = new JMenu("\u6DFB\u52A0");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u6DFB\u52A0\u5B66\u751F");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isStudent){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertSF frame = new InsertSF(null,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertSF frame = new InsertSF(model,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u6DFB\u52A0\u8BFE\u7A0B");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isCourse){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertCF frame = new InsertCF(null,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertCF frame = new InsertCF(model,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		menu_1.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u6DFB\u52A0\u9009\u8BFE");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isSC){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertSC frame = new InsertSC(null,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InsertSC frame = new InsertSC(model,database);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		menu_1.add(menuItem_5);
		
		JMenu menu_3 = new JMenu("\u5220\u9664");
		menu_3.setFocusable(false);
		menuBar.add(menu_3);
		
		JMenuItem menuItem_7 = new JMenuItem("\u5220\u9664\u9009\u5B9A\u884C");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row>-1){
					int res=JOptionPane.showConfirmDialog(null, "是否删除第"+Integer.toString(row+1)+"行", "是否继续", JOptionPane.YES_NO_OPTION);
				    if(res==JOptionPane.YES_OPTION){ 
				    	if(isStudent){
							boolean success = database.delete_from_S((String)model.getValueAt(row, 0));
							if(success){
								JOptionPane.showMessageDialog(null, "删除成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.removeRow(row);
							}else
								JOptionPane.showMessageDialog(null, "删除失败", "警告", JOptionPane.PLAIN_MESSAGE);
						}
						else if(isCourse){
							boolean success = database.delete_from_C((String)model.getValueAt(row, 0));
							if(success){
								JOptionPane.showMessageDialog(null, "删除成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.removeRow(row);
							}else
								JOptionPane.showMessageDialog(null, "删除失败", "警告", JOptionPane.PLAIN_MESSAGE);
						}else if(isSC){
							boolean success = database.delete_from_SC((String)model.getValueAt(row, 0),(String)model.getValueAt(row, 1));
							if(success){
								JOptionPane.showMessageDialog(null, "删除成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.removeRow(row);
							}else
								JOptionPane.showMessageDialog(null, "删除失败", "警告", JOptionPane.PLAIN_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "只能从学生表，课程表和选课表中进行删除", "警告", JOptionPane.PLAIN_MESSAGE);
						}
				    }
				}
			}
		});
		menu_3.add(menuItem_7);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFocusable(false);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				int c=  table.getSelectedColumn(); 
				if(r>-1&&c>-1)
				{
					String al_value = JOptionPane.showInputDialog("请输入修改值");
					if(al_value != null)
					{
						if(isStudent){
							boolean success = database.alter_from_S(c, al_value, (String)model.getValueAt(r, 0));
							if(success){
								JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.setValueAt(al_value, r, c);
							}else{
								JOptionPane.showMessageDialog(null, "修改失败", "警告", JOptionPane.PLAIN_MESSAGE);
							}
						}else if(isCourse){
							boolean success = database.alter_from_C(c, al_value, (String)model.getValueAt(r, 0));
							if(success){
								JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.setValueAt(al_value, r, c);
							}else{
								JOptionPane.showMessageDialog(null, "修改失败", "警告", JOptionPane.PLAIN_MESSAGE);
							}
						}else if(isSC){
							boolean success = database.alter_from_SC(c, al_value, (String)model.getValueAt(r, 0),(String)model.getValueAt(r, 1));
							if(success){
								JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
								model.setValueAt(al_value, r, c);
							}else{
								JOptionPane.showMessageDialog(null, "修改失败", "警告", JOptionPane.PLAIN_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "只能从学生表，课程表和选课表中进行修改", "警告", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
			}
		});
		menuBar.add(btnNewButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {
				 return false;
			 }
		};
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
	}

}
