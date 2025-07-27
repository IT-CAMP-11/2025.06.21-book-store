package com.comarch.szkolenia.book.store.dao.impl.jdbc;

import com.comarch.szkolenia.book.store.dao.IPositionDAO;
import com.comarch.szkolenia.book.store.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class PositionDAO implements IPositionDAO {
    private final Connection connection;

    @Override
    public List<Position> getByOrderId(int orderId) {
        ArrayList<Position> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tposition WHERE order_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(new Position(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getInt("order_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem pozycji dla zamowienia o id: " + orderId);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void persist(Position position) {
        try {
            String sql = "INSERT INTO tposition (book_id, quantity, order_id) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, position.getBookId());
            ps.setInt(2, position.getQuantity());
            ps.setInt(3, position.getOrderId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            position.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Problem z zapisem pozycji zamowienia: " + position);
            e.printStackTrace();
        }
    }
}
