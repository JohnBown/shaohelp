package com.etc.jump.dao;

import java.util.List;

public interface CityDao {

    /**
     * 根据ke查找省
     * @param key 传入省的ke值
     * @return list 返回此ke值的省的集合
     * @author 王兴伟
     */
    List<String> provinceByKey(int key);

    //根据省/市/区名字确认id
    int idByName(String name);

    //根据id确认省/市/区名字
    String nameById(int id);

    //根据id确认省/市/区的keys
    int keysById(int id);

} 
