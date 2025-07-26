package com.comarch.szkolenia.book.store.dao.impl.jdbc;

import com.comarch.szkolenia.book.store.dao.IOrderDAO;
import com.comarch.szkolenia.book.store.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class OrderDAO implements IOrderDAO {
    private final Connection connection;

    @Override
    public void persist(Order order) {
        try {
            String sql = "INSERT INTO torder (city, street, no, post_code, phone_number, user_id, date, price) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getCity());
            ps.setString(2, order.getStreet());
            ps.setString(3, order.getNo());
            ps.setString(4, order.getPostCode());
            ps.setString(5, order.getPhoneNumber());
            ps.setInt(6, order.getUserId());
            ps.setDate(7, new java.sql.Date(order.getDate().getTime()));
            ps.setDouble(8, order.getPrice());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Problem z zapisem zamowienia: " + order);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> getById(int id) {
        try {
            String sql = "SELECT * FROM torder WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new Order(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("no"),
                        rs.getString("post_code"),
                        rs.getString("phone_number"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem zamowienia o id: " + id);
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(new Order(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("no"),
                        rs.getString("post_code"),
                        rs.getString("phone_number"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem wszystkich zamowien !!");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Order> getByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(new Order(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("no"),
                        rs.getString("post_code"),
                        rs.getString("phone_number"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem zamowien dla uzytkownika o id: " + userId);
            e.printStackTrace();
        }

        return result;
    }
}
