package com.hwua.erhai.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模板类
 *
 * @author Alain
 */
public class JDBCTemplate {
    /**
     * 增、删、改
     *
     * @param sql
     * @param setter
     * @return 返回的是增、删、改的记录行数
     */
    public int update(String sql, PreparedStatementSetter setter) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            // 1.注册数据库驱动 2.获取连接
            conn = ConnectionFactory.getConnection();
            // 3.获取可以执行sql语句的Statement
            pstmt = conn.prepareStatement(sql);
            // 4.执行sql语句 (需要先设置占位符(参数)的值)
            // 设置参数:就是将实际查询参数填写到各个占位符“?”处
            if (setter != null) {
                setter.setValues(pstmt);
            }
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            // 5.释放资源
            DBUtil.close(pstmt, conn);
        }
        return rows;
    }

    // 查
    public void query(String sql, PreparedStatementSetter setter, ResultSetHandler handler) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1.注册数据库驱动 2.获取连接
            conn = ConnectionFactory.getConnection();
            // 3.获取可以执行sql语句的Statement
            pstmt = conn.prepareStatement(sql);
            // 4.执行sql语句 (需要先设置占位符(参数)的值)
            // 设置参数:就是将实际查询参数填写到各个占位符“?”处
            if (setter != null) {
                setter.setValues(pstmt);
            }
            rs = pstmt.executeQuery();
            // 5.处理结果集==============
            if (handler != null) {
                handler.handleRs(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
    }

    // 需要事务控制的增删改操作，Connection由外面统一提供并传入进来
    public int update(Connection conn, String sql, PreparedStatementSetter setter) {
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            // 1.获取可以执行sql语句的Statement
            pstmt = conn.prepareStatement(sql);
            // 2.执行sql语句 (需要先设置占位符(参数)的值)
            // 设置参数:就是将实际查询参数填写到各个占位符“?”处
            if (setter != null) {
                setter.setValues(pstmt);
            }
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            // 3.释放资源
            DBUtil.close(pstmt);
        }
        return rows;
    }

    // 查
    public void query(Connection conn, String sql, PreparedStatementSetter setter, ResultSetHandler handler) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1.获取可以执行sql语句的Statement
            pstmt = conn.prepareStatement(sql);
            // 2.执行sql语句 (需要先设置占位符(参数)的值)
            // 设置参数:就是将实际查询参数填写到各个占位符“?”处
            if (setter != null) {
                setter.setValues(pstmt);
            }
            rs = pstmt.executeQuery();
            // 3.处理结果集==============
            if (handler != null) {
                handler.handleRs(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            // 4.释放资源
            DBUtil.close(rs, pstmt);
        }
    }
}
