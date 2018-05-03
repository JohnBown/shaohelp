package com.etc.jump.bizimpl;

import com.etc.jump.biz.CityBiz;
import com.etc.jump.dao.CityDao;
import com.etc.jump.daoimpl.CityDaoImpl;

import java.util.List;

public class CityBizImpl implements CityBiz {
    CityDao dao = new CityDaoImpl();

    @Override
    public List<String> provinceByKey(int key) {
        if (key <0){
//            System.out.println("请输入非负整数");
            return null;
        }else {
            return dao.provinceByKey(key);
        }
    }

    @Override
    public int idByName(String name) {
        if (name==null||"".equals(name)){
//            System.out.println("输入不能为空");
        }
        return dao.idByName(name);
    }

    @Override
    public String nameById(int id) {
        if (id<0){
//            System.out.println("id小于0");
        }
        return dao.nameById(id);
    }

    @Override
    public int keysById(int id) {
        if (id<0){
//            System.out.println("id小于0");
        }
        return dao.keysById(id);
    }
}
