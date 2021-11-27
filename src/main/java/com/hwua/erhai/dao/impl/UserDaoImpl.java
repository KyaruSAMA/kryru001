package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IUserDao;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends JDBCTemplate implements IUserDao {

    @Override
    public User queryUser(final String userName, final String password) {
        final User user = new User();
        String sql = "SELECT id,username,password,"
                + "sex,id_number,tel,addr,type "
                + "FROM t_user WHERE username=? "
                + "AND password=? ";
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    user.setUserName(rs.getString(2));
                    user.setPassword(rs.getString(3));
                    user.setSex(rs.getInt(4));
                    user.setIdNumber(rs.getString(5));
                    user.setTel(rs.getString(6));
                    user.setAddr(rs.getString(7));
                    user.setType(rs.getInt(8));
                }
            }
        });
        return user;
    }

    @Override
    public User queryUser(final String userName) {
        final User user = new User();
        String sql = "SELECT id,username,password,"
                + "sex,id_number,tel,addr,type "
                + "FROM t_user WHERE username=?";
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userName);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    user.setUserName(rs.getString(2));
                    user.setPassword(rs.getString(3));
                    user.setId(rs.getInt(4));
                    user.setIdNumber(rs.getString(5));
                    user.setTel(rs.getString(6));
                    user.setAddr(rs.getString(7));
                    user.setType(rs.getInt(8));
                }
            }
        });
        return user;
    }

    @Override
    public int addUser(final User user) {
        // 可以将id设置为自增长的列，这样插入时，id就只需要设置为null即可。
        String sql = "INSERT INTO t_user(id, username, password, sex, id_number, tel, addr, type)"
                + " VALUES(null,?,?,?,?,?,?,?)";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserName());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3, user.getSex());
                pstmt.setString(4, user.getIdNumber());
                pstmt.setString(5, user.getTel());
                pstmt.setString(6, user.getAddr());
                pstmt.setInt(7, user.getType());
            }
        });
    }

}
