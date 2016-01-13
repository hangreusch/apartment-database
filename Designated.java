import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class Designated extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Designated");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblDesignated_RN_Number,lblDesignated_Apt_Number,lblDesignatedACIN,lblsubmit;
 
       JTextField txtDesignated_RN_Number,txtDesignated_Apt_Number,txtDesignatedACIN;
      
       
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       Designated()
       {
       super("Designated Details");
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
       
       
       lblDesignated_RN_Number=new JLabel("Designated_RN_Number");
       lblDesignated_Apt_Number=new JLabel("Designated_Apt_Number");
       lblDesignatedACIN=new JLabel("DesignatedACIN");
       
    
       lblDesignated_RN_Number.setBounds(200,140,200,20);
       lblDesignated_Apt_Number.setBounds(200,180,200,20);
       lblDesignatedACIN.setBounds(200,220,200,20);

       
      

       
       // add all the label on the frame
       add(lblDesignated_RN_Number);
       add(lblDesignated_Apt_Number);
       add(lblDesignatedACIN);
       
     
      
       
       // set font
       lblDesignated_RN_Number.setFont(f1);;
       lblDesignated_Apt_Number.setFont(f1);;
       lblDesignatedACIN.setFont(f1);;

     
       // initialize the textfield with size
      
       txtDesignated_RN_Number=new JTextField(15);
       txtDesignated_Apt_Number=new JTextField(15);
       txtDesignatedACIN=new JTextField(15);
    
  
       // set a particlar position on a screen with setbounds constructor
       txtDesignated_RN_Number.setBounds(400,140,100,20); 
       txtDesignated_Apt_Number.setBounds(400,180,100,20);
       txtDesignatedACIN.setBounds(400,220,100,20);
       
       // add textfield on a Frame
       add(txtDesignated_RN_Number);
       add(txtDesignated_Apt_Number);
       add(txtDesignatedACIN);
       
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
	 			  txtDesignated_RN_Number.setText("");
	 		       txtDesignated_Apt_Number.setText("");
	 		       txtDesignatedACIN.setText("");
	   
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Designated WHERE Designated_RN_Number=" + txtDesignated_RN_Number.getText() + " and"+" Designated_Apt_Number="+txtDesignated_Apt_Number.getText()+"and "+" DesignatedACIN="+txtDesignatedACIN.getText()+""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}
			
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Designated(Designated_RN_Number,Designated_Apt_Number,DesignatedACIN) VALUES (?, ?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtDesignated_RN_Number.getText());
			    ps.setString(2, txtDesignated_Apt_Number.getText());
			    ps.setString(3, txtDesignatedACIN.getText());
			   
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
			rs = stmt.executeQuery("Select * from Designated");
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
			txtDesignated_RN_Number.setText(rs.getString(1));
		       txtDesignated_Apt_Number.setText(rs.getString(2));
		       txtDesignatedACIN.setText(rs.getString(3));
	
	    	}catch(Exception ex){}		
	    	}
}

