package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class UpdateClass implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		System.out.println("ENter Class ID (-1 to show all classes): ");
		int ID=scanner.nextInt();
		while(ID<0) {
			new ReadClasses().oper(database, scanner);
			System.out.println("ENter Class ID (-1 to show all classes): ");
			ID=scanner.nextInt();
		}
		Model.Class c=new Model.Class(ID,database);
		scanner.nextLine();
		System.out.println("Enter Name (-1 to keep "+c.getName()+"): ");
		String name=scanner.nextLine();
		if(!name.equals("-1"))c.setName(name);
		
		c.update(database);
	}

}
