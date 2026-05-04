package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public Course(int ID,Database database) {
		setID(ID);
		String select1="select * from courses where ID="+ID+";";
		String select2="select * from courses "+ID+";";
		try {
			ResultSet rs1=database.getStatement().executeQuery(select1);
			rs1.next();
			setName(rs1.getString("Name"));
			int classID=rs1.getInt("Class");
			setDescription(rs1.getString("Description"));
			setLimit(rs1.getInt("Limit"));
			int profID=rs1.getInt("Prof");
			
			setClass(new Class(classID,database));
			setProf(new Employee(profID,database));
			
			ResultSet rs2=database.getStatement().executeQuery(select2);
			ArrayList<Integer> studentIDs=new ArrayList<>();
			ArrayList<Student> student =new ArrayList<>();

			while(rs2.next()) {
				studentIDs.add(rs2.getInt("Student"));
			}
			for(Integer i: studentIDs) {
				students.add(new Student(i,database));
				
			}
			setStudents(student);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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
		 System.out.println("-----------------------------------------------------------------------\n");
	 }
	 public void create(Database database) {
		 String insert="INSERT INTO courses(ID,Name,Class,Description,`Limit`,Prof) VALUES "
		 		+ "('"+getID()+"','"+getName()+"','"+getCurrentClass().getID()+
		 		"','"+getDescription()+"','"+getLimit()+"','"+getProf().getID()+"');";
		 String create="CREATE TABLE `course  "+getID()+"` (student int);";
		 try {
				database.getStatement().execute(insert);
				database.getStatement().execute(create);

				System.out.println("Course Created Successfully...");
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		 
	 }
}
