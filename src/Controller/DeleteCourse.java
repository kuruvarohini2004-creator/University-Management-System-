package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;

public class DeleteCourse implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Course ID (-1 to show all courses): ");
		int ID=scanner.nextInt();
		while(ID<0) {
			new ReadCourses().oper(database, scanner);
			System.out.println("Enter Course ID(-1 to show all courses): ");
			ID=scanner.nextInt();
		}
		Course c=new Course();
		c.delete(database);
	}

}
