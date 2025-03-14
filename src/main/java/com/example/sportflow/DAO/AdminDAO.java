package com.example.sportflow.DAO;

import com.example.sportflow.Model.Admin;
import com.example.sportflow.Model.Coach;
import com.example.sportflow.Model.Member;
import com.example.sportflow.connection.Connectiondb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<Admin> list() {
        String sql = "SELECT * FROM user";
        List<Admin> admins = new ArrayList<>();

        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("admin_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String birthDate = resultSet.getString("birth_date");

                Admin admin = new Admin(id, username, password, role, email, birthDate);
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

    public void addUser(Admin admin) {
        try (Connection connection = Connectiondb.connection()) {
            connection.setAutoCommit(false); // Start transaction

            String insertUserQuery = "INSERT INTO user (username, birth_date, email, password, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement userStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
                userStatement.setString(1, admin.getUsername());
                userStatement.setString(2, admin.getBirthDate());
                userStatement.setString(3, admin.getEmail());
                userStatement.setString(4, admin.getPassword());
                userStatement.setString(5, admin.getRole());

                int affectedRows = userStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = userStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int adminID = generatedKeys.getInt(1);

                        if ("member".equalsIgnoreCase(admin.getRole())) {
                            String insertMemberQuery = "INSERT INTO member (member_id, sport) VALUES (?, ?)";
                            try (PreparedStatement memberStatement = connection.prepareStatement(insertMemberQuery)) {
                                memberStatement.setInt(1, adminID);
                                memberStatement.setString(2, admin.getSport());
                                memberStatement.executeUpdate();
                            }
                        } else if ("coach".equalsIgnoreCase(admin.getRole())) {
                            String insertCoachQuery = "INSERT INTO coach (coach_id, speciality) VALUES (?, ?)";
                            try (PreparedStatement coachStatement = connection.prepareStatement(insertCoachQuery)) {
                                coachStatement.setInt(1, adminID);
                                coachStatement.setString(2, admin.getSpeciality());
                                coachStatement.executeUpdate();
                            }
                        } else {
                            throw new SQLException("Invalid role: " + admin.getRole());
                        }
                    } else {
                        throw new SQLException("Failed to retrieve admin_id.");
                    }
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback transaction in case of error
            try (Connection connection = Connectiondb.connection()) {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error adding user: " + e.getMessage(), e);
        }
    }

    public Admin getAdminById(int id) {
        String sql = "SELECT * FROM user WHERE admin_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admin(
                            resultSet.getInt("admin_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("email"),
                            resultSet.getString("birth_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getAdminCountByRole(String role) {
        String sql = "SELECT COUNT(*) FROM user WHERE role = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getSessionCount() {
        String sql = "SELECT COUNT(*) FROM session";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateCoach(Coach coach) {
        String sql = "UPDATE coach SET speciality = ? WHERE coach_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coach.getSpeciality());
            preparedStatement.setInt(2, coach.getCoachId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCoach(int id) {
        String sql = "DELETE FROM coach WHERE coach_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCoach(Coach coach) {
        String sql = "INSERT INTO coach (coach_id, speciality) VALUES (?, ?)";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, coach.getCoachId());
            preparedStatement.setString(2, coach.getSpeciality());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Coach getCoachById(int id) {
        String sql = "SELECT * FROM coach WHERE coach_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Coach(
                            resultSet.getInt("coach_id"),
                            resultSet.getString("speciality")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Coach> getAllCoaches() {
        List<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM coach";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                coaches.add(new Coach(
                        resultSet.getInt("coach_id"),
                        resultSet.getString("speciality")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coaches;
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMember(Member member) {
        String sql = "UPDATE member SET sport = ? WHERE member_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, member.getSport());
            preparedStatement.setInt(2, member.getMemberId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getMemberById(int id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Member(
                            resultSet.getInt("member_id"),
                            resultSet.getString("sport")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMember(Member member) {
        String sql = "INSERT INTO member (member_id, sport) VALUES (?, ?)";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, member.getMemberId());
            preparedStatement.setString(2, member.getSport());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try (Connection connection = Connectiondb.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                members.add(new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("sport")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}