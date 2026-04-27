package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class DeleteDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Department ID (-1 to show all departments):");
		int ID =scanner.nextInt();
		while(ID<0) {
			new ReadDepartments().oper(database, scanner);
			System.out.println("Enter Department ID (-1 to show all departments):");
		    ID =scanner.nextInt();
		}
		Department d=new Department();
		d.delete(database);
	}

}
