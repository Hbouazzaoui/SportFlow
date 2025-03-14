package com.example.sportflow.DAO;

import com.example.sportflow.Model.Login;
import com.example.sportflow.connection.Connectiondb;
import com.mysql.cj.BindValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LginDAO implements logindao {
    static Connection conn;
    static PreparedStatement ps ;

    public int insertLogin(Login c){
        int status = 0 ;
        try {


            conn = Connectiondb.connection();
            ps = conn.prepareStatement("insert into login values (?,?)");

            ResultSet resultSet = ps.executeQuery();

            ps.setString(1,c.getUserName(resultSet.getString(1)));
            ps.setString(2, c.getName(resultSet.getString(2)));
            ps.setString(3,c.getPassword(resultSet.getString(3)));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  status;

    }
    public  Login getLogin(String userid , String pass ) {
        Login c = new Login();
        try {
            conn = Connectiondb.connection();
            ps = conn.prepareStatement("select  * from login where userid=? and password=?");
            ps.setString(1,userid);
            ps.setString(2,pass);

            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()){
                c.getUserName(resultSet.getString(1));
                c.getPassword(resultSet.getString(2));
                c.getName(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }
}
