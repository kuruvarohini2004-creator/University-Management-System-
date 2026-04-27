package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class ShowAllDepartments implements Operation{

	@Override
	public void oper(Database database, Scanner scanner) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Department> getDepatments(Database database){
		ArrayList<Department> departments =new ArrayList<>();
	    String select="SELECT * FROM departments;";
	    try {
			ResultSet rs=database.getStatement().executeQuery(select);
			while(rs.next()) {
				Department d = new  Department();
				d.setID(rs.getInt(""));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return departments;
	}

}
