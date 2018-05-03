package com.etc.jump.daoimpl;

import com.etc.jump.dao.CityDao;
import com.etc.jump.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao{
    JDBCUtil util = new JDBCUtil();

    @Override
    public List<String> provinceByKey(int key) {

        ResultSet rs = util.query("SELECT * FROM city WHERE ke=?",key);
        List<String> list = new ArrayList<>();
        try {
            while (rs.next()){

                String str = rs.getString("name");
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return list;
    }

    @Override
    public int idByName(String name) {

        ResultSet rs = util.query("SELECT * FROM city WHERE name=?",name);
        int x = 0;
        try {
            if (rs.next()){
                x = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return x;
    }

    @Override
    public String nameById(int id) {

        ResultSet rs = util.query("SELECT * FROM city WHERE id=?",id);
        String s=null;
        try {
            if (rs.next()){
                s =rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return s;
    }

    @Override
    public int keysById(int id) {

        ResultSet rs = util.query("SELECT * FROM city WHERE id=?",id);
        int  x=0;
        try {
            if (rs.next()){
                x =rs.getInt("ke");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return x;
    }
}
