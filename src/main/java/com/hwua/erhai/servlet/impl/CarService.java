package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.dao.IBrandDao;
import com.hwua.erhai.dao.ICarDao;
import com.hwua.erhai.dao.ICategoryDao;
import com.hwua.erhai.dao.IRecordDao;
import com.hwua.erhai.dao.impl.BrandDaoImpl;
import com.hwua.erhai.dao.impl.CarDaoImpl;
import com.hwua.erhai.dao.impl.CategoryDaoImpl;
import com.hwua.erhai.dao.impl.RecordDaoImpl;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.jdbc.ConnectionFactory;
import com.hwua.erhai.jdbc.DBUtil;
import com.hwua.erhai.jdbc.ResultSetHandler;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.query.QueryCondition;
import com.hwua.erhai.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CarService implements ICarService {
    private final ICarDao carDao = new CarDaoImpl();
    private final IRecordDao recordDao = new RecordDaoImpl();
    private final ICategoryDao categoryDao = new CategoryDaoImpl();
    private final IBrandDao brandDao = new BrandDaoImpl();
    static private final AtomicLong CAR_ID=new AtomicLong(37);
    @Override
    public int countRecord(List<QueryCondition> conditions) {
        return recordDao.queryAllRecords(conditions).size();
    }

    @Override
    public int countCars(List<QueryCondition> conditions) {
        return  carDao.queryAllCars(conditions).size();
    }

    @Override
    public List<Car> queryCars(List<QueryCondition> conditions, int limit, int offset) {
        List<Car>copyCars=carDao.queryAllCars(conditions);
        //select * from recordList where CarId="" and userId="" ;

        if (copyCars.size()==0){
            return copyCars;
        }
        //limit ${limit},${offset}
        int fromIndex=offset;
        if (fromIndex>=copyCars.size()){
            fromIndex=copyCars.size()-1;
        }
        int toIndex=offset+limit;
        if (toIndex>copyCars.size()){
            toIndex=copyCars.size();
        }
        return copyCars.subList(fromIndex,toIndex);}

    @Override
    public List<Record> queryRecord(List<QueryCondition> conditions, int limit, int offset) {
        List<Record>copyRecords=recordDao.queryAllRecords(conditions);
        //select * from carList where CarId="" and carBrand="" and car category="" order by rent desc;

        if (copyRecords.size()==0){
            return copyRecords;
        }
        //limit ${limit},${offset}
        int fromIndex=offset;
        if (fromIndex>=copyRecords.size()){
            fromIndex=copyRecords.size()-1;
        }
        int toIndex=offset+limit;
        if (toIndex>copyRecords.size()){
            toIndex=copyRecords.size();
        }
        return copyRecords.subList(fromIndex,toIndex);
    }

    @Override
    public List<Car> queryUsableCars(String type, String value) {
        return null;
    }

    @Override
    public List<Car> queryUsableCars(String type) {
        return null;
    }

    @Override
    public List<Car> queryCars(String type, String value) {
        return null;
    }

    @Override
    public Record rentCar(long userId, long carId) {
        Connection conn = ConnectionFactory.getConnection();
        Record record = null;
        long id;
        try {
            // 一次成功的租车，涉及修改汽车表和租车记录表这两张独立的表，
            // 所以需要启动事务来保证要么两者同时修改成功，要么两者同时不做修改（也就是失败后回滚）
            // 启动本次连接的事务功能
            conn.setAutoCommit(false);
            // 查询当前汽车是否可租赁
            Car car = carDao.queryCarById(conn, carId);
            if (car != null && car.getStatus() == 0 && car.getUsable() == 0) {
                // 如果可以租赁，修改汽车表
                int rows = carDao.updateCar(conn, carId, 1, 0);
                if (rows == 1) {// 返回修改的记录行数为1，说明修改汽车成功
                    // 添加记录
                    id = recordDao.addRecord(conn, userId, carId);
                    record = recordDao.queryRecordById(conn, id);
                } else {
                    throw new Exception(String.format("carDao.updateCar failed, carId[%s]", carId));
                }
            }
            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 如果修改汽车表和租车记录表时发生异常，就回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
        return record;
    }

    @Override
    public Record returnCar(long userId, long carId) {
        Connection conn = ConnectionFactory.getConnection();
        Record record = null;
        long id;
        try {
            // 一次成功的租车，涉及修改汽车表和租车记录表这两张独立的表，
            // 所以需要启动事务来保证要么两者同时修改成功，要么两者同时不做修改（也就是失败后回滚）
            // 启动本次连接的事务功能
            conn.setAutoCommit(false);
            // 查询当前汽车是否可租赁
            Car car = carDao.queryCarById(conn, carId);
            if (car != null && car.getStatus() == 1 && car.getUsable() == 0) {
                Car car1 = carDao.queryCarById(conn,carId);
                Record record1 = recordDao.queryNotReturnRecord(conn,userId,carId);
                int row = 0;
                if (record1.getCarId() == car.getId()) {
                    row = carDao.updateCar(conn, record1.getCarId(), 0, 1);
                    if (row == 1) {
                        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
                        String returnDate = Util.today();
                        String startDate = record1.getStartDate();
                        Date star = dft.parse(startDate);
                        Date endDay = dft.parse(returnDate);
                        long starTime = star.getTime();
                        long endTime = endDay.getTime();
                        long num = endTime - starTime;
                        long day = num / 24 / 60 / 60 / 1000;
                        double payment;
                        double payment1;
                        payment = day * car1.getRent();
                        payment1=1*car1.getRent();
                        if (day<=1){ recordDao.updateRecord(conn, record1.getId(), returnDate, payment1);}
                        else {recordDao.updateRecord(conn, record1.getId(), returnDate, payment);}
                        record = recordDao.queryRecordById(conn, record1.getId());

                    } else {
                        throw new Exception(String.format("carDao.updateCar failed, carId[%s]", carId));
                    }

                }

            }

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 如果修改汽车表和租车记录表时发生异常，就回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
        return record;
    }

    @Override
    public List<Record> queryRecords(String type, String value) {
        return null;
    }

    @Override
    public List<Category> queryAllCategories() {
        return null;
    }

    @Override
    public List<Brand> queryAllBrands() {
        return null;
    }

    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public Car addAndReturnCar(Car car) {
        List<QueryCondition>conditions=new ArrayList<>();
    List<Brand>brandList=brandDao.queryAllBrand();
        boolean exist =false;
        boolean newbrand=true;
        boolean newcategory=true;
        for (Car c:carDao.queryAllCars(conditions)){
            if (c.getCarNumber().equals(car.getCarNumber())){
                exist=true;
                break;
            }
        }
        for (Brand b:brandList){
            if (b.getName().equals(car.getBrandName())){
               car.setBrandId((int)b.getId());
                newbrand=false;
               break;
            }

        }
        if (newbrand==true){

                Brand brand=new Brand();
                brand.setName(car.getBrandName());
                brandDao.addBrand(brand);
                Brand brand1=brandDao.queryBrandByBrandName(car.getBrandName());
                car.setBrandId((int)brand1.getId());


        }

        for (Category c:categoryDao.queryAllCategories()){
            if (c.getName().equals(car.getCategoryName())){
                car.setCategoryId((int)c.getId());
                newcategory=false;
                break;
            }

//
        }
        if (newcategory==true){

                Category category=new Category();
                category.setName(car.getCategoryName());
                categoryDao.addCategory(category);
                Category category1=categoryDao.queryCategorybyCategoryName(car.getCategoryName());
                car.setCategoryId((int)category1.getId());


        }
        if (exist){
            throw new RuntimeException(String.format("车牌号：[%s] 已存在",car.getCarNumber()));
        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        carDao.addCar(car);
        car.setId(carDao.queryCarIdbyCarnumber(car.getCarNumber()).getId());
        return car;
    }

    @Override
    public Car updateAndReturnCar(Car car) {
        List<Brand>brandList=brandDao.queryAllBrand();
        List<QueryCondition>conditions=new ArrayList<>();
        boolean exist =false;
        boolean newbrand=true;
        boolean newcategory=true;
        for (Car c:carDao.queryAllCars(conditions)){
            if (c.getCarNumber().equals(car.getCarNumber())){
                exist=true;
                break;
            }
        }
        for (Brand b:brandList){
            if (b.getName().equals(car.getBrandName())){
                car.setBrandId((int)b.getId());
                newbrand=false;
                break;
            }

        }
        if (newbrand==true){

            Brand brand=new Brand();
            brand.setName(car.getBrandName());
            brandDao.addBrand(brand);
            Brand brand1=brandDao.queryBrandByBrandName(car.getBrandName());
            car.setBrandId((int)brand1.getId());


        }

        for (Category c:categoryDao.queryAllCategories()){
            if (c.getName().equals(car.getCategoryName())){
                car.setCategoryId((int)c.getId());
                newcategory=false;
                break;
            }

//
        }
        if (newcategory==true){

            Category category=new Category();
            category.setName(car.getCategoryName());
            categoryDao.addCategory(category);
            Category category1=categoryDao.queryCategorybyCategoryName(car.getCategoryName());
            car.setCategoryId((int)category1.getId());


        }
        if (exist){
            throw new RuntimeException(String.format("车牌号：[%s] 已存在",car.getCarNumber()));
        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        carDao.updateCar(car);
        car.setId(carDao.queryCarIdbyCarnumber(car.getCarNumber()).getId());
        return car;
    }

    @Override
    public boolean updateCar(String type, String value, long carId) {
        return false;
    }

    @Override
    public int deleteCar(long carId) {
        int i = carDao.deleteCar(carId);

        return i;
    }

    @Override
    public List<Car> queryCars(String type) {
        return null;
    }

    @Override
    public List<Record> queryRecords(String type) {
        return null;
    }

    @Override
    public Record rentCar(List<QueryCondition> conditions,Long userId, Long carId) {

        return null;
}
}
