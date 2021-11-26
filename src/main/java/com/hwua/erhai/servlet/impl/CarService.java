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
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.List;

public class CarService implements ICarService {
    private final ICarDao carDao = new CarDaoImpl();
    private final IRecordDao recordDao = new RecordDaoImpl();
    private final ICategoryDao categoryDao = new CategoryDaoImpl();
    private final IBrandDao brandDao = new BrandDaoImpl();

    @Override
    public int countRecord(List<QueryCondition> conditions) {
        return 0;
    }

    @Override
    public int countCars(List<QueryCondition> conditions) {
        return 0;
    }

    @Override
    public List<Car> queryCars(List<QueryCondition> conditions, int limit, int offset) {
        return null;
    }

    @Override
    public List<Record> queryRecord(List<QueryCondition> conditions, int limit, int offset) {
        return null;
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
        return null;
    }

    @Override
    public Car updateAndReturnCar(Car car) {
        return null;
    }

    @Override
    public boolean updateCar(String type, String value, long carId) {
        return false;
    }

    @Override
    public Car deleteCar(long carId) {
        return null;
    }

    @Override
    public List<Car> queryCars(String type) {
        return null;
    }

    @Override
    public List<Record> queryRecords(String type) {
        return null;
    }
}
