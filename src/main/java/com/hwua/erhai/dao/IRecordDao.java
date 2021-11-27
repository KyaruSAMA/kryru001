package com.hwua.erhai.dao;

import com.hwua.erhai.entity.Record;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.sql.Connection;
import java.util.List;

public interface IRecordDao {
    /**
     * 添加记录
     *
     * @param conn   连接对象
     * @param userId 用户ID
     * @param userId 汽车ID
     * @return 添加记录行数
     */
    long addRecord(Connection conn, long userId, long carId);

    /**
     * 更新租赁记录
     *
     * @param conn       连接对象
     * @param recordId   记录编号
     * @param returnTime 归还时间
     * @param payment    支付金额
     * @return 修改的行数
     */
    int updateRecord(Connection conn, long recordId, String returnTime, double payment);

    /**
     * 查询指定的未归还的汽车记录
     *
     * @param conn   连接对象
     * @param userId 用户编号
     * @param carId  汽车编号
     * @return 记录
     */
    Record queryNotReturnRecord(Connection conn, long userId, long carId);

    /**
     * 查询指定用户的全部租赁记录
     *
     * @param userId 用户编号
     * @return 租赁记录集合
     */
    List<Record> queryRecordsByUserId(long userId);

    /**
     * 通过记录编号查看指定记录
     *
     * @param conn 连接对象
     * @param id   记录编号
     * @return 记录对象
     */
    Record queryRecordById(Connection conn, long id);

    /**
     * 查询全部租赁记录
     *
     * @return 租赁记录集合
     */
    List<Record> queryAllRecords(List<QueryCondition> conditions);

    /**
     * 查询指定汽车租赁记录
     *
     * @return 租赁记录集合
     */
    List<Record> queryRecordsByCarId(long carId);


}
