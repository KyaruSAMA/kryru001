package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.ICarDao;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends JDBCTemplate implements ICarDao {

    @Override
    public List<Car> queryAllCars() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status,car.usable,car.car_number,car.color,car.price "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id " ;
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getLong(13));
                    list.add(car);
                }
            }
        });
        return list;
    }

    @Override
    public List<Car> queryAllCars(List<QueryCondition> conditions) {
        StringBuilder otherCondetions = new StringBuilder();
        String orderCondition = "";

        for (QueryCondition condition:conditions){
            if ("carId".equals(condition.getField())){
                otherCondetions.append(String.format(" AND car.id = '%s'",condition.getValue()));
            }else if ("carBrand".equals(condition.getField())){
                otherCondetions.append(String.format(" AND b.name = '%s'",condition.getValue()));
            }else if ("carCategory".equals(condition.getField())){
                otherCondetions.append(String.format(" AND cay.name = '%s'",condition.getValue()));
            }else if ("usable".equals(condition.getField())){
                otherCondetions.append(String.format(" AND car.usable = '%s'",condition.getValue()));
            }
        }
        for (QueryCondition condition:conditions){
            if ("priceOrder".equals(condition.getField())){
                if ("asc".equals(condition.getValue())){
                    orderCondition = " ORDER BY car.rent ASC";
                } else if ("desc".equals(condition.getValue())){
                    orderCondition = " ORDER BY car.rent DESC";
                }
                break;
            }
        }



        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status,car.usable,car.car_number,car.color,car.price "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id " +otherCondetions.toString()
                + orderCondition;
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getLong(13));
                    list.add(car);
                }
            }
        });
        return list;
    }



    @Override
    public List<Car> queryCarsByPriceAsc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "ORDER BY car.rent ASC";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }
            }
        });
        return list;

    }

    @Override
    public List<Car> queryCarsByPriceDesc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "ORDER BY car.rent DESC";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }
            }
        });
        return list;
    }

    @Override
    public List<Car> queryCarsByCategoryId(final int categoryId) {

        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.category_id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,categoryId);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }

        });
         return list;
    }
// todo
    @Override
    public List<Car> queryCarsByBrandId(final int brandId) {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.brand_id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,brandId);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public Car queryCarById(Connection conn, final long id) {
        final Car car = new Car();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                           car.setId(rs.getLong(1));
                           car.setBrandId(rs.getInt(2));
                           car.setBrandName(rs.getString(3));
                           car.setModel( rs.getString(4));
                           car.setCategoryId( rs.getInt(5));
                           car.setCategoryName(rs.getString(6));
                           car.setComments(rs.getString(7));
                           car.setRent(rs.getDouble(8));
                           car.setStatus(rs.getInt(9));
                           car.setUsable(rs.getInt(10));

                }

            }
        });
//        if (Long.parseLong(car.getIdam())==null)
        return car;
    }

    @Override
    public int updateCar(Connection conn, final long id, final int status, final int beforeStatus) {
        String sql = "update t_car set  status =? where id =?";
        update(conn,sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,status);
                pstmt.setLong(2,id);
            }
        });
        return 1;
    }

    @Override
    public List<Car> queryCarById(final long id) {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.Id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }
    @Override
    public List<Car> queryCarByCarnumber(final long carNumber) {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.car_number = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,carNumber);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public int updateCar(Car car) {
        String sql = "UPDATE t_car SET car_number =?,brand_id =?,model=?,color=?,category_id=?,t_comments=?,price=?,rent=?,usable=?,status=? WHERE id = ?";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,car.getCarNumber());
                pstmt.setInt(2,car.getBrandId());
                pstmt.setString(3,car.getModel());
                pstmt.setString(4,car.getColor());
                pstmt.setInt(5,car.getCategoryId());
                pstmt.setString(6,car.getComments());
                pstmt.setDouble(7,car.getPrice());
                pstmt.setDouble(8,car.getRent());
                pstmt.setInt(9,car.getUsable());
                pstmt.setInt(10,car.getStatus());
                pstmt.setLong(11,car.getId());
            }
        });
    }

//todo

    @Override
    public int addCar(final Car car) {
        String sql = "insert into t_car (car_number,brand_id,model,color,category_id,t_comments,price,rent,status,usable)"
                +"values (?,?,?,?,?,?,?,?,?,?)";
      return update(sql, new PreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement pstmt) throws SQLException {
              pstmt.setString(1,car.getCarNumber());
              pstmt.setInt(2,car.getBrandId());
              pstmt.setString(3,car.getModel());
              pstmt.setString(4,car.getColor());
              pstmt.setInt(5,car.getCategoryId());
              pstmt.setString(6,car.getComments());
              pstmt.setDouble(7,car.getPrice());
              pstmt.setDouble(8,car.getRent());
              pstmt.setInt(9,car.getStatus());
              pstmt.setInt(10,car.getUsable());

          }
      });

    }
    @Override
    public int deleteCar(long carId) {
        String sql = "DELETE FROM t_car where id=?";
        return update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,carId);
            }
        });
    }
    @Override
    public int updateCanLendCarRentById(final long carId, final double rent) {
        String sql = "UPDATE t_car set rent=? where id= ? ";
        update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setDouble(1,rent);
                pstmt.setLong(2,carId);
            }
        });

        return 1;
    }


    @Override
    public int updateCanLendCarUsableById(final long carId, final int usable) {
        String sql = "UPDATE t_car set usable=? where id= ? ";
        update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,usable);
                pstmt.setLong(2,carId);
            }
        });
        return 1;
    }

    @Override
    public Car queryCarIdbyCarnumber(String carNumber) {
        final Car car = new Car();
        String sql = "SELECT id from t_car where car_number=?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,carNumber);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    car.setId(rs.getLong(1));

                }

            }
        });
        return car;
    }

    @Override
    public List<Car> queryAllUsableCars() {
        final List<Car> list = new ArrayList<>();
        String sql ="SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "and car.usable =0 "
                ;
        query(sql, null
        , new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public List<Car> queryUsableCarsByPriceAsc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "and car.usable = 0 ORDER BY car.rent asc";

        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9)
                    );
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public List<Car> queryUsableCarsByPriceDesc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "and car.usable = 0 ORDER BY car.rent desc";

        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9)
                         );
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public List<Car> queryUsableCarsByCategoryId(final int categoryId) {
        final List<Car> list = new ArrayList<>();
        String sql ="SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "and car.usable = 0 "+"and category_id =?";
        ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,categoryId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public List<Car> queryUsableCarsByBrandId(final int brandId) {
        final List<Car> list = new ArrayList<>();
        String sql ="SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "and car.usable =0"
                +"and brand_Id =?";
        ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,brandId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9));
                    list.add(car);
                }

            }
        });
        return list;
    }

}
