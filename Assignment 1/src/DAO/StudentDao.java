package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.StudentModel;

public class StudentDao {
    private Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    // Insert a student
    public void insertStudent(StudentModel student) throws SQLException {
        String query = "INSERT INTO Student (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.executeUpdate();
        }
    }

    // Retrieve all students
    public List<StudentModel> getAllStudents() throws SQLException {
        List<StudentModel> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                students.add(student);
            }
        }
        return students;
    }

    // Retrieve a student by ID
    public StudentModel getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM Student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    StudentModel student = new StudentModel();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    return student;
                }
            }
        }
        return null;
    }

    // Update a student
    public void updateStudent(StudentModel student) throws SQLException {
        String query = "UPDATE Student SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a student
    public void deleteStudent(int studentId) throws SQLException {
        String query = "DELETE FROM Student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }
}
