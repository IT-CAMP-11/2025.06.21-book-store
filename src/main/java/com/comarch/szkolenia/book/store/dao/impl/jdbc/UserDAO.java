package com.comarch.szkolenia.book.store.dao.impl.jdbc;

import com.comarch.szkolenia.book.store.dao.IUserDAO;
import com.comarch.szkolenia.book.store.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserDAO implements IUserDAO {

    private final Connection connection;

    @Override
    public void persist(User user) {
        try {
            String sql = "INSERT INTO tuser (login, password, name, surname, age, role) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setInt(5, user.getAge());
            ps.setString(6, user.getRole().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Problem z zapisem uzytkownika: " + user);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (Exception e) {
            System.out.println("Problem z pobraniem uzytkownika o id: " + id);
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (Exception e) {
        System.out.println("Problem z pobraniem uzytkownika o loginie: " + login);
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (Exception e) {
            System.out.println("Problem z pobraniem wszystkich uzytkownikow !!");
            e.printStackTrace();
        }

        return result;
    }
}
