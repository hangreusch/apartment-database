import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MyInterface extends JFrame implements ActionListener
{
	 JLabel choose; 
	 JButton manager = new JButton("Manager");
	 JButton apartment = new JButton("Apartment");
	 JButton complex = new JButton("Apartment Complex");
	 JButton carcolor = new JButton("Colored Car");
	 JButton designated = new JButton("Designated");
	 JButton parking = new JButton("Parking Space");
	 JButton receive = new JButton("Received Service");
	 JButton car = new JButton("Vehicle");
	 JButton provider = new JButton("Service Provider");
	 JButton type = new JButton("Service Type");
	 JButton tenant = new JButton("Tenant");
	 
	 public MyInterface(){
	 setLayout(null);
	 setTitle("MY INTERFACE");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
	 choose= new JLabel ("Please choose table");
	 add(choose);
	 choose.setBounds(100,50,400,100);
	 choose.setFont(new Font("Times",Font.BOLD,20));
	 
     manager.addActionListener(this);
	 apartment.addActionListener(this);
	 complex.addActionListener(this);
	 carcolor.addActionListener(this);
	 designated.addActionListener(this);
	 parking.addActionListener(this);
	 receive.addActionListener(this);
	 car.addActionListener(this);
	 provider.addActionListener(this);
	 type.addActionListener(this);
	 tenant.addActionListener(this);
	 
	 add(manager);
	 add(apartment);
	 add(complex);
	 add(carcolor);
	 add(designated);
	 add(parking);
	 add(receive);
	 add(car);
	 add(provider);
	 add(type);
	 add(tenant);
	 
	 apartment.setBounds(150,150,100,30);
	 complex.setBounds(270,150,180,30);
	 manager.setBounds(470,150,100,30);
	 designated.setBounds(80,200,100,30);
	 parking.setBounds(200,200,180,30);
	 tenant.setBounds(400,200,100,30);
	 provider.setBounds(520,200,200,30);
	 car.setBounds(80,250,100,30);
	 carcolor.setBounds(200,250,180,30);
	 type.setBounds(400,250,110,30);
	 receive.setBounds(520,250,200,30);
	 
	 }
        public static void main(String ar[])throws Exception
        {
        	MyInterface gui= new MyInterface();
        	gui.setSize(800,600);
        	gui.setVisible(true);
        }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action=e.getActionCommand();
			if (action.equals("Apartment")){
				Apartment f1=new Apartment();
		        f1.setSize(800,600);
		        f1.setVisible(true);
			}
			else if (action.equals("Tenant")){
				Tenant f2=new Tenant();
		        f2.setSize(800,600);
		        f2.setVisible(true);
			}
			else if (action.equals("Colored Car")){
				ColorCar f3=new ColorCar();
		        f3.setSize(800,600);
		        f3.setVisible(true);
			}
			else if (action.equals("Service Provider")){
				ServiceProvider f4=new ServiceProvider();
		        f4.setSize(800,600);
		        f4.setVisible(true);
			}
			else if (action.equals("Parking Space")){
			    ParkingSpace f5= new ParkingSpace();
			    f5.setSize(800,600);
	            f5.setVisible(true);
			}
			else if (action.equals("Vehicle")){
				Vehicle f6= new Vehicle();
			    f6.setSize(800,600);
	            f6.setVisible(true);
			}
			else if (action.equals("Designated")){
				Designated f7= new Designated();
			    f7.setSize(800,600);
	            f7.setVisible(true);
			}
			else if (action.equals("Received Service")){
				ReceiveService f9= new ReceiveService();
			    f9.setSize(800,600);
	            f9.setVisible(true);
			}
			else if (action.equals("Apartment Complex")){
				ApartmentComplex f10= new ApartmentComplex();
			    f10.setSize(800,600);
	            f10.setVisible(true);
			}
			else if (action.equals("Manager")){
				Manager f11= new Manager();
			    f11.setSize(800,600);
	            f11.setVisible(true);
			}
			else if (action.equals("Service Type")){
				ServiceType f12= new ServiceType();
			    f12.setSize(800,600);
	            f12.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Unexpected Error", "Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	 
}