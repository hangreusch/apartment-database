import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class Apartment extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Apartment Information");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblacin,lblaptnumber,lblbathroom,lblbedroom,lblrental,lblcarpet, lblaptvrn, lblsubmit;
       JTextField txtacin,txtaptnumber,txtbathroom,txtbedroom,txtrental,txtcarpet,txtaptvrn;
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       Apartment()
       {
       super("Apartment Details");
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
       lblacin=new JLabel("ACIN"); 
       lblaptnumber=new JLabel("APT_NUMBER");
       lblbathroom=new JLabel("NUMBER OF BATHROOM");
       lblbedroom=new JLabel("NUMBER OF BEDROOM");
       lblrental=new JLabel("MONTHLY RENTAL");
       lblcarpet=new JLabel("CARPET COLOR");
       lblaptvrn=new JLabel("APTVRN");
       
       // set the particular position on a screen
       lblacin.setBounds(200,140,200,20);
       lblaptnumber.setBounds(200,180,200,20);
       lblbathroom.setBounds(200,220,200,20);
       lblbedroom.setBounds(200,260,200,20);
       lblrental.setBounds(200,300,200,20);
       lblcarpet.setBounds(200,340,200,20);
       lblaptvrn.setBounds(200,380,200,20);

       // add all the label on the frame
       add(lblacin);
       add(lblaptnumber);
       add(lblbathroom);
       add(lblbedroom);
       add(lblrental);
       add(lblcarpet);
       add(lblaptvrn);

       // set font
       lblacin.setFont(f1);
       lblaptnumber.setFont(f1);
       lblbathroom.setFont(f1);
       lblbedroom.setFont(f1);
       lblrental.setFont(f1);
       lblcarpet.setFont(f1);
       lblaptvrn.setFont(f1);

       // initialize the textfield with size
       txtacin=new JTextField(15);
       txtaptnumber=new JTextField(15);
       txtbathroom=new JTextField(15);
       txtbedroom=new JTextField(15);
       txtrental=new JTextField(15);
       txtcarpet=new JTextField(15);
       txtaptvrn=new JTextField(15);
       
       // set a particlar position on a screen with setbounds constructor
       txtacin.setBounds(400,140,100,20); 
       txtaptnumber.setBounds(400,180,100,20);
       txtbathroom.setBounds(400,220,100,20);
       txtbedroom.setBounds(400,260,100,20);
       txtrental.setBounds(400,300,100,20);
       txtcarpet.setBounds(400,340,100,20);
       txtaptvrn.setBounds(400,380,100,20);

       // add textfield on a Frame
       add(txtacin); 
       add(txtaptnumber);
       add(txtbathroom);
       add(txtbedroom);
       add(txtrental);
       add(txtcarpet);
       add(txtaptvrn);
       
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
				txtacin.setText("");
				txtaptnumber.setText("");
				txtbedroom.setText("");
				txtbathroom.setText("");
				txtrental.setText("");
				txtcarpet.setText("");
				txtaptvrn.setText("");
				
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Apartment WHERE acin=" + txtacin.getText() + " and"+" apt_number="+txtaptnumber.getText()+"");			
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}	
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Apartment(acin,Apt_number,bathroom,bedroom,rental, carpet, aptvrn) VALUES (?,?,?,?,?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtacin.getText());
			    ps.setString(2, txtaptnumber.getText());
			    ps.setString(3, txtbathroom.getText());
			    ps.setString(4, txtbedroom.getText());
			    ps.setString(5, txtrental.getText());
			    ps.setString(6, txtcarpet.getText());
			    ps.setString(7, txtaptvrn.getText());
			    
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
			rs = stmt.executeQuery("Select * from Apartment");
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
			txtacin.setText(rs.getString(1));
			txtaptnumber.setText(rs.getString(2));
			txtbedroom.setText(rs.getString(3));
			txtbathroom.setText(rs.getString(4));
			txtrental.setText(rs.getString(5));
			txtcarpet.setText(rs.getString(6));
			txtaptvrn.setText(rs.getString(7));
	    	}catch(Exception ex){}		
	    	}
}

