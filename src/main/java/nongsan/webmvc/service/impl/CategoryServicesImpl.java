package nongsan.webmvc.service.impl;


import java.util.List;

import nongsan.webmvc.dao.ICategoryDAO;
import nongsan.webmvc.dao.impl.CategoryDAOImpl;
import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.service.ICategoryService;

import javax.inject.Inject;

public class CategoryServicesImpl implements ICategoryService {
    @Inject
    ICategoryDAO categoryDAO;

    @Override
    public Boolean createCategory(String name, String parentId) {
        try {
            Catalog parentCatalog = categoryDAO.findCategoryById(Integer.parseInt(parentId));
            Catalog newCatalog = new Catalog();
            newCatalog.setName(name);
            newCatalog.setParent(parentCatalog);
            return categoryDAO.createCategory(newCatalog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateCategory(String id, String name, String parentId) {
        try {
            Catalog parentCatalog = categoryDAO.findCategoryById(Integer.parseInt(parentId));
            Catalog catalog = categoryDAO.findCategoryById(Integer.parseInt(id));
            catalog.setName(name);
            catalog.setParent(parentCatalog);
            return categoryDAO.updateCategory(catalog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteCategory(String id) {
        try {
            return categoryDAO.deleteCategory(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Catalog findCategoryById(String id) {
        try {
            return categoryDAO.findCategoryById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Catalog> findAllCategory() {
        return categoryDAO.findAllCategory();
    }


    public List<Catalog> findCategoryByProduct(Integer id) {
        return categoryDAO.findCategoryByProduct(id);
    }
}