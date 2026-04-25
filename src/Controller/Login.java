package Controller;

import java.util.Scanner;

import Model.Database;

public class Login {
	public static void main(String[] args) {
		new UpdateEmployee().oper(new Database(), new Scanner(System.in));
	}

}
