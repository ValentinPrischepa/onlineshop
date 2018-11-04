package dao.jdbc;

import dao.ProductDAO;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDAO implements ProductDAO {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/task_manager";
    private static final String USER = "val";
    private static final String PASSWORD = "password";
    private static final String PRODUCTS_TABLE = "products";

    private Connection connection;
    public static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    public JDBCProductDAO(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void add(Product product) {
        String query = "INSERT INTO " + PRODUCTS_TABLE + " (NAME, PRICE, IMAGE_PATH, DATE) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice());
            statement.setString(3, product.getImage());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + PRODUCTS_TABLE;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                products.add(PRODUCT_ROW_MAPPER.getProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String query = "SELECT * FROM " + PRODUCTS_TABLE + " WHERE ID = " + id;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            Product prodById = new Product();
            if (resultSet.next()) {
                prodById =  PRODUCT_ROW_MAPPER.getProduct(resultSet);
            }
            return  prodById;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM " + PRODUCTS_TABLE + " WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public void update(Product product) {
        String query = "UPDATE " + PRODUCTS_TABLE +
                " SET name = ?, price = ?, image_path = ? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,product.getName());
            statement.setString(2,product.getPrice());
            statement.setString(3,product.getImage());
            statement.setInt(4,product.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
