package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Operation;

public class AddNewEmployee implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter First Name: ");
		String firstName =scanner.next();
		System.out.println("Enter Last Name: ");
		String lastName=scanner.next();
		System.out.println("Enter Email: ");
		String email=scanner.next();
		System.out.println("Enter Phone Number: ");
		String phoneNumber=scanner.next();
		System.out.println("Enter BirthDate: ");
		String birthDate=scanner.next();
		System.out.println("Enter Salary: ");
		double salary=scanner.nextDouble();
		System.out.println("Enter Department ID: ");
		int deptID=scanner.nextInt();
		while(deptID<0) {
			new ShowAllDepartments().oper(database, scanner);
			System.out.println("Enter Department ID: ");
			deptID=scanner.nextInt();
		}
		System.out.println("Enter Password: ");
		String password=scanner.next();
		System.out.println("Confirm Password: ");
		String confirmPassword=scanner.next();
		while(!confirmPassword.equals(password)) {
			System.out.println("Enter Password: ");
			password=scanner.next();
			System.out.println("Confirm Password: ");
			confirmPassword=scanner.next();
		}
		try {
			ArrayList<Employee> employees=new ShowAllEmployees().getEmployees(database);
			int ID=0;
			if(employees.size()!=0) {
				ID=employees.get(employees.size()-1).getID()+1;
			}
			
			String insert = "INSERT INTO employee(FirstName,LastName,Email,PhoneNumber,BirthDate,Salary,DepartmentID,Password) VALUES('"
			        + firstName + "','"
			        + lastName + "','"
			        + email + "','"
			        + phoneNumber + "','"
			        + birthDate + "',"
			        + salary + ","
			        + deptID + ",'"
			        + password + "')";

			database.getStatement().execute(insert);
			System.out.println("Employee Added Succesfully....");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	



}
