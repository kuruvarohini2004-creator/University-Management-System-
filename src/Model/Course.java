package Model;

import java.util.ArrayList;

public class Course {
	private int ID;
	private String name;
	private Class c;
	private String description;
	private int limit;
	private ArrayList<Student> students;
	private Employee prof;
	private Department dept;
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
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
	public Class getCurrentClass() {
		return c;
	}
	public void setClass(Class c) {
		this.c = c;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	public Employee getProf() {
		return prof;
	}
	public void setProf(Employee prof) {
		this.prof = prof;
	}
	
	 public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	 public void print() {
		 System.out.println("ID: \t\t"+getID());
		 System.out.println("Name: \t\t"+getName());
		 System.out.println("Class: \t\t"+getCurrentClass().getName());
		 System.out.println("Description: \t"+getDescription());
		 System.out.println("Limit: \t\t"+getLimit());
		 System.out.println("Prof: \t\tDr."+getProf().getFirstName()+" "+getProf().getLastName());
		 System.out.println("Department: \t"+getDept().getName());
		 System.out.println("-----------------------------------------------------------------------\n");
	 }
	 public void create(Database database) {
		 String insert=" ";
		 String create=" ";
		 
	 }

}
