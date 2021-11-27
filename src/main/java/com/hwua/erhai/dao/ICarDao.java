package com.hwua.erhai.dao;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.sql.Connection;
import java.util.List;

public interface ICarDao {
    /**
     * 查询所有的汽车，包含品牌名，类别名
     *
     * @return 汽车的集合
     */
//s
    List<Car> queryAllCars(List<QueryCondition> conditions);

    /**
     * 按照价格升序查询全部汽车
     *
     * @return 汽车集合
     */
    List<Car> queryCarsByPriceAsc();

    /**
     * 按照价格降序查询全部汽车
     *
     * @return 汽车集合
     */
    List<Car> queryCarsByPriceDesc();

    /**
     * 通过类别id查看汽车
     *
     * @param categoryId 类别id
     * @return 汽车集合
     */
    List<Car> queryCarsByCategoryId(int categoryId);

    /**
     * 通过品牌id查看汽车
     *
     * @param brandId 品牌id
     * @return 汽车集合
     */
    List<Car> queryCarsByBrandId(int brandId);

    /**
     * 查询所有的汽车，包含品牌名，类别名
     *
     * @return 汽车的集合
     */
    List<Car> queryAllUsableCars();

    /**
     * 按照价格升序查询全部汽车
     *
     * @return 汽车集合
     */
    List<Car> queryUsableCarsByPriceAsc();

    /**
     * 按照价格降序查询全部汽车
     *
     * @return 汽车集合
     */
    List<Car> queryUsableCarsByPriceDesc();

    /**
     * 通过类别id查看汽车
     *
     * @param categoryId 类别id
     * @return 汽车集合
     */
    List<Car> queryUsableCarsByCategoryId(int categoryId);

    /**
     * 通过品牌id查看汽车
     *
     * @param brandId 品牌id
     * @return 汽车集合
     */
    List<Car> queryUsableCarsByBrandId(int brandId);

    /**
     * 通过id查找汽车
     *
     * @param id 汽车编号
     * @return 汽车对象
     */
    Car queryCarById(Connection conn, long id);

    /**
     * 修改汽车状态
     *
     * @param conn         连接对象
     * @param id           汽车编号
     * @param status       汽车状态
     * @param beforeStatus 汽车修改前的状态
     * @return 修改的行数
     */
    int updateCar(Connection conn, long id, int status, int beforeStatus);

    /**
     * 通过id查找汽车,包含类别名，品牌名
     *
     * @param id 汽车编号
     * @return 汽车对象集合，统一使用集合，便于后面把所有查询集中起来
     */
    List<Car> queryCarById(long id);
    List<Car> queryCarByCarnumber(long carNumber);
    /**
     * 添加汽车
     *
     * @param car 要添加的数据
     * @return 添加的行数
     */

    int addCar(Car car);

    /**
     * 通过汽车编号修改可借汽车的租金
     *
     * @param carId
     * @return 修改的行数
     */
    int updateCanLendCarRentById(long carId, double rent);

    /**
     * 通过汽车编号修改可借汽车usable(是否可用)
     *
     * @param carId
     * @return 修改的行数
     */
    int updateCanLendCarUsableById(long carId, int usable);
}
