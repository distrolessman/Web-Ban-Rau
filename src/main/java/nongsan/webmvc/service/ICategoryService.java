package nongsan.webmvc.service;

import nongsan.webmvc.model.Catalog;

import java.util.List;

public interface ICategoryService {
    Boolean createCategory(String name, String parentId);

    Boolean updateCategory(String id, String name, String parentId);

    Boolean deleteCategory(String id);

    Catalog findCategoryById(String id);

    List<Catalog> findAllCategory();

    List<Catalog> findCategoryByProduct(Integer id);
}

