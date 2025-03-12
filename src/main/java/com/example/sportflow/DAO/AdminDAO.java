package com.example.sportflow.DAO;

import com.example.sportflow.Model.Admin;
import com.example.sportflow.connection.Connectiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<Admin> list() {
        String sql = "SELECT * FROM sportflow";
        List<Admin> admins = new ArrayList<>();

        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String birth_date = resultSet.getString("birth_date");


                Admin admin = new Admin(id, username, password, role, email,birth_date);
                admins.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    public void createUserTable() {
        try (Connection connection = Connectiondb.connection();
             Statement statement = connection.createStatement()) {

            String sqlQuery1 = "CREATE TABLE IF NOT EXISTS user (" +
                    "admin_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(100) NOT NULL, " +
                    "birth_date DATE NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "role VARCHAR(15) NOT NULL" +
                    ")";

            String sqlQuery2 = "CREATE TABLE IF NOT EXISTS member (" +
                    "member_id INT PRIMARY KEY, " +
                    "sport VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT member_user FOREIGN KEY (member_id) REFERENCES user(admin_id) ON DELETE CASCADE" +
                    ")";

            String sqlQuery3 = "CREATE TABLE IF NOT EXISTS coach (" +
                    "coach_id INT PRIMARY KEY, " +
                    "speciality VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT entraineur_user FOREIGN KEY (coach_id) REFERENCES user(admin_id) ON DELETE CASCADE" +
                    ")";

            statement.executeUpdate(sqlQuery1);
            statement.executeUpdate(sqlQuery2);
            statement.executeUpdate(sqlQuery3);
            System.out.println("Tables 'user', 'member', and 'coach' created successfully!");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public long getAdminCountByRole(Class<? extends Admin> role) {

        return 0;
    }

    public long getSessionCount() {

        return 0;
    }
}