package com.maipu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.maipu.dao.Datadao;
import com.maipu.model.Course;
import com.maipu.model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InsertCF extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertCF frame = new InsertCF(null,null);
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
	public InsertCF(DefaultTableModel model,Datadao database) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 366, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(10, 33, 330, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		label_1.setBounds(10, 67, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 90, 327, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5148\u884C\u8BFE\u8BFE\u7A0B\u53F7\uFF1A");
		label_2.setBounds(10, 122, 100, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 142, 330, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u5206\uFF1A");
		lblNewLabel.setBounds(10, 170, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 195, 330, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()!=0){
					String cno = textField.getText();
					String cname = null;
					String cpno = null;
					String ccredit = null;
					if(textField_1.getText().length()!=0)
						cname = textField_1.getText();
					if(textField_2.getText().length()!=0)
						cpno = textField_2.getText();
					if(textField_3.getText().length()!=0)
						ccredit = textField_3.getText();
					Course course = new Course(cno, cname, cpno, ccredit);
	
					boolean success = database.insert_into_C(course);
					if(success)
						JOptionPane.showMessageDialog(null, "插入成功", "恭喜", JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "插入失败，请检查输入是否正确", "警告", JOptionPane.PLAIN_MESSAGE);
					
					if(success && model!=null){
						Vector<String> vi = new Vector<>();
						vi.add(cno);
						vi.add(cname);
						vi.add(cpno);
						vi.add(ccredit);
						model.addRow(vi);
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入课程号号", "警告", JOptionPane.PLAIN_MESSAGE);
				}
				dispose();
			}
		});
		button.setBounds(128, 235, 93, 23);
		contentPane.add(button);
		setLocationRelativeTo(null); 
	}
}
