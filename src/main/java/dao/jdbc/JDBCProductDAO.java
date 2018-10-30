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

    private Connection getConnection(){
        try{
            return DriverManager.getConnection(CONNECTION_STRING,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Override
    public void add(Product product) {
        String query = "INSERT INTO " + PRODUCTS_TABLE + " (NAME, PRICE, IMAGE_PATH, DATE) VALUES (\'" +
               product.getName() + "\', \'" + product.getPrice() + "\', \'" + product.getImage() + "\', \'" + product.getDate() +"\')" ;
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + PRODUCTS_TABLE;
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            ProductRowMapper productRowMapper = new ProductRowMapper();
            while (resultSet.next()){
                products.add(productRowMapper.getProduct(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String query = "SELECT * FROM " + PRODUCTS_TABLE + " WHERE ID = " + id;
        Product productById = new Product();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            ProductRowMapper productRowMapper = new ProductRowMapper();
            if(resultSet.next()){
            productById = productRowMapper.getProduct(resultSet);}
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productById;
    }
}
