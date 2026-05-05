package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Student;

public class Login {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		Database database=new Database();
		System.out.println("Welcome to University Management System");
		System.out.println("1. Staff");
		System.out.println("2. Student");
		int selected = scanner.nextInt();
		
		System.out.println("Enter Email: ");
		String email=scanner.next();
		System.out.println("Enter Password: ");
		String password = scanner.next();
		
		boolean loggedIn=false;
		
		if(selected==1) {
			String select="select ID,Email,Password "+"from employee where Email='"+email+"';";
			try {
				ResultSet rs= database.getStatement().executeQuery(select);
				while(rs.next()) {
					if(password.equals(rs.getString("Password"))) {
						Employee e=new Employee(rs.getInt("ID"),database);
						loggedIn=true;
						e.showList(database, scanner);
						break;
					}
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			String select="select ID,Email,Password "+"from student where Email='"+email+"' ;";
			try {
				ResultSet rs= database.getStatement().executeQuery(select);
				while(rs.next()) {
					if(password.equals(rs.getString("Password"))) {
						Student s=new Student(rs.getInt("ID"),database);
						loggedIn=true;
						s.showList(database,scanner);
						break;
					}
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(!loggedIn) {
			System.out.println("Wrong email or Password.\n try again later");
		}
	}

}
