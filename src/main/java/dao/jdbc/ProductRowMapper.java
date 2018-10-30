package dao.jdbc;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getString("price"));
        product.setImage(resultSet.getString("image_path"));
        product.setDate(resultSet.getTimestamp("date"));

        return product;

    }
}
