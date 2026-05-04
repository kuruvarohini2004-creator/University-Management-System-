package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;
import Model.Class;

public class CreateCourse implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		Course c=new Course();
		int ID=0;
		ArrayList<Course> courses=new ReadCourses().getAllCourses(database);
		if(courses.size()!=0) {
			ID= courses.get(courses.size()-1).getID()+1;
		}
		c.setID(ID);
		
		
		System.out.println("Enter Course Name: ");
		c.setName(scanner.nextLine());
		
		System.out.println("Enter Class ID (-1 to show all classes):");
		int classID=scanner.nextInt();
		while(classID<0) {
			new ReadClasses().oper(database, scanner);
			System.out.println("Enter Class ID (-1 to show all classes):");
			classID=scanner.nextInt();
		}
		c.setClass(new Class(classID,database));
		scanner.nextLine();
		System.out.println("Enter Course Description: ");
		c.setDescription(scanner.nextLine());
		
		System.out.println("Enter Course Limit (int): ");
		c.setLimit(scanner.nextInt());
		
		Employee emp = null;

		while (true) {
		    System.out.println("Enter Prof ID (-1 to show all employees):");
		    int employeeID = scanner.nextInt();

		    if (employeeID == -1) {
		        new ReadEmployees().oper(database, scanner);
		        continue;
		    }

		    try {
		        emp = new Employee(employeeID, database);
		        break; // ✅ valid ID → exit loop
		    } catch (Exception e) {
		        System.out.println("❌ Invalid Employee ID. Try again.");
		    }
		}

		c.setProf(emp);
		
		System.out.println("Enter Department ID (-1 to show all departments):");
		int deptID=scanner.nextInt();
		while(deptID<0) {
			new ReadDepartments().oper(database, scanner);
			System.out.println("Enter Department ID (-1 to show all departments):");
			deptID=scanner.nextInt();
		}
		c.setDept(new Department(deptID,database));
		
		c.create(database);
	}

}
