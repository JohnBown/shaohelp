package com.etc.jump.daoimpl;

import com.etc.jump.dao.UsersDao;
import com.etc.jump.entity.Users;
import com.etc.jump.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {
    JDBCUtil util = new JDBCUtil();

    /**
     * 根据用户id查询所有个人信息
     * @param uid 传入一个用户id
     * @return Users 返回该用户的个人信息
     */
    @Override
    public Users queryInfoByUid(int uid) {
        ResultSet rs = util.query("SELECT * FROM users WHERE u_id=?",uid);
        Users users = null;
        try {
            if (rs.next()){
                users = new Users();
                users.setU_id(rs.getInt("u_id"));
                users.setU_name(rs.getString("u_name"));
                users.setU_city(rs.getInt("u_city"));
                users.setU_school(rs.getString("u_school"));
                users.setU_address(rs.getString("u_address"));
                users.setU_phone(rs.getString("u_phone"));
                users.setU_pwd(rs.getString("u_pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return users;
    }

    /**
     * 根据用户名与用户密码进行登录
     * @param name 用户名
     * @param pwd 用户密码
     * @return 返回用户对象
     */
    @Override
    public Users login(String name, String pwd) {
        ResultSet rs = util.query("SELECT * FROM users WHERE u_name=? AND u_pwd=?",name,pwd);
        Users users = null;
        try {
            if (rs.next()){
                users = new Users();
                users.setU_id(rs.getInt("u_id"));
                users.setU_name(rs.getString("u_name"));
                users.setU_pwd(rs.getString("u_pwd"));
                users.setU_school(rs.getString("u_school"));
                users.setU_address(rs.getString("u_address"));
                users.setU_phone(rs.getString("u_phone"));
                users.setU_city(rs.getInt("u_city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return users;
    }

    /**
     * 添加用户
     * @param users 传入用户对象
     * @return 判断是否添加成功
     */
    @Override
    public boolean addUser(Users users) {
        int i = util.update("INSERT INTO users(u_id, u_name, u_pwd, u_school, u_icon, u_address, u_phone, u_city) " +
                "VALUE (NULL ,?,?,?,NULL ,?,?,?)",
                users.getU_name(),
                users.getU_pwd(),
                users.getU_school(),
                users.getU_address(),
                users.getU_phone(),
                users.getU_city());
        return i>0;
    }

    /**
     * 更新密码
     * @param userid 用户id
     * @param newpwd 要更新的密码
     * @return 是否更新成功
     */
    @Override
    public boolean updatePwd(int userid, String newpwd) {
        int i = util.update("UPDATE users SET u_pwd=? WHERE u_id=?",newpwd,userid);
        return i>0;
    }

    /**
     * 更新用户信息
     * @param name 用户名
     * @param city 省/市/区的id
     * @param address 详细地址
     * @param school 学校
     * @param phone 电话号
     * @return 返回是否更新成功
     */
    @Override
    public boolean updateInfoById(int id,String name, int city, String address, String school, String phone) {
        int i = util.update("UPDATE users SET u_name=?, u_city=?, u_address=?,u_school=?,u_phone=? WHERE u_id=?",
                name,city,address,school,phone,id);
        return i>0;
    }

    /**
     * 根据登录的用户名查询对应所有信息
     * @param name 传入用户名
     * @return Users 返回该用户所有信息
     */
    @Override
    public Users queryAllByName(String name) {
        ResultSet rs = util.query("SELECT * FROM users WHERE u_name=?",name);
        Users users = null;
        try {
            if (rs.next()){
                users = new Users();
                users.setU_id(rs.getInt("u_id"));
                users.setU_name(rs.getString("u_name"));
                users.setU_pwd(rs.getString("u_pwd"));
                users.setU_city(rs.getInt("u_city"));
                users.setU_address(rs.getString("u_address"));
                users.setU_school(rs.getString("u_school"));
                users.setU_phone(rs.getString("u_phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return users;
    }
}
