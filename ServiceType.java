import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class ServiceType extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Service Type");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblCode,lblService_Type_Name,lblService_Type_Duration_hrs,lblService_Type_Cost,lblsubmit;
 
       JTextField txtCode,txtService_Type_Name,txtService_Type_Duration_hrs,txtService_Type_Cost;
      
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       ServiceType()
       {
       super("Service Type Details");
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
       
       lblCode=new JLabel("Code");
       lblService_Type_Name= new JLabel("Service_Name");
       lblService_Type_Duration_hrs=new JLabel("Duration_hrs");
       lblService_Type_Cost=new JLabel("Cost");
 
       lblCode.setBounds(200,140,200,20);
       lblService_Type_Name.setBounds(200,180,200,20);
       lblService_Type_Duration_hrs.setBounds(200,220,200,20);
       lblService_Type_Cost.setBounds(200,260,200,20);

       // add all the label on the frame
       add(lblCode);
       add(lblService_Type_Name);
       add(lblService_Type_Duration_hrs);
       add(lblService_Type_Cost);

       // set font
       lblCode.setFont(f1);
       lblService_Type_Name.setFont(f1);
       lblService_Type_Duration_hrs.setFont(f1);
       lblService_Type_Cost.setFont(f1);
 
       // initialize the textfield with size
       txtCode=new JTextField(15);
       txtService_Type_Name=new JTextField(15);
       txtService_Type_Duration_hrs=new JTextField(15);
       txtService_Type_Cost=new JTextField(15);
  
       // set a particlar position on a screen with setbounds constructor
       txtCode.setBounds(400,140,100,20); 
       txtService_Type_Name.setBounds(400,180,100,20);
       txtService_Type_Duration_hrs.setBounds(400,220,100,20);
       txtService_Type_Cost.setBounds(400,260,100,20); 

       // add textfield on a Frame
       add(txtCode);
       add(txtService_Type_Name);
       add(txtService_Type_Duration_hrs);
       add(txtService_Type_Cost);

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
	 			 txtCode.setText("");
	 		       txtService_Type_Name.setText("");
	 		       txtService_Type_Duration_hrs.setText("");
	 		       txtService_Type_Cost.setText("");
			}

			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Service_Type WHERE Code=" + txtCode.getText() +""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}

			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Service_Type(Code,Service_Name,Duration_hrs,Cost) VALUES (?, ?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtCode.getText());
			    ps.setString(2, txtService_Type_Name.getText());
			    ps.setString(3, txtService_Type_Duration_hrs.getText());
			    ps.setString(4, txtService_Type_Cost.getText());
			    
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
                        //->select microsoft access driver(*.mdb) then finish->give data source name-> select database and press ok
                        // again press ok.
                        con=DriverManager.getConnection("jdbc:odbc:coloredstones_final");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("Select * from Service_Type");
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
			
			 txtCode.setText(rs.getString(1)); 
		       txtService_Type_Name.setText(rs.getString(2)); 
		       txtService_Type_Duration_hrs.setText(rs.getString(3)); 
		       txtService_Type_Cost.setText(rs.getString(4)); 
		       
		
	    	}catch(Exception ex){}		
	    	}
}
