package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IBrandDao;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDaoImpl extends JDBCTemplate implements IBrandDao {

    @Override
    public List<Brand> queryAllBrand() {
        final List<Brand> list = new ArrayList<>();
        String sql = "SELECT id,name FROM t_brand";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Brand brand = new Brand(rs.getLong(1), rs.getString(2));
                    list.add(brand);
                }
            }
        });
        return list;
    }

    @Override
    public int addBrand(Brand brand) {
        String sql = "insert into t_brand (name)"
                +"values (?)";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,brand.getName());
            }
        });
    }

    @Override
    public Brand queryBrandByBrandName(String brandName) {
        Brand brand=new Brand();
        String sql = "SELECT id,name FROM t_brand where name=?";
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,brandName);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                   brand.setId(rs.getLong(1));
                   brand.setName(rs.getString(2));
                }

            }
        });
        return brand;
    }

}
