package com.etc.jump.biz;

import java.util.List;

public interface CityBiz {

    List<String> provinceByKey(int key);

    int idByName(String name);

    String nameById(int id);

    int keysById(int id);

} 
