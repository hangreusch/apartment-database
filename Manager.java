import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class Manager extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Manager");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblEIN,lblProperty_MGR_Name,lblProperty_MGR_Home_Phone,lblProperty_MGR_MobilePhone ,lblProperty_MGR_EmailAddress,lblsubmit;
 
       JTextField txtEIN,txtProperty_MGR_Name,txtProperty_MGR_Home_Phone,txtProperty_MGR_MobilePhone ,txtProperty_MGR_EmailAddress;
      
       
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       Manager()
       {
       super("Property Manager Details");
       addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                System.exit(0);
                }
       });
       // set layout to null
       setLayout(null);

       lblsubmit=new JLabel("Developed By : Mahgol and hang.");
       add(lblsubmit);
       lblsubmit.setBounds(420,540,350,20);

       // add lbl label on form.
       add(lbl);

       // set the particular position on a screen
       lbl.setBounds(300,50,300,30);

       // set the font of lbl label 
       lbl.setFont(f);
      
       // initializa all the label which are declared in the example above with its caption name 
       lblEIN=new JLabel("EIN");
       lblProperty_MGR_Name=new JLabel("Name");
       lblProperty_MGR_Home_Phone=new JLabel("Home Phone");
       lblProperty_MGR_MobilePhone=new JLabel("Mobile Phone");
       lblProperty_MGR_EmailAddress=new JLabel("Email");
       
 
       lblEIN.setBounds(200,140,100,20);
       lblProperty_MGR_Name.setBounds(200,180,100,20);
       lblProperty_MGR_Home_Phone.setBounds(200,220,100,20);
       lblProperty_MGR_MobilePhone.setBounds(200,260,100,20);
       lblProperty_MGR_EmailAddress.setBounds(200,300,100,20);
 
       // add all the label on the frame
       add(lblEIN);
       add(lblProperty_MGR_Name);
       add(lblProperty_MGR_Home_Phone);
       
       add(lblProperty_MGR_MobilePhone);
       add(lblProperty_MGR_EmailAddress);
  
       // set font
       lblEIN.setFont(f1);
       lblProperty_MGR_Name.setFont(f1);
       lblProperty_MGR_Home_Phone.setFont(f1);
       lblProperty_MGR_MobilePhone.setFont(f1);
       lblProperty_MGR_EmailAddress.setFont(f1);
       
       // initialize the textfield with size
       txtEIN=new JTextField(15);
       txtProperty_MGR_Name=new JTextField(22);
       txtProperty_MGR_Home_Phone=new JTextField(22);
       txtProperty_MGR_MobilePhone=new JTextField(22);
       txtProperty_MGR_EmailAddress=new JTextField(22);
      
  
       // set a particlar position on a screen with setbounds constructor
       
       txtEIN.setBounds(400,140,200,20);
       txtProperty_MGR_Name.setBounds(400,180,200,20);
       txtProperty_MGR_Home_Phone.setBounds(400,220,200,20);
       txtProperty_MGR_MobilePhone.setBounds(400,260,200,20);
       txtProperty_MGR_EmailAddress.setBounds(400,300,200,20);
      
       // add textfield on a Frame
       add(txtEIN);
       add(txtProperty_MGR_Name);
       add(txtProperty_MGR_Home_Phone);
       

       add( txtProperty_MGR_MobilePhone);
       add(txtProperty_MGR_EmailAddress);
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
	 		    txtEIN.setText("");
	 	       txtProperty_MGR_Name.setText("");
	 	       txtProperty_MGR_Home_Phone.setText("");
	 	       txtProperty_MGR_MobilePhone.setText("");
	 	       txtProperty_MGR_EmailAddress.setText("");
			}

			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Manager WHERE EIN=" + txtEIN.getText() +""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}

			if(ae.getActionCommand()=="Save")
			{
				stmt.executeUpdate("INSERT INTO Manager VALUES('" + txtEIN.getText() + "','" + txtProperty_MGR_Name.getText() + "','" + txtProperty_MGR_Home_Phone.getText() + "'," + txtProperty_MGR_MobilePhone.getText() + ",'" + txtProperty_MGR_EmailAddress.getText()+gen+"')");
				JOptionPane.showMessageDialog(null, "Save Successfully", "Message", JOptionPane.INFORMATION_MESSAGE); 
				dbClose();
				dbOpen();
			}
			
			if(ae.getActionCommand()=="Next")
			{
				if(rs.next())
				{
					setText();                 			setText();
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
                        //->select microsoft access driver(*.mdb) then finish->give data source name-> select database and press ok
                        // again press ok.
                        con=DriverManager.getConnection("jdbc:odbc:coloredstones_final");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("Select * from Manager");
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
			txtEIN.setText(rs.getString(1));
	 	       txtProperty_MGR_Name.setText(rs.getString(2));
	 	       txtProperty_MGR_Home_Phone.setText(rs.getString(3));
	 	       txtProperty_MGR_MobilePhone.setText(rs.getString(4));
	 	       txtProperty_MGR_EmailAddress.setText(rs.getString(5));
	 	       
		
		       
			
		       
		
	    	}catch(Exception ex){}		
	    	}
}
