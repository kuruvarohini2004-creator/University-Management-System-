package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class CreateClass implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Class Name: ");
		String name=scanner.next();
		
		ArrayList<Model.Class> classes=new ReadClasses().getClasses(database);
		int ID=0;
		if(classes.size()!=0) {
			ID=classes.get(classes.size()-1).getID()+1;
		}
		Model.Class c=new Model.Class();
		c.setID(ID);
		c.setName(name);
		c.create(database);
	}

}
