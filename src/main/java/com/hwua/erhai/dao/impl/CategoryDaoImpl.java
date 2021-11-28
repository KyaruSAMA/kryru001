package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.ICategoryDao;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;

import java.sql.PreparedStatement;
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

    @Override
    public int addCategory(Category category) {
        String sql = "insert into t_category (name)"
                +"values (?)";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,category.getName());
            }
        });
    }

    @Override
    public Category queryCategorybyCategoryName(String CategoryName) {
        Category category=new Category();
        String sql = "SELECT id,name FROM t_category where name=?";
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,CategoryName);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    category.setId(rs.getLong(1));
                    category.setName(rs.getString(2));
                }

            }
        });
        return category;
    }

}
