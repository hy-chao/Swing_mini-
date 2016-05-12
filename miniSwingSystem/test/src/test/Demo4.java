package test;
/**
 * 完成一个mini版的系统
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
	//定义相关组件
	JPanel jp1,jp2;
	JLabel jl1;//标签
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
		jl1=new JLabel("请输入名字");
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		
		jsp=new JScrollPane();
		
		//中间
		StuModel sm=new StuModel();
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
		
		//最下面
		jp2=new JPanel();
		jb2=new JButton("增加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
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
		
		this.setTitle("学生管理系统");
		this.setSize(500,400);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb1)
		{
			//System.out.println("已经被监听");
			String name=this.jtf.getText().trim();
			String sql="select * from student where 姓名='"+name+"'";
			StuModel sm=new StuModel(sql);
			jt.setModel(sm);
			
		}
		if(arg0.getSource()==jb2)
		{
			StuAddDialog std=new StuAddDialog(this, "添加学生", true);
//			System.out.println("添加");
			StuModel sm=new StuModel();
			jt.setModel(sm);
		}
		if(arg0.getSource() == jb3)
		{
			StuModel sm=new StuModel();
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;
			}
			new StuUpDialog(this, "修改学生", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
	
		}
		if(arg0.getSource() == jb4)
		{
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1)
			{
				JOptionPane.showConfirmDialog(this, "请选择一行");
				return ;
			}
			StuModel sm=new StuModel();
			String stuId = (String)sm.getValueAt(rowNum, 0);
//			System.out.println("id=" + stuId);
			PreparedStatement ps=null;
			Connection ct=null;
			try {
				//1.加载驱动
//				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//				ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=spdb1","sa","nyg1991927");
//				
//				ps=ct.prepareStatement(sql);
//				rs=ps.executeQuery();
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/student?"
		                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
				ct = DriverManager.getConnection(url);
				String sql = "delete from student where 学号=?";
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
