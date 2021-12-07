package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.model.Product;

public interface ICategoryDAO extends IBaseDAO<Catalog> {
    Boolean createCategory(Catalog category);

    Boolean updateCategory(Catalog category);

    Boolean deleteCategory(Integer id);

    Catalog findCategoryById(Integer id);

    List<Catalog> findAllCategory();

    List<Catalog> findCategoryByProduct(Integer id);
}
