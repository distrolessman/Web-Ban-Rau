package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.Product;

public interface IProductDAO extends IBaseDAO<Product> {
    Boolean createProduct(Product product);

    Boolean updateProduct(Product product);

    Boolean deleteProduct(Integer id);

    Product findProductById(Integer id);

    List<Product> getProductByCategoryId(Integer id);

    List<Product> getAllProduct();

    List<Product> findProductByName(String productName);
}
