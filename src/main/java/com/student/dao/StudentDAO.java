package com.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    // INSERT
    public void addStudent(String name, int age, String email, String course) {

        String sql = "INSERT INTO student (name, age, email, course) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, email);
            ps.setString(4, course);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ - View All Students
    public void viewAllStudents() {

        String sql = "SELECT * FROM student";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Age: " + rs.getInt("age") +
                    ", Email: " + rs.getString("email") +
                    ", Course: " + rs.getString("course")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SEARCH
    public void searchStudent(int id) {

        String sql = "SELECT * FROM student WHERE id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Age: " + rs.getInt("age") +
                    ", Email: " + rs.getString("email") +
                    ", Course: " + rs.getString("course")
                );

            } else {
                System.out.println("Student not found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateStudent(int id, String name, int age, String email, String course) {

        String sql = "UPDATE student SET name = ?, age = ?, email = ?, course = ? WHERE id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, email);
            ps.setString(4, course);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//DELETE - Delete Student
public void deleteStudent(int id) {

 String sql = "DELETE FROM student WHERE id = ?";

 try {

     Connection con = DBConnection.getConnection();

     PreparedStatement ps = con.prepareStatement(sql);

     ps.setInt(1, id);

     int rows = ps.executeUpdate();

     if (rows > 0) {
         System.out.println("Student deleted successfully!");
     } else {
         System.out.println("Student not found!");
     }

     ps.close();
     con.close();

 } catch (Exception e) {

     e.printStackTrace();

 }
}
}