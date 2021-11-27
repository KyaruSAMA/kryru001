package com.hwua.erhai.dao;

import com.hwua.erhai.entity.Brand;

import java.util.List;

public interface IBrandDao {
    List<Brand> queryAllBrand();
    int addBrand(Brand brand);
    Brand queryBrandByBrandName(String brandName);
}
