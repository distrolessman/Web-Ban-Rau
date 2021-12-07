package nongsan.webmvc.service;

import nongsan.webmvc.model.Product;

import java.util.List;

public interface IProductService {
    Boolean createProduct(String productCategory, String productName, String productPrice,
                       String productStatus, String productDescription, String productContent,
                       String productDiscount, String productImage, String productCreated);

    Boolean updateProduct(String id, String productCategory, String productName, String productPrice,
                          String productStatus, String productDescription, String productContent,
                          String productDiscount, String productImage, String productCreated);

    Boolean deleteProduct(String id);

    List<Product> findAllProduct();

    Product findProductById(String id);

    List<Product> searchByName(String productName);

    List<Product> findProductByCategoryId(Integer idCate);
}

