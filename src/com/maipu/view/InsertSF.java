package com.maipu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.maipu.dao.Datadao;
import com.maipu.model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.print.attribute.TextSyntax;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InsertSF extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InsertSF frame = new InsertSF(null,null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public InsertSF(DefaultTableModel model,Datadao database) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 339, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setBounds(10, 10, 86, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(9, 28, 304, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setBounds(12, 49, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 66, 303, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setBounds(14, 90, 54, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 115, 303, 16);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5E74\u9F84\uFF1A");
		label_3.setBounds(12, 137, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 156, 302, 15);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("\u7CFB\uFF1A");
		label_4.setBounds(10, 181, 54, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 206, 303, 18);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()!=0){
					String sno = textField.getText();
					String sname = null;
					String sgender = null;
					String sage = null;
					String sdept = null;
					if(textField_1.getText().length()!=0)
						sname = textField_1.getText();
					if(textField_2.getText().length()!=0)
						sgender = textField_2.getText();
					if(textField_3.getText().length()!=0)
						sage = textField_3.getText();
					if(textField_4.getText().length()!=0)
						sdept = textField_4.getText();
					Student student = new Student(sno, sname, sgender, sage, sdept);
	
					boolean success = database.insert_into_S(student);
					if(success)
						JOptionPane.showMessageDialog(null, "插入成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "插入失败，请检查输入是否正确", "警告", JOptionPane.PLAIN_MESSAGE);
					
					if(success && model!=null){
						Vector<String> vi = new Vector<>();
						vi.add(sno);
						vi.add(sname);
						vi.add(sgender);
						vi.add(sage);
						vi.add(sdept);
						model.addRow(vi);
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入学号", "警告", JOptionPane.PLAIN_MESSAGE);
				}
				dispose();
			}
		});
		button.setFocusable(false);
		button.setBounds(110, 233, 93, 23);
		contentPane.add(button);
	}
}
