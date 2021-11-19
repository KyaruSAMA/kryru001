package com.hwua.erhai.dao;

import com.hwua.erhai.entity.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> queryAllCategories();
}
