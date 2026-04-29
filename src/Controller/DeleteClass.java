package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Class;

public class DeleteClass implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Class ID (-1 to show all classes): ");
		int ID=scanner.nextInt();
		while(ID<0) {
			new ReadClasses().oper(database, scanner);
			System.out.println("Enter Class ID (-1 to show all classes): ");
			ID=scanner.nextInt();
		}
		Class c = new Class(ID);
		c.delete(database);
	}

}
