package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class DeleteEmployee implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Employee ID (-1 to show all employees):");
		int ID=scanner.nextInt();
		while(ID<0) {
			new ShowAllEmployees().oper(database, scanner);
			System.out.println("Enter Employee ID (-1 to show all employees):");
			ID=scanner.nextInt();
		}
		String delete="DELETE FROM employee WHERE ID="+ID+";";
		try {
			database.getStatement().execute(delete);
			System.out.println("Employee deleted successfully...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
