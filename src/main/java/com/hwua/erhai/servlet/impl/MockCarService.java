package com.hwua.erhai.servlet.impl;

import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.servlet.ICarService;
import com.hwua.erhai.servlet.query.QueryCondition;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MockCarService implements ICarService {
    static private  final List<String>BRAND_LIST=
            Arrays.asList("奇瑞","五菱宏光","奔驰","宝马","奥迪");
    static private  final List<String>MODEL_LIST=
            Arrays.asList("模型1","模型2","模型3","模型4","模型5");
    static private  final List<String>COLOR_LIST=
            Arrays.asList("红色","蓝色","黄色","绿色","黑色");
    static private  final List<String>CATEGORY_LIST=
            Arrays.asList("舒适型","越野型","精英型");
    static private final int CAR_COUNT=5;
    static private final int RECORD_COUNT=5;
    static private final AtomicLong CAR_ID=new AtomicLong(-1);
    static private final AtomicLong RECORD_ID=new AtomicLong(-1);
    static private final List<Car>CAR_LIST=loadCars();
    static private final List<Record> RECORD_LIST=loadRecord();
    static private List<Car> loadCars(){
        List<Car> cars=new ArrayList<>();
        for (int i=0;i<CAR_COUNT;i++){
            Car car=new Car();
            long id=CAR_ID.addAndGet(1);
            car.setId(id);
            car.setCarNumber(String.format("桂A·%05d",i));
            car.setBrandName(BRAND_LIST.get(i%BRAND_LIST.size()));
            car.setBrandId(i%BRAND_LIST.size());
            car.setModel(MODEL_LIST.get(i% MODEL_LIST.size()));
            car.setColor(COLOR_LIST.get(i%COLOR_LIST.size()));
            car.setCategoryId(i%CATEGORY_LIST.size());
            car.setCategoryName(CATEGORY_LIST.get(i%CATEGORY_LIST.size()));
            car.setComments("简介：xxx"+i);
            car.setPrice(10000);
            car.setRent((i%10+1)*10);
            car.setStatus(i%2);
            car.setUsable(i%2);
            cars.add(car);
        }
return cars;
    }
    static private Car copyCar(Car c){
        Car car=new Car();
        car.setId(c.getId());
        car.setCarNumber(c.getCarNumber());
        car.setBrandName(c.getBrandName());
        car.setBrandId(c.getBrandId());
        car.setModel(c.getModel());
        car.setColor(c.getColor());
        car.setCategoryId(c.getCategoryId());
        car.setCategoryName(c.getCategoryName());
        car.setComments(c.getComments());
        car.setPrice(c.getPrice());
        car.setRent(c.getRent());
        car.setStatus(c.getStatus());
        car.setUsable(c.getUsable());
        return car;
    }
    static private List<Car>copyCars(List<Car> carList){
        List<Car>cars=new ArrayList<>();
        for (Car c:CAR_LIST){
            cars.add(copyCar(c));
        }
        return cars;
    }
//    private long id;
//    private long userId;
//    private long carId;
//    private String startDate;
//    private String returnDate;
//    private double payment;
//    private double rent;
//    private String model;
//    private String comments;
//    private String brandName;
//    private String categoryName;
//    private String userName;
    static private List<Record>loadRecord(){
        List<Record>records=new ArrayList<>();
        for (int i=0;i<RECORD_COUNT;i++) {
            Record record = new Record();
            long id = RECORD_ID.addAndGet(1);
            record.setId(id);
            record.setUserId(id);
            record.setCarId(id);
            record.setStartDate("2021-05-06");
            record.setReturnDate("2021-05-07");
            record.setPayment(200);
            record.setRent(200);
            record.setBrandName(BRAND_LIST.get(i%BRAND_LIST.size()));
            record.setModel(MODEL_LIST.get(i% MODEL_LIST.size()));
            record.setCategoryName(CATEGORY_LIST.get(i%CATEGORY_LIST.size()));
            record.setComments("简介：xxx"+i);
            record.setUserName("zhangsan");
            records.add(record);
        }
        return records;
    }
    static private Record copyRecord(Record r){
        Record record=new Record();
        record.setId(r.getId());
        record.setCarId(r.getCarId());
        record.setUserId(r.getUserId());
        record.setStartDate(r.getStartDate());
        record.setReturnDate(r.getReturnDate());
        record.setPayment(r.getPayment());
        record.setRent(r.getRent());
        record.setBrandName(r.getBrandName());
        record.setModel(r.getModel());
        record.setCategoryName(r.getCategoryName());
        record.setComments(r.getComments());
        record.setUserName(r.getUserName());
        return record;
    }
    static private List<Record> copyRecords(List<Record> recordList){
        List<Record>records=new ArrayList<>();
        for (Record r:RECORD_LIST){
            records.add(copyRecord(r));
        }
        return records;
    }
    //select * from carList where CarId="" and CarBrand="" and carCategory="" order by rent desc;
    static private List<Car> select (List<Car>carList,List<QueryCondition> conditions){
        List <Car>result=new ArrayList<>();
    for (Car car:carList){
        boolean selected=true;
        for (QueryCondition condition : conditions) {
            if ("carId".equals(condition.getField())){
                if (!String.valueOf(car.getId()).equals(condition.getValue())){
                    selected=false;
                    break;
                }
            }
            else if ("carBrand".equals(condition.getField())){
                if (!String.valueOf(car.getBrandName()).equals(condition.getValue())){
                    selected=false;
                    break;
                }
            }
            else if ("carCategory".equals(condition.getField())){
                if (!String.valueOf(car.getCategoryName()).equals(condition.getValue())){
                    selected=false;
                    break;
                }
            }
            else if ("usable".equals(condition.getField())){
                if (!String.valueOf(car.getUsable()).equals(condition.getValue())){
                    selected=false;
                    break;
                }
            }
        }
        if (selected){
            result.add(car);
        }
    }
    for (QueryCondition condition:conditions){
        if ("priceOrder".equals(condition.getField())){
            if ("unordered".equals(condition.getValue())){
                break;
            }else if ("asc".equals(condition.getValue())){
                result.sort(
                        Comparator.comparingDouble(Car::getRent)
                );
                break;
            }else {
                result.sort(new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {

                        return Double.compare(o2.getRent(),o1.getRent());
                    }
                });
            }
        }
    }
    return result;
    }
    //select * from recordList where CarId="" and userId="" ;
    static private List<Record> select1 (List<Record>recordList,List<QueryCondition> conditions){
        List <Record>result=new ArrayList<>();
        for (Record record:recordList){
            boolean selected=true;
            for (QueryCondition condition : conditions) {
                if ("carId".equals(condition.getField())){
                    if (!String.valueOf(record.getId()).equals(condition.getValue())){
                        selected=false;
                        break;
                    }
                }


                else if ("userName".equals(condition.getField())){
                    if (!String.valueOf(record.getUserName()).equals(condition.getValue())){
                        selected=false;
                        break;
                    }
                }
            }
            if (selected){
                result.add(record);
            }
        }

        return result;
    }
    @Override
    public int countRecord(List<QueryCondition> conditions) {
        return select1(RECORD_LIST,conditions).size();
    }

    @Override
   synchronized public int countCars(List<QueryCondition> conditions) {

        return select(CAR_LIST,conditions).size();
    }

    @Override
    synchronized public List<Car> queryCars(List<QueryCondition> conditions, int limit, int offset) {
        List<Car>copyCars=copyCars(CAR_LIST);
        //select * from recordList where CarId="" and userId="" ;
        copyCars=select(copyCars,conditions);
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
        return copyCars.subList(fromIndex,toIndex);
    }

    @Override
    public List<Record> queryRecord(List<QueryCondition> conditions, int limit, int offset) {
        List<Record>copyRecords=copyRecords(RECORD_LIST);
        //select * from carList where CarId="" and carBrand="" and car category="" order by rent desc;
        copyRecords=select1(copyRecords,conditions);
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
    synchronized public boolean addCar(Car car) {
        return false;
    }

    @Override
  synchronized   public Car addAndReturnCar(Car car) {
        if (car.getId()==-1){
            long id=CAR_ID.addAndGet(1);
            car.setId(id);
        }
        boolean exist =false;
        for (Car c:CAR_LIST){
         if (c.getId()==car.getId()){
             exist=true;
             break;
         }
        }
        if (exist){
            throw new RuntimeException(String.format("car id[%d] 已存在",car.getId()));
        }
        //TODO:brandId和categoryId需要通过分别根据brandname和categoryname从数据库里查询得到。
        //之后将这两个id设置到car对象里即可
        CAR_LIST.add(copyCar(car));
        return car;
    }

    @Override
   synchronized public Car updateAndReturnCar(Car car) {
        for (Car c:CAR_LIST){
            if (c.getId()==car.getId()){
                c.setCarNumber(car.getCarNumber());
                c.setBrandId(car.getBrandId());
                c.setBrandName(car.getBrandName());
                c.setModel(car.getModel());
                c.setColor(car.getColor());
                c.setCategoryId(car.getCategoryId());
                c.setCategoryName(car.getCategoryName());
                c.setComments(car.getComments());
                c.setPrice(car.getPrice());
                c.setRent(car.getRent());
                c.setStatus(car.getStatus());
                c.setUsable(car.getUsable());
                return copyCar(c);
            }
        }
        return  null;
    }

    @Override
    public boolean updateCar(String type, String value, long carId) {
        return false;
    }

    @Override
    synchronized public Car deleteCar(long carId) {
        int carIndex=-1;
        for (int i=0;i<CAR_LIST.size();i++){
            if (CAR_LIST.get(i).getId()==carId){
                carIndex=i;
                break;
            }
        }
        if (carIndex==-1){
            return null;
        }
        return copyCar(CAR_LIST.remove(carIndex));
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
