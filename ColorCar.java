import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class ColorCar extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Colored Car Information");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblrnnum,lblcolor,lblsubmit;
       JTextField txtrnnum,txtcolor;
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       ColorCar()
       {
       super("Colored Car Details");
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
       lbl.setBounds(300,50,400,30);

       // set the font of lbl label 
       lbl.setFont(f);
      
       // initialize all the label which are declared in the example above with its caption name 
       lblrnnum=new JLabel("RN_NUM"); 
       lblcolor=new JLabel("COLOR");
       
       // set the particular position on a screen
       lblrnnum.setBounds(200,140,200,20);
       lblcolor.setBounds(200,180,200,20);
       
       // add all the label on the frame
       add(lblrnnum);
       add(lblcolor);
       
       // set font
       lblrnnum.setFont(f1);
       lblcolor.setFont(f1);
       
       // initialize the textfield with size
       txtrnnum=new JTextField(15);
       txtcolor=new JTextField(15);
       
       // set a particlar position on a screen with setbounds constructor
       txtrnnum.setBounds(400,140,100,20); 
       txtcolor.setBounds(400,180,100,20);
       
       // add textfield on a Frame
       add(txtrnnum); 
       add(txtcolor);
       
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
				txtrnnum.setText("");
				txtcolor.setText("");	
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Colored_Car WHERE RN_number=" + txtrnnum.getText() + " and"+" Color='"+txtcolor.getText()+"'"+""); 	                
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				dbClose();
				dbOpen();
			}	
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Colored_Car(rn_number, color) VALUES (?, ?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtrnnum.getText());
			    ps.setString(2, txtcolor.getText());
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
			rs = stmt.executeQuery("Select * from Colored_Car");
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
			txtrnnum.setText(rs.getString(1));
			txtcolor.setText(rs.getString(2));
	    	}catch(Exception ex){}		
	    	}
}


