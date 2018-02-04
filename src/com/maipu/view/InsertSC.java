package com.maipu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.maipu.dao.Datadao;
import com.maipu.model.Student;
import com.maipu.model.Student_Course;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InsertSC extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertSC frame = new InsertSC(null,null);
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
	public InsertSC(DefaultTableModel model,Datadao database) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 301, 257);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(10, 35, 260, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		label_1.setBounds(10, 66, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 90, 260, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6210\u7EE9\uFF1A");
		label_2.setBounds(10, 125, 54, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 149, 260, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()!=0 && textField_1.getText().length()!=0){
					String sno = textField.getText();
					String cno = textField_1.getText();
					String grade = null;
					if(textField_2.getText().length()!=0)
						grade = textField_2.getText();
					Student_Course student_Course = new Student_Course(sno, cno, grade);
					
					boolean success = database.insert_into_SC(student_Course);
					if(success)
						JOptionPane.showMessageDialog(null, "插入成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "插入失败，请检查输入是否正确", "警告", JOptionPane.PLAIN_MESSAGE);
					
					if(success && model!=null){
						Vector<String> vi = new Vector<>();
						vi.add(sno);
						vi.add(cno);
						vi.add(grade);
						model.addRow(vi);
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入学号和课程号", "警告", JOptionPane.PLAIN_MESSAGE);
				}
				dispose();
			}
		});
		button.setBounds(91, 190, 93, 23);
		contentPane.add(button);
		setLocationRelativeTo(null); 
	}

}
