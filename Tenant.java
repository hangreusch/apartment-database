import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class Tenant extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Tenant Information");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblvrn,lblname,lblhomephone,lblworkphone,lblstname,lblstnumber, lblsttype, lblsubmit;
       JTextField vrn,name,homephone,workphone,stname,stnumber, sttype;
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       Tenant()
       {
       super("Tenant Details");
       addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                System.exit(0);
                }
       });
       // set layout to null
       setLayout(null);

       lblsubmit=new JLabel("Developed By: Hang & Mahgol. Thanks for using this program!");
       add(lblsubmit);
       lblsubmit.setBounds(420,540,350,20);

       // add lbl label on form.
       add(lbl);

       // set the particular position on a screen
       lbl.setBounds(300,50,300,30);

       // set the font of lbl label 
       lbl.setFont(f);
      
       // initialize all the label which are declared in the example above with its caption name 
       lblvrn=new JLabel("VRN"); 
       lblname=new JLabel("NAME");
       lblhomephone=new JLabel("HOME PHONE");
       lblworkphone=new JLabel("WORK PHONE");
       lblstname=new JLabel("STREET NAME");
       lblstnumber=new JLabel("STREET NUMBER");
       lblsttype=new JLabel("STREET TYPE");
       
       // set the particular position on a screen
       lblvrn.setBounds(200,140,200,20);
       lblname.setBounds(200,180,200,20);
       lblhomephone.setBounds(200,220,200,20);
       lblworkphone.setBounds(200,260,200,20);
       lblstname.setBounds(200,300,200,20);
       lblstnumber.setBounds(200,340,200,20);
       lblsttype.setBounds(200,380,200,20);

       // add all the label on the frame
       add(lblvrn);
       add(lblname);
       add(lblhomephone);
       add(lblworkphone);
       add(lblstname);
       add(lblstnumber);
       add(lblsttype);

       // set font
       lblvrn.setFont(f1);
       lblname.setFont(f1);
       lblhomephone.setFont(f1);
       lblworkphone.setFont(f1);
       lblstname.setFont(f1);
       lblstnumber.setFont(f1);
       lblsttype.setFont(f1);

       // initialize the textfield with size
       vrn=new JTextField(15);
       name=new JTextField(15);
       homephone=new JTextField(15);
       workphone=new JTextField(15);
       stname=new JTextField(15);
       stnumber=new JTextField(15);
       sttype=new JTextField(15);
       
       // set a particlar position on a screen with setbounds constructor
       vrn.setBounds(400,140,100,20); 
       name.setBounds(400,180,100,20);
       homephone.setBounds(400,220,100,20);
       workphone.setBounds(400,260,100,20);
       stname.setBounds(400,300,100,20);
       stnumber.setBounds(400,340,100,20);
       sttype.setBounds(400,380,100,20);

       // add textfield on a Frame
       add(vrn); 
       add(name);
       add(homephone);
       add(workphone);
       add(stname);
       add(stnumber);
       add(sttype);
       
       // initialize button with its caption
       btnadd=new JButton("Add");
       btnsave=new JButton("Save");
       btndelete=new JButton("Delete");
       btnfirst=new JButton("First");
       btnnext=new JButton("Next");
       btnprev=new JButton("Previous");
       btnlast=new JButton("Last");
       btnexit=new JButton("Exit");

       // set a particular position on a Frame
       btnadd.setBounds(200,420,100,30);
       btnsave.setBounds(310,420,100,30);
       btndelete.setBounds(420,420,100,30);
       btnfirst.setBounds(200,460,100,30);
       btnnext.setBounds(310,460,100,30);
       btnprev.setBounds(420,460,100,30);
       btnlast.setBounds(530,460,100,30);
       btnexit.setBounds(530,420,100,30);

       // register all the button
       btnadd.addActionListener(this);
       btnsave.addActionListener(this);
       btndelete.addActionListener(this);
       btnfirst.addActionListener(this);
       btnnext.addActionListener(this);
       btnprev.addActionListener(this);
       btnlast.addActionListener(this);
       btnexit.addActionListener(this);

       // add all the button on frame
       add(btnadd);
       add(btnsave);
       add(btndelete);
       add(btnfirst);
       add(btnnext);
       add(btnprev);
       add(btnlast);
       add(btnexit);
       
       // open database connection
       // here we call a dbopen() method
       dbOpen();
       }
       
       public void actionPerformed(ActionEvent ae) {
	   try
		{
	 		if(ae.getActionCommand()=="Add")
			{
				vrn.setText(""); 
				name.setText("");
				homephone.setText("");
				workphone.setText("");
				stname.setText("");
				stnumber.setText("");
				sttype.setText("");
				
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Tenant WHERE VRN=" + vrn.getText() + "");			
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				
				dbClose();
				dbOpen();
			}	
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Tenant(vrn, tenant_name,home_phone, work_phone,st_name, st_no, st_type) VALUES (?, ?,?,?,?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, vrn.getText());
			    ps.setString(2, name.getText());
			    ps.setString(3, homephone.getText());
			    ps.setString(4, workphone.getText());
			    ps.setString(5, stname.getText());
			    ps.setString(6, stnumber.getText());
			    ps.setString(7, sttype.getText());
			    ps.addBatch();
			    
			    ps.executeBatch();
			    ps.close();
			    JOptionPane.showMessageDialog(null, "Save Successfully", "Message", JOptionPane.INFORMATION_MESSAGE); 
				
				dbClose();
				dbOpen();
			}
			if(ae.getActionCommand()=="Next")
			{
				if(rs.next())
				{
					setText();                 			
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You are At Already Last Record", "Message", JOptionPane.ERROR_MESSAGE);	
                }
			}
			if(ae.getActionCommand()=="Previous")
			{
				if(rs.previous())
				{
                  			setText();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You Are At Already First Record", "Message", JOptionPane.ERROR_MESSAGE);
                }
			}
			if (ae.getActionCommand()=="First")
			{
				if(rs.first())
				{
					setText();
				}
			}
			if (ae.getActionCommand()=="Last")
			{
				if(rs.last())
				{
					setText();
				}
			}
			if(ae.getActionCommand()=="Exit")
			{
				MyInterface gui= new MyInterface();
	        	gui.setSize(800,600);
	        	gui.setVisible(true);	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The record you enter is not correct", "Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void dbOpen()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            // here in this statement mydsn is a DSN name which u have to create before run this program
            // step to create dsn
            // open control panel-> open administrativr tools-> open data source(ODBC)-> press add
            //->select microsoft access driver(*.mdb,*accdb) then finish->give data source name-> select database and press ok
            // again press ok.
            con=DriverManager.getConnection("jdbc:odbc:coloredstones_final");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("Select * from Tenant");
			if(rs.next())
			setText();
		}catch(Exception e){}
	}
	
	public void dbClose()
	{
		try{stmt.close();
		rs.close();
		con.close();
		}catch(Exception e){}
	}
	
	public void setText(){
		try{
			vrn.setText(rs.getString(1));
			name.setText(rs.getString(2));
			homephone.setText(rs.getString(3));
			workphone.setText(rs.getString(4));
			stname.setText(rs.getString(5));
			stnumber.setText(rs.getString(6));
			sttype.setText(rs.getString(7));
	    	}catch(Exception ex){}		
	    	}
}

