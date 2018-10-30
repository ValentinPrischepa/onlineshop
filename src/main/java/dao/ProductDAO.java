package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {

    void add(Product product);

    List<Product> getAll();

    Product getById(int id);


}
