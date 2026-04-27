package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class CreateDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Department Name: ");
		String Name=scanner.nextLine();
		
		int ID=0;
		ArrayList<Department> departments = new ReadDepartments().getDepatments(database);
		if(departments.size()!=0) {
			ID=departments.get(departments.size()-1).getID()+1;
		}
		Department department=new Department();
		department.setID(ID);
		department.setName(Name);
		department.create(database);
	}

}
