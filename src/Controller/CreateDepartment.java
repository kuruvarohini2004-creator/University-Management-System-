package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class CreateDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Department Name: ");
		String deptName=scanner.next();
	}

}
