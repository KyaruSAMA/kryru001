package com.hwua.erhai.dao;

import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> queryAllCategories();
    int addCategory(Category category);
    Category queryCategorybyCategoryName(String CategoryName);
}
