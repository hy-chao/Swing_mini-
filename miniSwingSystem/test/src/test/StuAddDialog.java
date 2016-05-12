package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener{
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	

	
	public StuAddDialog(Frame owner,String title,boolean model)
	{
		super(owner,title,model);
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("����ϵ");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		jb1=new JButton("���");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setSize(250,200);
		this.setLocation(200,100);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb1)
		{
			Vector rowData,columnNames;
			
			//����������ݿ�
			PreparedStatement ps=null;
			Connection ct=null;
			ResultSet rs=null;
			
			//�������ݿ�
			columnNames=new Vector();
			columnNames.add("ѧ��");
			columnNames.add("����");
			columnNames.add("�Ա�");
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("����ϵ");
			
			rowData=new Vector();
			try {
				//1.��������
//				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=spdb1","sa","nyg1991927");
//				String sql="insert into stu values(?,?,?,?,?,?)";
//				
//				ps=ct.prepareStatement(sql);
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/student?"
		                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
				ct = DriverManager.getConnection(url);
				String sql = "insert into student values(?,?,?,?,?,?)";
				
				ps = ct.prepareStatement(sql);
				//��������ֵ
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				ps.setString(3, jtf3.getText());
				ps.setString(4, jtf4.getText());
				ps.setString(5, jtf5.getText());
				ps.setString(6, jtf6.getText());
				
				ps.executeUpdate();
				
				this.dispose();
				
				
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
//			System.out.println("hello");
		}
		if(arg0.getSource() == jb2)
		{
//			System.out.println("ȡ��");
			//�رյ�ǰ����
			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
		}
		
	}
}
