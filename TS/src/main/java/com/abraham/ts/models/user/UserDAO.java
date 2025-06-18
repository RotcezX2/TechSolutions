package com.abraham.ts.models.user;
import com.abraham.ts.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn = new DBConnection().getConnection();
    private PreparedStatement ps;
    private ResultSet rs;

    public void save(User u) {
        String query = "INSERT INTO users (name, username, password, role) VALUES (?, ?, ?, ?);";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }

        return users;
    }

    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?;";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void update(User u) {
        String query = "UPDATE users SET name = ?, username = ?, password = ?, role = ? WHERE id = ?;";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }


}


