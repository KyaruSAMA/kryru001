package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IRecordDao;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.jdbc.DBUtil;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;
import com.hwua.erhai.servlet.query.QueryCondition;
import com.hwua.erhai.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl extends JDBCTemplate implements IRecordDao {

    @Override
    public long addRecord(Connection conn, final long userId, final long carId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long id;
        String sql = "INSERT INTO t_record (id, user_id, car_id, start_date)"
                + " VALUES(null,?,?,?)";
        try {
            // 执行SQL语句
            pstmt = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, carId);
            pstmt.setString(3, Util.today());
            pstmt.executeUpdate();

            // 获取自增主键id
            rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            DBUtil.close(rs, pstmt);
        }

        // 返回的id是新增记录的主键
        return id;
    }

    @Override
    public int updateRecord(Connection conn, final long recordId, final String returnTime, final double payment) {
        PreparedStatement pstmt = null;
        long id;
        String sql = "update t_record set return_date= ?, payment=? where id=?";
        try {
            pstmt = conn.prepareStatement(
                    sql);
            pstmt.setString(1, returnTime);
            pstmt.setDouble(2, payment);
            pstmt.setLong(3, recordId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            DBUtil.close( pstmt);
        }
        return 1;
    }

//    public int updateRecord(Connection conn, final long recordId, final String returnTime, final double payment) {
//        PreparedStatement pstmt = null;
//
//        long id;
//        String sql = "update t_record set return_date=?,payment=? where id = ?";
//        try {
//            // 执行SQL语句
//            pstmt = conn.prepareStatement(
//                    sql);
//            pstmt.setLong(3, recordId);
//            pstmt.setString(1, returnTime);
//            pstmt.setDouble(2, payment);
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
//        } finally {
//            DBUtil.close( pstmt);
//        }
//
//        // 返回的id是新增记录的主键
//        return 1;
//    }

    @Override
    public List<Record> queryRecordsByUserId(final long userId) {
        final List<Record> list = new ArrayList<>();
        String sql = "SELECT re.id,car.id,car.model,re.payment,"
                + "car.t_comments,b.name,cay.name,re.start_date,re.return_date "
                + "FROM t_car car, t_brand b, t_category cay ,t_record re "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id AND car.id = re.car_id "
                + "and re.user_id =? ";
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1, userId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Record record = new Record(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9));

                    list.add(record);
                }

            }
        });
        return  list;
    }

    @Override
    public Record queryRecordById(Connection conn, final long id) {
        final Record record = new Record();
        String sql = "SELECT  record.id, car.model,car.rent,car.t_comments,b.name," +
                " cay.name,record.start_date,record.return_date,record.payment " +
                "from t_record record ,t_car car ,t_brand b, t_category cay" +
                " WHERE record.car_id = car.id AND car.brand_id = b.id AND car.category_id = cay.id AND record.id =?";
        query(conn, sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    record.setId(rs.getLong(1));
                    record.setModel(rs.getString(2));
                    record.setRent(rs.getDouble(3));
                    record.setComments(rs.getString(4));
                    record.setBrandName(rs.getString(5));
                    record.setCategoryName(rs.getString(6));
                    record.setStartDate(rs.getString(7));
                    record.setReturnDate(rs.getString(8));
                    record.setPayment(rs.getDouble(9));
                }

            }
        });


        return record;
    }

    @Override
    public Record queryNotReturnRecord(Connection conn, final long userId, final long carId) {
        final Record record = new Record();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where (user_Id = ? or car_id = ?) and return_date is null ";

        query(conn,sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,userId);
                pstmt.setLong(2,carId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    record.setId(rs.getLong(1));
                    record.setUserId(rs.getLong(2));
                    record.setCarId(rs.getLong(3));
                    record.setStartDate(rs.getString(4));
                    record.setReturnDate(rs.getString(5));
                    record.setPayment(rs.getDouble(6));



                }

            }
        });


        return record;
    }

    @Override
    public List<Record> queryAllRecords(List<QueryCondition> conditions) {
        StringBuilder otherCondetions = new StringBuilder();

        for (QueryCondition condition:conditions){
            if ("carId".equals(condition.getField())){
                otherCondetions.append(String.format(" AND r.car_id = %s",condition.getValue()));
            }else if ("userName".equals(condition.getField())){
                otherCondetions.append(String.format(" AND u.username = '%s'",condition.getValue()));
            }
        }



        final List<Record> list = new ArrayList<>();
        String sql = "select r.Id,r.user_id,r.car_id,u.username,r.start_date,r.return_date,r.payment,c.status from t_car c,t_record r,t_user u " +
                "where r.user_id=u.id and c.id=r.car_id"+otherCondetions.toString();

        query(sql, null,
         new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Record record = new Record(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getLong(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getDouble(7),
                            rs.getInt(8)

                    );
                    list.add(record);
                }

            }
        });


        return list;
    }

    @Override
    public List<Record> queryRecordsByCarId(final long carId) {
        final List<Record> list = new ArrayList<>();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where car_Id = ?";

        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,carId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Record record = new Record(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getLong(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6)

                    );
                    list.add(record);
                }

            }
        });


        return list;
    }

}
