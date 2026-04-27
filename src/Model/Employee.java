package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String birthDate;
	private double salary;
	private String password;
	private Department department;
	public Employee() {
		super();
	}
	public Employee(int ID,Database database) {
		try {
			String select = "SELECT ID, FirstName, LastName, Email, PhoneNumber, "+ " BirthDate , Salary, DepartmentID, Password FROM employee "

		+ "WHERE ID = "+ID+" ;";
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			setID(ID);
			setFirstName(rs.getString("FirstName"));
	        setLastName(rs.getString("LastName"));
	        setEmail(rs.getString("Email"));
	        setPhoneNumber(rs.getString("PhoneNumber"));
	        setBirthDate(rs.getString("BirthDate"));
	        setSalary(rs.getDouble("Salary"));
	        setPassword(rs.getString("Password"));
	        int deptID=rs.getInt("DepartmentID");
	        setDepartment(new Department(deptID,database));
			}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void update(Database database) {
		
				try {
					String update= "UPDATE employee SET FirstName='"+getFirstName()+ "',LastName='"+getLastName()+"',Email='"+getEmail()+"',"
							+ "PhoneNumber='"+getPhoneNumber()+"',BirthDate='"+getBirthDate()+"',Salary='"+getSalary()+"',"
									+ "DepartmentID='"+getDepartment().getID()+"',Password='"+getPassword()+"' WHERE ID="+getID()+";";
					database.getStatement().execute(update);
					System.out.println("Employee Updated Succesfully...");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public void print() {
		System.out.println("ID: \t\t"+getID());
		System.out.println("Name: \t\t"+getFirstName()+" "+getLastName());
		System.out.println("Email: \t\t"+getEmail());
		System.out.println("Phone Number: \t"+getPhoneNumber());
		System.out.println("Birth Date: \t"+getBirthDate());
		System.out.println("Salary: \t"+getSalary());
		System.out.println("Department ID: \t"+getDepartment().getName());
		System.out.println("-------------------------------------------\n");
		
	}

	public void create(Database database) {
		try {
			String insert = "INSERT INTO employee(FirstName,LastName,Email,PhoneNumber,BirthDate,Salary,DepartmentID,Password) VALUES('"
			        + firstName + "','"
			        + lastName + "','"
			        + email + "','"
			        + phoneNumber + "','"
			        + birthDate + "',"
			        + salary + ","
			        + department.getID() + ",'"
			        + password + "')";

			database.getStatement().execute(insert);
			System.out.println("Employee Added Succesfully....");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
