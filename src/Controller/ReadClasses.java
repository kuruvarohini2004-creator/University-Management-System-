package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Class;
import Model.Database;
import Model.Operation;

public class ReadClasses implements Operation {

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		for(Class c: getClasses(database)) {
			c.print();
		}
	}
	
	public ArrayList<Class> getClasses(Database database){
		ArrayList<Model.Class> classes=new ArrayList<>();
		String select= "SELECT * FROM classes ;";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			while(rs.next()) {
				Class c=new Class();
				c.setID(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				classes.add(c);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return classes;
	}

}
