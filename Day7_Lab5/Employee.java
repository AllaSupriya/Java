package Day7_Lab5;

import java.util.Scanner;

public class Employee {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Salary");
		double n = sc.nextDouble();
		EmployeeException ne = new EmployeeException();
		try {
			ne.validateSalary(n);
		} catch (salaryException e) {
			System.out.println(e);
		}
	}

}
