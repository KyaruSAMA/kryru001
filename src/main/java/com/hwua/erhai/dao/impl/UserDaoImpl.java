package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IUserDao;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
         User user = new User();
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
                while (rs.next()) {
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
    public int addUser(final User user) {
        // ?????????id?????????????????????????????????????????????id?????????????????????null?????????
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
    public int updateUser(User user) {
        String sql= "UPDATE t_user SET username=?,password=?,sex=?,id_number=?,tel=?,addr=?,type=? WHERE id = ?";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUserName());
                pstmt.setString(2,user.getPassword());
                pstmt.setInt(3,user.getSex());
                pstmt.setString(4, user.getIdNumber());
                pstmt.setString(5,user.getTel());
                pstmt.setString(6,user.getAddr());
                pstmt.setInt(7,user.getType());
                pstmt.setLong(8,user.getId());
            }
        });
    }

    @Override
    public int deleteUser(Long userId) {
        String sql = "DELETE FROM t_user where id=?";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,userId);
            }
        });
    }

    @Override
    public List<User> queryUser(List<QueryCondition> conditions) {
        final List<User> userList = new ArrayList<>();
        StringBuilder otherCondetions = new StringBuilder();

        for (QueryCondition condition:conditions){
            if ("userName".equals(condition.getField())){
                otherCondetions.append(String.format(" WHERE username = '%s'",condition.getValue()));
            }else if ("userId".equals(condition.getField())){
                otherCondetions.append(String.format(" WHERE id = '%s'",condition.getValue()));
            }else if ("Type".equals(condition.getField())){
                otherCondetions.append(String.format(" WHERE type = '%s'",condition.getValue()));
            }
        }

        String sql = "SELECT id,username,password,"
                + "sex,id_number,tel,addr,type "
                + "FROM t_user  "+otherCondetions;
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    User user=new User(
                           rs.getLong(1),
                           rs.getString(2),
                           rs.getString(3),
                           rs.getInt(4),
                           rs.getString(5),
                           rs.getString(6),
                           rs.getString(7),
                           rs.getInt(8));
                    userList.add(user);
                }
            }
        });
        return userList;
    }

}
