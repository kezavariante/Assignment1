package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.CourseDao;
import Model.CourseModel;

public class CourseTest {

    public static void main(String[] args) {
        // Establish database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:5432/Registration", "root", "")) {
            CourseDao courseDao = new CourseDao(connection);
            try (Scanner scanner = new Scanner(System.in)) {
				int choice;
				do {
				    System.out.println("\n1. Insert Course");
				    System.out.println("2. Get All Courses");
				    System.out.println("3. Get Course by ID");
				    System.out.println("4. Update Course");
				    System.out.println("5. Delete Course");
				    System.out.println("6. Exit");
				    System.out.print("\nEnter your choice: ");
				    choice = scanner.nextInt();
				    scanner.nextLine(); // Consume newline character

				    switch (choice) {
				        case 1:
				            System.out.print("Enter course name: ");
				            String name = scanner.nextLine();
				            CourseModel newCourse = new CourseModel(name);
				            courseDao.insertCourse(newCourse);
				            System.out.println("Course inserted successfully!");
				            break;
				        case 2:
				            List<CourseModel> allCourses = courseDao.getAllCourses();
				            System.out.println("All Courses:");
				            for (CourseModel course : allCourses) {
				                System.out.println("ID: " + course.getId() + ", Name: " + course.getName());
				            }
				            break;
				        case 3:
				            System.out.print("Enter course ID: ");
				            int id = scanner.nextInt();
				            CourseModel courseById = courseDao.getCourseById(id);
				            if (courseById != null) {
				                System.out.println("Course found:");
				                System.out.println("ID: " + courseById.getId() + ", Name: " + courseById.getName());
				            } else {
				                System.out.println("Course not found!");
				            }
				            break;
				        case 4:
				            System.out.print("Enter course ID to update: ");
				            int updateId = scanner.nextInt();
				            scanner.nextLine(); // Consume newline character
				            System.out.print("Enter new name: ");
				            String updatedName = scanner.nextLine();
				            CourseModel courseToUpdate = new CourseModel(updateId, updatedName);
				            courseDao.updateCourse(courseToUpdate);
				            System.out.println("Course updated successfully!");
				            break;
				        case 5:
				            System.out.print("Enter course ID to delete: ");
				            int deleteId = scanner.nextInt();
				            courseDao.deleteCourse(deleteId);
				            System.out.println("Course deleted successfully!");
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
