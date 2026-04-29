package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String birthDate;
	private Model.Class c;
	private String password;
	public Student() {
		super();
	}
	public Student(int ID,Database database) {
		this.ID=ID;
		String select="SELECT * FROM student WHERE ID = "+ID+";";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			rs.next();
			setFirstName(rs.getString("FirstName"));
			setLastName(rs.getString ("LastName"));
			setEmail(rs.getString("Email"));
			setPhoneNumber(rs.getString("PhoneNumber")); 
			setBirthDate(rs.getString("BirthDate"));
			setPassword(rs.getString("Password"));
			setClass(new Class(rs.getInt("Class"),database));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Student(int ID) {
		this.ID=ID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public Class getCurrentClass() {
		return c;
	}
	public void setClass(Class c) {
		this.c = c;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void print() {
		System.out.println("ID: \t\t"+getID());
		System.out.println("Name: \t\t"+getFirstName()+" "+getLastName());
		System.out.println("Email: \t\t"+getEmail());
		System.out.println("Phone Number: \t"+getPhoneNumber());
		System.out.println("Birth Date: \t"+getBirthDate());
		System.out.println("Class: \t\t"+c.getName());
		System.out.println("-------------------------------------------\n");
		
	}
	
public void create(Database database) {
	String insert = "INSERT INTO student (FirstName, LastName, Email, PhoneNumber, BirthDate, `Class`, Password) VALUES "
	        + "('" + firstName + "','" + lastName + "','"
	        + email + "','" + phoneNumber + "','" + birthDate + "','" + c.getID() + "','" + password + "');";
	try {
		database.getStatement().execute(insert);
		System.out.println("Student Added Successfully...");
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

    public void update(Database database) {
    	String update="UPDATE student SET FirstName='"+firstName+"',"
    			+"LastName='"+lastName+"',Email='"+email+"',"
    			+"PhoneNumber ='"+phoneNumber+"',BirthDate ='"+birthDate+"',"
    					+ "Class ='"+c.getID()+"',Password ='"+password+"' "
    					+"WHERE ID = "+ID+";";
    
    try {
		database.getStatement().execute(update);
		System.out.println("Student Updated Successfully...");
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
    }
    public void delete(Database database) {
    	String delete="DELETE FROM student WHERE ID ="+ID+";";
    	try {
			database.getStatement().execute(delete);
			System.out.println("Student Deleted Successfully...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
