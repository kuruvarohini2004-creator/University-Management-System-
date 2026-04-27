package Controller;

import java.util.Scanner;

import Model.Database;

public class Login {
	public static void main(String[] args) {
		new CreateStudent().oper(new Database(), new Scanner(System.in));
	}

}
