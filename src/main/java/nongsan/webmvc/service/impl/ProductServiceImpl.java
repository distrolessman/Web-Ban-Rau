package nongsan.webmvc.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


import nongsan.webmvc.dao.ICategoryDAO;
import nongsan.webmvc.dao.IProductDAO;
import nongsan.webmvc.dao.impl.CategoryDAOImpl;
import nongsan.webmvc.dao.impl.ProductDAOImpl;
import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IProductService;

import javax.inject.Inject;

public class ProductServiceImpl implements IProductService {
    @Inject
    IProductDAO productDAO;
    @Inject
    ICategoryDAO categoryDAO;
    @Override
    public Boolean createProduct(String productCategory, String productName, String productPrice,
                                 String productStatus, String productDescription, String productContent,
                                 String productDiscount, String productImage, String productCreated) {
        try {
            Product product = new Product();
            Catalog catalog = categoryDAO.findCategoryById(Integer.parseInt(productCategory));
            product.setCatalog(catalog);
            product.setName(productName);
            product.setPrice(Integer.parseInt(productPrice));
            product.setStatus(Integer.parseInt(productStatus));
            product.setDescription(productDescription);
            product.setContent(productContent);
            product.setDiscount(Integer.parseInt(productDiscount));
            product.setImage_link(productImage);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            product.setCreated(formatter.parse(productCreated));
            return productDAO.createProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateProduct(String id, String productCategory, String productName, String productPrice,
                                 String productStatus, String productDescription, String productContent,
                                 String productDiscount, String productImage, String productCreated) {
        try {
            Product newProduct = productDAO.findProductById(Integer.parseInt(id));
            Catalog catalog = categoryDAO.findCategoryById(Integer.parseInt(productCategory));
            newProduct.setCatalog(catalog);
            newProduct.setName(productName);
            newProduct.setPrice(Integer.parseInt(productPrice));
            newProduct.setStatus(Integer.parseInt(productStatus));
            newProduct.setDescription(productDescription);
            newProduct.setContent(productContent);
            newProduct.setDiscount(Integer.parseInt(productDiscount));
            newProduct.setImage_link(productImage);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            newProduct.setCreated(formatter.parse(productCreated));
            return productDAO.updateProduct(newProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(String id) {
        try {
            return productDAO.deleteProduct(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public Product findProductById(String id) {
        try {
            return productDAO.findProductById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> searchByName(String productName) {
        return productDAO.findProductByName(productName);
    }

    @Override
    public List<Product> findProductByCategoryId(Integer id) {
        return productDAO.getProductByCategoryId(id);
    }
}