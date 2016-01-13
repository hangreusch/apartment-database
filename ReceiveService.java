import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class ReceiveService extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Receive Service");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblCIN,lblCode,lblSPIN,lblRecieved_Service_Date,lblsubmit;
 
       JTextField txtCIN,txtCode,txtSPIN,txtRecieved_Service_Date;
      
       
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       ReceiveService()
       {
       super("Receive service Details");
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
       lblCode=new JLabel("Code");
       lblSPIN=new JLabel("SPIN");
       lblRecieved_Service_Date=new JLabel("Date");
       
       
       lblCIN.setBounds(300,140,100,20);
       lblCode.setBounds(300,180,100,20);
       lblSPIN.setBounds(300,220,100,20);
       lblRecieved_Service_Date.setBounds(300,260,100,20);
 
       // add all the label on the frame
       add( lblCIN);
       add(lblCode);
       add(lblSPIN);
       add(lblRecieved_Service_Date);
 
       // set font
       lblCIN.setFont(f1);
       lblCode.setFont(f1);
       lblSPIN.setFont(f1);
       lblRecieved_Service_Date.setFont(f1);

       // initialize the textfield with size
       txtCIN=new JTextField(15);
       txtCode=new JTextField(15);
       txtSPIN=new JTextField(15);
       txtRecieved_Service_Date=new JTextField(15);

       // set a particlar position on a screen with setbounds constructor
       txtCIN.setBounds(400,140,100,20);
       txtCode.setBounds(400,180,100,20);
       txtSPIN.setBounds(400,220,100,20);
       txtRecieved_Service_Date.setBounds(400,260,100,20);
        
       // add textfield on a Frame
       add(txtCIN);
       add(txtCode);
       add(txtSPIN);
       add(txtRecieved_Service_Date);
     
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
	 	       txtCode.setText("");
	 	       txtSPIN.setText("");
	 	       txtRecieved_Service_Date.setText("");
	 	 
			}
        	if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Receive_Service WHERE CIN=" + txtCIN.getText() + " and"+" Code="+txtCode.getText()+" and"+ " SPIN="+txtSPIN.getText()+""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}
        	if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Receive_Service(CIN, Code,SPIN,Recieved_Service_Date) VALUES (?, ?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtCIN.getText());
			    ps.setString(2, txtCode.getText());
			    ps.setString(3, txtSPIN.getText());
			    ps.setString(4, txtRecieved_Service_Date.getText());
			   
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
			rs = stmt.executeQuery("Select * from Receive_Service");
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
		       txtCode.setText(rs.getString(2));
		       txtSPIN.setText(rs.getString(3));
		       txtRecieved_Service_Date.setText(rs.getString(4));

	    	}catch(Exception ex){}		
	    	}
}

