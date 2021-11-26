package com.hwua.erhai.servlet;

import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.List;

public interface ICarService {
    int countRecord(List<QueryCondition>conditions);
    int countCars(List<QueryCondition>conditions);
    List<Car>queryCars(List<QueryCondition> conditions,int limit ,int offset);
    List<Record>queryRecord(List<QueryCondition> conditions,int limit ,int offset);
    /**
     * 查询上架汽车的集合
     *
     * @param type  第一个参数，查找的类型
     * @param value 第二个参数，查找的指定参数值
     * @return 汽车的集合
     */
    List<Car> queryUsableCars(String type, String value);
    List<Car> queryUsableCars(String type);
    /**
     * 查询汽车的集合
     *
     * @param type  第一个参数，查找的类型
     * @param value 第二个参数，查找的指定参数值
     * @return 汽车的集合
     */
    List<Car> queryCars(String type, String value);

    /**
     * 租赁汽车
     *
     * @param userId 用户编号
     * @param carId  汽车编号
     * @return 租赁成功的租赁记录
     */
    Record rentCar(long userId, long carId);

    /**
     * 归还租赁汽车
     *
     * @param userId 用户编号
     * @param carId  汽车编号
     * @return 归还成功的租赁记录
     */
    Record returnCar(long userId, long carId);

    /**
     * 查询记录的集合
     *
     * @param type  第一个参数，查找的类型
     * @param value 第二个参数，查找的指定参数值
     * @return
     */
    List<Record> queryRecords(String type, String value);

    /**
     * 查询全部的汽车类型
     *
     * @return 类型集合
     */
    List<Category> queryAllCategories();

    /**
     * 查询全部的汽车品牌
     *
     * @return 品牌集合
     */
    List<Brand> queryAllBrands();

    /**
     * 添加汽车
     *
     * @param car
     * @return 是否添加成功
     */
    boolean addCar(Car car);


    Car addAndReturnCar(Car car);
    Car updateAndReturnCar(Car car);
    /**
     * 修改汽车
     *
     * @param type  要修改的内容
     * @param value 修改的值
     * @param carId 汽车编号
     * @return 修改成功的汽车
     */
    boolean updateCar(String type, String value, long carId);
Car deleteCar(long carId);

    List<Car> queryCars(String type);

    List<Record> queryRecords(String type);
Record rentCar(List<QueryCondition> conditions,Long userId,Long carId);

}
