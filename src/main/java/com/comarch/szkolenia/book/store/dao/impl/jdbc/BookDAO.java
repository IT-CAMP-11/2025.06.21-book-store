package com.comarch.szkolenia.book.store.dao.impl.jdbc;

import com.comarch.szkolenia.book.store.dao.IBookDAO;
import com.comarch.szkolenia.book.store.model.Book;
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
public class BookDAO implements IBookDAO {
    private final Connection connection;

    @Override
    public Optional<Book> getById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return Optional.of(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem ksiazki o id: " + id);
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";
            PreparedStatement ps  = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem wszystkich ksiazek !!");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void persist(Book book) {
        try {
            String sql = "INSERT INTO tbook (title, author, isbn, price, quantity) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, book.getQuantity());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            book.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            System.out.println("Problem z zapisem ksiazki: " + book);
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        try {
            String sql = "SELECT * FROM tbook WHERE isbn = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, isbn);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return Optional.of(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z pobraniem ksiazki o isbn: " + isbn);
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String searchTerm) {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement ps  = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Problem z wyszukiwaniem ksiazek po tytule lub autorze: " + searchTerm);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(Book book) {
        try {
            String sql = "UPDATE tbook SET title = ?, author = ?, isbn = ?, price = ?, quantity = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6, book.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem z aktualizacja ksiazki: " + book);
            e.printStackTrace();
        }
    }
}
