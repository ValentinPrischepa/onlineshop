package service;

import entity.Product;

import java.util.List;

public interface ProductService {

    Product getById(int id);

    void add(Product product);

    List<Product> getAll();
}
