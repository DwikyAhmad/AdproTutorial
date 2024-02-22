package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository extends DataRepository<Product>{

    public Product findProduct(int index) {
        return getListData().get(index);
    }

    public Product editProduct(int index, Product product) {
        return getListData().set(index, product);
    }

    public Product deleteProduct(int index) {
        return getListData().remove(index);
    }
}
