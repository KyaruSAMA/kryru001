package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.ICategoryDao;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends JDBCTemplate implements ICategoryDao {

    @Override
    public List<Category> queryAllCategories() {
        final List<Category> list = new ArrayList<>();
        String sql = "SELECT id, name FROM t_category";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Category category = new Category(
                            rs.getLong(1), rs.getString(2));
                    list.add(category);
                }
            }
        });
        return list;
    }

}
