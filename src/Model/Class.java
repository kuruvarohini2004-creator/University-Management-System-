package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Class {
	private int ID;
	private String name;
	public Class() {
		super();
	}
	public Class(int ID,Database database) {
		this.ID=ID;
		String select ="SELECT * FROM classes WHERE ID="+ID+";";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			rs.next();
			setName(rs.getString("Name"));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Class(int ID) {
		this.ID=ID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void print() {
		System.out.println("ID: \t"+ID);
		System.out.println("Name: \t"+name);
		System.out.println("---------------------");
	}
	public void create(Database database) {
		String insert="INSERT INTO classes (ID,Name) VALUES "
				+"('"+ID+"','"+name+"');";
		try {
			database.getStatement().execute(insert);
			System.out.println("Class Created Successfully...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update(Database database) {
		String update="UPDATE classes SET Name='"+getName()+"' "
				+"WHERE ID ="+getID()+";";
		try {
			database.getStatement().execute(update);
			System.out.println("Class Created Successfully...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void delete(Database database) {
		String delete="DELETE FROM classes WHERE ID="+getID()+";";
		try {
			database.getStatement().execute(delete);
			System.out.println("Class Deleted Successfully...");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
