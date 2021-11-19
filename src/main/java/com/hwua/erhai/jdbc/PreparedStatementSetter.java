package com.hwua.erhai.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    //设置占位符的值
    void setValues(PreparedStatement pstmt) throws SQLException;
}
