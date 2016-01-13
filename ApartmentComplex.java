import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class ApartmentComplex extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Apartment Complex");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblCIN,lblApt_complex_name,lblAptcomplexCity,lblAptcomplexstate, lblcomplexPostalCode,lblMGIN, lblAptStNo,lblAptStName,lblAptStType,lblsubmit;
 
       JTextField txtCIN,txtApt_complex_name, txtAptcomplexCity, txtAptcomplexstate, txtcomplexPostalCode, txtMGIN, txtAptStNo,txtAptStName, txtAptStType;
      
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       ApartmentComplex()
       {
       super("Apartment Complex Details");
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
       lblCIN=new JLabel("CIN");
       lblApt_complex_name=new JLabel("Complex_name");
       lblAptcomplexCity=new JLabel("City");
       lblAptcomplexstate=new JLabel("State");
       lblcomplexPostalCode=new JLabel("Postal_Code");
       lblMGIN=new JLabel("MGEIN");
       lblAptStNo=new JLabel("St No");
       lblAptStName=new JLabel("St Name");
       lblAptStType=new JLabel("St Type");
       
       lblCIN.setBounds(200,130,200,20);
       lblApt_complex_name.setBounds(200,160,200,20);
       lblAptcomplexCity.setBounds(200,190,200,20);
       lblAptcomplexstate.setBounds(200,220,200,20);
       lblcomplexPostalCode.setBounds(200,250,200,20);
       lblMGIN.setBounds(200,280,200,20);
       lblAptStNo.setBounds(200,310,200,20);
       lblAptStName.setBounds(200,340,200,20);
       lblAptStType.setBounds(200,370,200,20);
       
      
       
       // add all the label on the frame
       add(lblCIN);
       add( lblApt_complex_name);
       add(lblAptcomplexCity);
       add(lblAptcomplexstate);
       add(lblcomplexPostalCode);
       add(lblMGIN);
       add(lblAptStNo);
       add(lblAptStName);
       add( lblAptStType);
       
       // set font
       lblCIN.setFont(f1);
       lblApt_complex_name.setFont(f1);
       lblAptcomplexCity.setFont(f1);
       lblAptcomplexstate.setFont(f1);
       lblcomplexPostalCode.setFont(f1);
       lblMGIN.setFont(f1);
       lblAptStNo.setFont(f1);
       lblAptStName.setFont(f1);
       lblAptStType.setFont(f1);

       // initialize the textfield with size
       txtCIN=new JTextField(15);
       txtApt_complex_name=new JTextField(15);
       txtAptcomplexCity=new JTextField(15);
       txtAptcomplexstate=new JTextField(15);
       txtcomplexPostalCode=new JTextField(15);
       txtMGIN=new JTextField(15);
       txtAptStNo=new JTextField(15);
       txtAptStName=new JTextField(15);
       txtAptStType=new JTextField(15);

       // set a particlar position on a screen with setbounds constructor
       txtCIN.setBounds(400,130,100,20); 
       txtApt_complex_name.setBounds(400,160,100,20);
       txtAptcomplexCity.setBounds(400,190,100,20);
       txtAptcomplexstate.setBounds(400,220,100,20);
       txtcomplexPostalCode.setBounds(400,250,100,20);
       txtMGIN.setBounds(400,280,100,20);
       txtAptStNo.setBounds(400,310,100,20);
       
       txtAptStName.setBounds(400,340,100,20);
       txtAptStType.setBounds(400,370,100,20);
       
     

       // add textfield on a Frame
       add(txtCIN);
       add(txtApt_complex_name);
       add(txtAptcomplexCity);
       add(txtAptcomplexstate);
       add(txtcomplexPostalCode);
       add(txtMGIN);
       add(txtAptStNo);
       add(txtAptStName);
       add(txtAptStType );
       
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
	 			txtCIN.setText(""); 
	 	       txtApt_complex_name.setText("");
	 	       txtAptcomplexCity.setText("");
	 	       txtAptcomplexstate.setText("");
	 	       txtcomplexPostalCode.setText("");
	 	       txtMGIN.setText("");
	 	       txtAptStNo.setText("");  
	 	       txtAptStName.setText("");
	 	       txtAptStType.setText("");
			}
			if(ae.getActionCommand()=="Delete")
			{				
				stmt.executeUpdate("DELETE FROM Apartment_Complex WHERE CIN=" + txtCIN.getText()+""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Apartment_complex(CIN,complex_name,City,State,Postal_Code,MGEIN,St_No,St_Name,St_Type) VALUES (?, ?,?,?,?,?,?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtCIN.getText());
			    ps.setString(2,txtApt_complex_name.getText());
			    ps.setString(3, txtAptcomplexCity.getText());
			    ps.setString(4, txtAptcomplexstate.getText());
			    ps.setString(5, txtcomplexPostalCode.getText());
			    ps.setString(6, txtMGIN.getText());
			    ps.setString(7, txtAptStNo.getText());
			    ps.setString(8, txtAptStName.getText());
			    ps.setString(9, txtAptStType.getText());
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
			rs = stmt.executeQuery("Select * from Apartment_complex");
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
			txtCIN.setText(rs.getString(1)); 
	 	       txtApt_complex_name.setText(rs.getString(2));
	 	       txtAptcomplexCity.setText(rs.getString(3));
	 	       txtAptcomplexstate.setText(rs.getString(4));
	 	       txtcomplexPostalCode.setText(rs.getString(5));
	 	       txtMGIN.setText(rs.getString(6));
	 	       txtAptStNo.setText(rs.getString(7));
	 	       
	 	       txtAptStName.setText(rs.getString(8));
	 	       txtAptStType.setText(rs.getString(9));
		
	    	}catch(Exception ex){}		
	    	}
}


