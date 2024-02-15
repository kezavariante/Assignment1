package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.StudentDao;
import Model.StudentModel;

public class StudentTest {

    public static void main(String[] args) {
        // Establish database connection
    	try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:5432/Registration", "root", "")) {
            StudentDao studentDao = new StudentDao(connection);
            try (Scanner scanner = new Scanner(System.in)) {
				int choice;
				do {
				    System.out.println("\n1. Insert Student");
				    System.out.println("2. Get All Students");
				    System.out.println("3. Get Student by ID");
				    System.out.println("4. Update Student");
				    System.out.println("5. Delete Student");
				    System.out.println("6. Exit");
				    System.out.print("\nEnter your choice: ");
				    choice = scanner.nextInt();
				    scanner.nextLine(); // Consume newline character

				    switch (choice) {
				        case 1:
				            System.out.print("Enter student name: ");
				            String name = scanner.nextLine();
				            StudentModel newStudent = new StudentModel(name);
				            studentDao.insertStudent(newStudent);
				            System.out.println("Student inserted successfully!");
				            break;
				        case 2:
				            List<StudentModel> allStudents = studentDao.getAllStudents();
				            System.out.println("All Students:");
				            for (StudentModel student : allStudents) {
				                System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
				            }
				            break;
				        case 3:
				            System.out.print("Enter student ID: ");
				            int id = scanner.nextInt();
				            StudentModel studentById = studentDao.getStudentById(id);
				            if (studentById != null) {
				                System.out.println("Student found:");
				                System.out.println("ID: " + studentById.getId() + ", Name: " + studentById.getName());
				            } else {
				                System.out.println("Student not found!");
				            }
				            break;
				        case 4:
				            System.out.print("Enter student ID to update: ");
				            int updateId = scanner.nextInt();
				            scanner.nextLine(); // Consume newline character
				            System.out.print("Enter new name: ");
				            String updatedName = scanner.nextLine();
				            StudentModel studentToUpdate = new StudentModel(updateId, updatedName);
				            studentDao.updateStudent(studentToUpdate);
				            System.out.println("Student updated successfully!");
				            break;
				        case 5:
				            System.out.print("Enter student ID to delete: ");
				            int deleteId = scanner.nextInt();
				            studentDao.deleteStudent(deleteId);
				            System.out.println("Student deleted successfully!");
				            break;
				        case 6:
				            System.out.println("Exiting...");
				            break;
				        default:
				            System.out.println("Invalid choice! Please enter a number between 1 and 6.");
				    }
				} while (choice != 6);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
