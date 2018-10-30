package service;

import dao.ProductDAO;
import dao.jdbc.JDBCProductDAO;
import entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDAO productDAO = new JDBCProductDAO();
    @Override
    public Product getById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public void add(Product product) {
        productDAO.add(product);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }
}
