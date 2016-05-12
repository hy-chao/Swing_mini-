package test;
/**
 * ����ѧ����ģ��
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.*;
public class StuModel extends AbstractTableModel{


	Vector rowData,columnNames;
	
	//����������ݿ�
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	//�򻯴���ϲ��������캯��
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from student";
			
		}
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
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=spdb1","sa","nyg1991927");
//			
//			ps=ct.prepareStatement(sql);
//			rs=ps.executeQuery();
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/student?"
	                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
			ct = DriverManager.getConnection(url);
//			System.out.println("hello");
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(ct!=null)ct.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	
	//�ڶ������캯��
	public StuModel(String sql)
	{
		this.init(sql);
	}
	
	public StuModel()
	{
		this.init("");
	}
	
	//�õ�����
	public int getColumnCount() {
		
		return this.columnNames.size();
	}
	//�õ�����
	public int getRowCount() {
		
		return this.rowData.size();
	}
	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		
		return ((Vector)this.rowData.get(row)).get(column);
	}

	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(arg0);
	}

}
