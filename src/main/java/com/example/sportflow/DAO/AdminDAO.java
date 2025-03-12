package com.example.sportflow.DAO;

import com.example.sportflow.Model.Admin;
import com.example.sportflow.connection.Connectiondb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

                Admin admin = new Admin(id, username, password);
                admins.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

}
