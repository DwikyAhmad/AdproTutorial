package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> findAll();

    Product findProduct(int index);

    Product editProduct(int index, Product product);

    Product deleteProduct(int index);
}
