package test;
/**
 * ���һ��mini���ϵͳ
 */
import javax.swing.*;
import javax.swing.text.StyleContext.SmallAttributeSet;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
public class Demo4 extends JFrame implements ActionListener{
	//����������
	JPanel jp1,jp2;
	JLabel jl1;//��ǩ
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	public static void main(String[] args) {
	Demo4 demo4 = new Demo4();

	}
	public Demo4()
	{
		
		jp1=new JPanel();
		jtf=new JTextField(10);
		jl1=new JLabel("����������");
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		
		jsp=new JScrollPane();
		
		//�м�
		StuModel sm=new StuModel();
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
		
		//������
		jp2=new JPanel();
		jb2=new JButton("����");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		
		
		
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		this.add(jsp);
		this.add(jp1,BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		
		this.setTitle("ѧ������ϵͳ");
		this.setSize(500,400);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb1)
		{
			//System.out.println("�Ѿ�������");
			String name=this.jtf.getText().trim();
			String sql="select * from student where ����='"+name+"'";
			StuModel sm=new StuModel(sql);
			jt.setModel(sm);
			
		}
		if(arg0.getSource()==jb2)
		{
			StuAddDialog std=new StuAddDialog(this, "���ѧ��", true);
//			System.out.println("���");
			StuModel sm=new StuModel();
			jt.setModel(sm);
		}
		if(arg0.getSource() == jb3)
		{
			StuModel sm=new StuModel();
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			new StuUpDialog(this, "�޸�ѧ��", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
	
		}
		if(arg0.getSource() == jb4)
		{
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1)
			{
				JOptionPane.showConfirmDialog(this, "��ѡ��һ��");
				return ;
			}
			StuModel sm=new StuModel();
			String stuId = (String)sm.getValueAt(rowNum, 0);
//			System.out.println("id=" + stuId);
			PreparedStatement ps=null;
			Connection ct=null;
			try {
				//1.��������
//				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=spdb1","sa","nyg1991927");
//				
//				ps=ct.prepareStatement(sql);
//				rs=ps.executeQuery();
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/student?"
		                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
				ct = DriverManager.getConnection(url);
				String sql = "delete from student where ѧ��=?";
				ps = ct.prepareStatement(sql);
				ps.setString(1, stuId);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(ps!=null)ps.close();
					if(ct!=null)ct.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sm = new StuModel();
			jt.setModel(sm);
		}
		
		
	}

}
