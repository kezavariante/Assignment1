package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.CourseModel;

public class CourseDao {
    private Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }

    // Insert a course
    public void insertCourse(CourseModel course) throws SQLException {
        String query = "INSERT INTO Course (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.executeUpdate();
        }
    }

    // Retrieve all courses
    public List<CourseModel> getAllCourses() throws SQLException {
        List<CourseModel> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CourseModel course = new CourseModel();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courses.add(course);
            }
        }
        return courses;
    }

    // Retrieve a course by ID
    public CourseModel getCourseById(int id) throws SQLException {
        String query = "SELECT * FROM Course WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CourseModel course = new CourseModel();
                    course.setId(rs.getInt("id"));
                    course.setName(rs.getString("name"));
                    return course;
                }
            }
        }
        return null;
    }

    // Update a course
    public void updateCourse(CourseModel course) throws SQLException {
        String query = "UPDATE Course SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a course
    public void deleteCourse(int courseId) throws SQLException {
        String query = "DELETE FROM Course WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
        }
    }
}
