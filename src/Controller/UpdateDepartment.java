package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class UpdateDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("ENter Department ID (-1 to show all departments):");
		int ID=scanner.nextInt();
		while(ID<0) {
			new ReadDepartments().oper(database, scanner);
			System.out.println("ENter Department ID (-1 to show all departments):");
			ID=scanner.nextInt();
		}
		Department department=new Department(ID, database);
		scanner.nextLine();
		System.out.println("Enter Department Name (-1 to keep "+department.getName()+"):");
		String name=scanner.nextLine();
		if(!name.equals("-1"))department.setName(name);
		department.update(database);
	}

}
