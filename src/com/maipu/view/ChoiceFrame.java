package com.maipu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.maipu.dao.Datadao;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ChoiceFrame extends JFrame {

	private JPanel contentPane;
	private List<JCheckBox> choicelist = new ArrayList<JCheckBox>();
	private List<String> namelist = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceFrame frame = new ChoiceFrame(null,null);
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
	public ChoiceFrame(DefaultTableModel model,Datadao database) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 295, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); 
		setResizable(false);
		contentPane.setLayout(null);
		
		JCheckBox checkBox_1_1 = new JCheckBox("\u5B66\u53F7");
		checkBox_1_1.setBounds(6, 6, 103, 23);
		contentPane.add(checkBox_1_1);
		
		JCheckBox checkBox_2_1 = new JCheckBox("\u8BFE\u7A0B\u53F7");
		checkBox_2_1.setBounds(111, 6, 103, 23);
		contentPane.add(checkBox_2_1);
		
		JCheckBox checkBox_3 = new JCheckBox("\u6210\u7EE9");
		checkBox_3.setBounds(216, 6, 103, 23);
		contentPane.add(checkBox_3);
		
		JCheckBox checkBox_1_2 = new JCheckBox("\u59D3\u540D");
		checkBox_1_2.setBounds(6, 31, 103, 23);
		contentPane.add(checkBox_1_2);
		
		JCheckBox checkBox_1_3 = new JCheckBox("\u6027\u522B");
		checkBox_1_3.setBounds(6, 56, 103, 23);
		contentPane.add(checkBox_1_3);
		
		JCheckBox checkBox_1_4 = new JCheckBox("\u5E74\u9F84");
		checkBox_1_4.setBounds(6, 81, 103, 23);
		contentPane.add(checkBox_1_4);
		
		JCheckBox checkBox_1_5 = new JCheckBox("\u7CFB");
		checkBox_1_5.setBounds(6, 106, 103, 23);
		contentPane.add(checkBox_1_5);
		
		JCheckBox checkBox_2_2 = new JCheckBox("\u8BFE\u7A0B\u540D");
		checkBox_2_2.setBounds(111, 31, 103, 23);
		contentPane.add(checkBox_2_2);
		
		JCheckBox checkBox_2_3 = new JCheckBox("\u5148\u884C\u8BFE\u53F7");
		checkBox_2_3.setBounds(111, 56, 103, 23);
		contentPane.add(checkBox_2_3);
		
		JCheckBox checkBox_2_4 = new JCheckBox("\u5B66\u5206");
		checkBox_2_4.setBounds(111, 81, 103, 23);
		contentPane.add(checkBox_2_4);
		
		choicelist.add(checkBox_1_1);
		choicelist.add(checkBox_1_2);
		choicelist.add(checkBox_1_3);
		choicelist.add(checkBox_1_4);
		choicelist.add(checkBox_1_5);
		choicelist.add(checkBox_2_1);
		choicelist.add(checkBox_2_2);
		choicelist.add(checkBox_2_3);
		choicelist.add(checkBox_2_4);
		choicelist.add(checkBox_3);
		
		namelist.add("S.Sno");
		namelist.add("S.Sname");
		namelist.add("S.Sgender");
		namelist.add("S.Sage");
		namelist.add("S.Sdept");
		namelist.add("C.Cno");
		namelist.add("C.Cname");
		namelist.add("C.Cpno");
		namelist.add("C.Ccredit");
		namelist.add("Grade");
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean select_student = checkBox_1_1.isSelected() || checkBox_1_2.isSelected() || checkBox_1_3.isSelected() || checkBox_1_4.isSelected() || checkBox_1_5.isSelected();
				boolean select_course = checkBox_2_1.isSelected() || checkBox_2_2.isSelected() || checkBox_2_3.isSelected() || checkBox_2_4.isSelected();
				boolean select_sc = checkBox_3.isSelected();
				StringBuilder sb = new StringBuilder();
				if(select_student){
					for(int i = 0 ; i < 10 ; i++)
					{
						if(choicelist.get(i).isSelected()){
							if(sb.length() == 0)
								sb.append(" ");
							else
								sb.append(",");
							sb.append(namelist.get(i));
						}
					}
					if(select_course){
						String sql = "select" + sb.toString() +" from S,SC,C where S.Sno=SC.Sno and SC.Cno=C.Cno";
						database.select(sql, model);
					}else if(select_sc){
						String sql = "select" + sb.toString() +" from S,SC where S.Sno=SC.Sno";
						database.select(sql, model);
					}else{
						String sql = "select" + sb.toString() +" from S";
						database.select(sql, model);
					}
				}
				else if(select_course){
					for(int i = 5 ; i < 10 ; i++)
					{
						if(choicelist.get(i).isSelected()){
							if(sb.length() == 0)
								sb.append(" ");
							else
								sb.append(",");
							sb.append(namelist.get(i));
						}
					}
					if(select_sc){
						String sql = "select" + sb.toString() +" from SC,C where SC.Cno=C.Cno";
						database.select(sql, model);
					}else{
						String sql = "select" + sb.toString() +" from C";
						database.select(sql, model);
					}
				}
				else if(select_sc){
					String sql = "select Grade from SC";
					database.select(sql, model);
				}
				dispose();
			}
		});
		button.setFocusable(false);
		button.setBounds(186, 106, 93, 23);
		contentPane.add(button);
	}
}
