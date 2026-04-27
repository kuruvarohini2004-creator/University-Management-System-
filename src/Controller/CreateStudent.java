package Controller;
import Model.Class;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;
import sun.security.util.Password;

public class CreateStudent implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		Student s=new Student();
		System.out.println("Enter First Name: ");
		s.setFirstName(scanner.next());
		System.out.println("Enter Last Name: ");
		s.setLastName(scanner.next());
		System.out.println("Enter Email: ");
		s.setEmail(scanner.next());
		System.out.println("Enter Phone Number: ");
		s.setPhoneNumber(scanner.next());
		System.out.println("Enter BirthDate: ");
		s.setBirthDate(scanner.next());
		System.out.println("Enter Class ID (-1 to show all Classes):");
		int classID=scanner.nextInt();
		while(classID<0) {
			new ReadClasses().oper(database, scanner);
			System.out.println("Enter Class ID (-1 to show all Classes):");
			classID=scanner.nextInt();
		}
		s.setClass(new Model.Class(classID, database));
		System.out.println("Enter Password: ");
		String Password=scanner.next();
		System.out.println("Confirm Password: ");
		String confirmPassword=scanner.next();
		while(!Password.equals(confirmPassword)) {
			System.out.println("Password Doesn't Match..");
			System.out.println("Enter Password: ");
			Password=scanner.next();
			System.out.println("Confirm Password: ");
			confirmPassword=scanner.next();
		}
		s.setPassword(Password);
		s.create(database);
	}

}
