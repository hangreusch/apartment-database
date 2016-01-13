import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class ServiceProvider extends JFrame implements ActionListener
{
       JLabel lbl=new JLabel("Service Provider Information");

       Font f=new Font("Times",Font.BOLD,26);
       Font f1=new Font("Times",Font.BOLD,14);
       
       JLabel lblspin,lblname,lblphone,lblemail,lblurl,lblcity, lblstate, lblpostal, lblstno, lblstname, lbltype, lblsubmit;
       JTextField txtspin,txtname,txtphone,txtemail,txturl,txtcity, txtstate, txtpostal,txtstno, txtstname, txttype;
       JButton btnadd,btnsave,btndelete,btnexit;
       JButton btnnext,btnprev,btnlast,btnfirst;
       String gen;
       
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       ServiceProvider()
       {
       super("Service Provider Details");
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
       lblspin=new JLabel("SPIN"); 
       lblname=new JLabel("NAME");
       lblphone=new JLabel("PHONE");
       lblemail=new JLabel("EMAIL");
       lblurl=new JLabel("URL");
       lblcity=new JLabel("CITY");
       lblstate=new JLabel("STATE");
       lblpostal=new JLabel("POSTAL");
       lblstno=new JLabel("STREET NUMBER");
       lblstname=new JLabel("STREET NAME");
       lbltype=new JLabel("STREET TYPE");
       
       // set the particular position on a screen
       lblspin.setBounds(200,100,200,20);
       lblname.setBounds(200,130,200,20);
       lblphone.setBounds(200,160,200,20);
       lblemail.setBounds(200,190,200,20);
       lblurl.setBounds(200,220,200,20);
       lblcity.setBounds(200,250,200,20);
       lblstate.setBounds(200,280,200,20);
       lblpostal.setBounds(200,310,200,20);
       lblstno.setBounds(200,340,200,20);
       lblstname.setBounds(200,370,200,20);
       lbltype.setBounds(200,400,200,20);

       // add all the label on the frame
       add(lblspin);
       add(lblname);
       add(lblphone);
       add(lblemail);
       add(lblurl);
       add(lblcity);
       add(lblstate);
       add(lblpostal);
       add(lblstno);
       add(lblstname);
       add(lbltype);

       // set font
       lblspin.setFont(f1);
       lblname.setFont(f1);
       lblphone.setFont(f1);
       lblemail.setFont(f1);
       lblurl.setFont(f1);
       lblcity.setFont(f1);
       lblstate.setFont(f1);
       lblpostal.setFont(f1);
       lblstno.setFont(f1);
       lblstname.setFont(f1);
       lbltype.setFont(f1);
       
       // initialize the textfield with size
       txtspin=new JTextField(20);
       txtname=new JTextField(20);
       txtphone=new JTextField(20);
       txtemail=new JTextField(20);
       txturl=new JTextField(20);
       txtcity=new JTextField(20);
       txtstate=new JTextField(20);
       txtpostal=new JTextField(20);
       txtstno=new JTextField(20);
       txtstname=new JTextField(20);
       txttype=new JTextField(20);
       
       // set a particlar position on a screen with setbounds constructor
       txtspin.setBounds(400,100,150,20); 
       txtname.setBounds(400,130,150,20);
       txtphone.setBounds(400,160,150,20);
       txtemail.setBounds(400,190,150,20);
       txturl.setBounds(400,220,150,20);
       txtcity.setBounds(400,250,150,20);
       txtstate.setBounds(400,280,150,20);
       txtpostal.setBounds(400,310,150,20);
       txtstno.setBounds(400,340,150,20);
       txtstname.setBounds(400,370,150,20);
       txttype.setBounds(400,400,150,20);

       // add textfield on a Frame
       add(txtspin); 
       add(txtname);
       add(txtphone);
       add(txtemail);
       add(txturl);
       add(txtcity);
       add(txtstate);
       add(txtpostal);
       add(txtstno);
       add(txtstname);
       add(txttype);
       
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
				txtspin.setText("");
				txtname.setText("");
				txtphone.setText("");
				txtemail.setText("");
				txturl.setText("");
				txtcity.setText("");
				txtstate.setText("");
				txtpostal.setText("");
				txtstno.setText("");
				txtstname.setText("");
				txttype.setText("");
			}
			if(ae.getActionCommand()=="Delete")
			{
				stmt.executeUpdate("DELETE FROM Service_Provider WHERE SPIN=" + txtspin.getText() +"");						
				JOptionPane.showMessageDialog(null, "Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				
				dbClose();
				dbOpen();
			}	
			if(ae.getActionCommand()=="Save")
			{
				String sql = "INSERT INTO Service_Provider(spin, provider_name,telephone, email, url,city, state, postal, st_no, st_name, st_type) VALUES (?, ?,?,?,?,?,?,?,?,?,?)";
			    PreparedStatement ps = con.prepareStatement(sql);
			     
			    ps.setString(1, txtspin.getText());
			    ps.setString(2, txtname.getText());
			    ps.setString(3, txtphone.getText());
			    ps.setString(4, txtemail.getText());
			    ps.setString(5, txturl.getText());
			    ps.setString(6, txtcity.getText());
			    ps.setString(7, txtstate.getText());
			    ps.setString(8, txtpostal.getText());
			    ps.setString(9, txtstno.getText());
			    ps.setString(10, txtstname.getText());
			    ps.setString(11, txttype.getText());
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
			rs = stmt.executeQuery("Select * from Service_Provider");
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
			txtspin.setText(rs.getString(1));
			txtname.setText(rs.getString(2));
			txtphone.setText(rs.getString(3));
			txtemail.setText(rs.getString(4));
			txturl.setText(rs.getString(5));
			txtcity.setText(rs.getString(6));
			txtstate.setText(rs.getString(7));
			txtpostal.setText(rs.getString(8));
			txtstno.setText(rs.getString(9));
			txtstname.setText(rs.getString(10));
			txttype.setText(rs.getString(11));
	    	}catch(Exception ex){}		
	    	}
}


