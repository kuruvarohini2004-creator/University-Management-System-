package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Controller.CreateClass;
import Controller.CreateCourse;
import Controller.CreateDepartment;
import Controller.CreateEmployee;
import Controller.CreateStudent;
import Controller.DeleteClass;
import Controller.DeleteCourse;
import Controller.DeleteDepartment;
import Controller.DeleteEmployee;
import Controller.DeleteStudent;
import Controller.ReadClasses;
import Controller.ReadCourses;
import Controller.ReadDepartments;
import Controller.ReadEmployees;
import Controller.ReadStudents;
import Controller.UpdateClass;
import Controller.UpdateCourse;
import Controller.UpdateDepartment;
import Controller.UpdateEmployee;
import Controller.UpdateStudent;

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
			String select = "SELECT ID, FirstName, LastName, Email, PhoneNumber, "
		+ " BirthDate , Salary, DepartmentID, Password FROM employee "

		+ "WHERE ID = "+ID+" ;";
			ResultSet rs = database.getStatement().executeQuery(select);
			if (rs.next()) {
			    setID(ID);
			    setFirstName(rs.getString("FirstName"));
			    setLastName(rs.getString("LastName"));
			    setEmail(rs.getString("Email"));
			    setPhoneNumber(rs.getString("PhoneNumber"));
			    setBirthDate(rs.getString("BirthDate"));
			    setSalary(rs.getDouble("Salary"));
			    setPassword(rs.getString("Password"));

			    int deptID = rs.getInt("DepartmentID");
			    setDepartment(new Department(deptID, database));
			} else {
			    throw new RuntimeException("❌ Invalid Employee ID: " + ID);
			}
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
	private Operation[] managerOperations=null;
	private Operation[] getManagerOperations() {
			if (managerOperations==null) {
				managerOperations=new Operation[] {
						new CreateDepartment(),
						new ReadDepartments(),
						new UpdateDepartment(),
						new DeleteDepartment(),
						new CreateClass(),
						new ReadClasses(),
						new UpdateClass(),
						new DeleteClass(),
						new CreateCourse(),
						new ReadCourses(),
						new UpdateCourse(),
						new DeleteCourse(),
						new CreateEmployee(),
						new ReadEmployees(),
						new UpdateEmployee(),
						new DeleteEmployee(),
						new CreateStudent(),
						new ReadStudents(),
						new UpdateStudent(),
						new DeleteStudent()
				};
			}
			return managerOperations;
			
	};
	 public void showList(Database database,Scanner scanner) {
		 boolean running =true;
		 while(running) {
			 if (department.getName().equals("Management")) {
				 System.out.println("\n-------------------------------");
				System.out.println("1. Add new Department");
				System.out.println("2. Show all departments");
				System.out.println("3. Edit Department");
				System.out.println("4. Delete Department");
				System.out.println("5. Add New Class");
				System.out.println("6. Show all Classes");
				System.out.println("7. Edit Class");
				System.out.println("8. Delete Class");
				System.out.println("9. Add new Course");
				System.out.println("10. Show all Course");
				System.out.println("11. Edit Class");
				System.out.println("12. Delete Course");
				System.out.println("13. Add new Employee");
				System.out.println("14. Show all Employees");
				System.out.println("15. Edit Employee");
				System.out.println("16. Delete Employee");
				System.out.println("17. Add new Student");
				System.out.println("18. Show all Studnets");
				System.out.println("19. Edit Student");
				System.out.println("20. Delete Student");
				System.out.println("---------------------------------\n");
				
			System.out.println("Enter Choice: ");
			int selected=scanner.nextInt();
			
			if(selected==0) {
				running =false;
				System.out.println("Logged out successfully!");
			}
			else if(selected>=1&&selected<=20){
				managerOperations[selected-1].oper(database, scanner);
			} else {
				System.out.println("Invalid Choice. Please try again.");
			}
		}else {
			System.out.println("\n-------------------------------");
			System.out.println("1. Read Departments");
			System.out.println("2. Read Classes");
			System.out.println("3. Read Courses");
			System.out.println("4. Add Course Grades");
			System.out.println("5. Show Course Grades");
			System.out.println("6. Edit Course Grades");
			System.out.println("7. Delete Course Grades");
			System.out.println("0. Logout");
			System.out.println("-------------------------------\n");
			System.out.println("Enter Choice: ");
			int selected=scanner.nextInt();
			if(selected==0) {
				running=false;
				System.out.println("Logged out successfully!");
			}else {
				System.out.println("Feature not yet implemented.");
			}
		}
	    	
	 }
	 }
}
