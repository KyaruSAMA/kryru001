package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IBrandDao;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.ResultSetHandler;

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

}
