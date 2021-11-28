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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return null;
    }

    @Override
    public Record returnCar(long userId, long carId) {
        return null;
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
    List<Brand>brandList=brandDao.queryAllBrand();
        boolean exist =false;
        boolean newbrand=true;
        boolean newcategory=true;
//        for (Car c:carDao.queryAllCars(null)){
//            if (c.getCarNumber().equals(car.getCarNumber())){
//                exist=true;
//                break;
//            }
//        }
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
//        if (exist){
//            throw new RuntimeException(String.format("车牌号：[%d] 已存在",car.getCarNumber()));
//        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        carDao.addCar(car);
        car.setId(carDao.queryCarIdbyCarnumber(car.getCarNumber()).getId());
        return car;
    }

    @Override
    public Car updateAndReturnCar(Car car) {
        List<Brand>brandList=brandDao.queryAllBrand();
        boolean exist =false;
        boolean newbrand=true;
        boolean newcategory=true;
//        for (Car c:carDao.queryAllCars(null)){
//            if (c.getCarNumber().equals(car.getCarNumber())){
//                exist=true;
//                break;
//            }
//        }
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
//        if (exist){
//            throw new RuntimeException(String.format("车牌号：[%d] 已存在",car.getCarNumber()));
//        }
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
