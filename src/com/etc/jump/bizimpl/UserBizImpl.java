package com.etc.jump.bizimpl;

import com.etc.jump.biz.UserBiz;
import com.etc.jump.dao.UsersDao;
import com.etc.jump.daoimpl.UsersDaoImpl;
import com.etc.jump.entity.Users;

public class UserBizImpl implements UserBiz {
    UsersDao dao = new UsersDaoImpl();

    @Override
    public Users queryInfoByUid(int uid) {
        if (uid>=1000000000){
            return dao.queryInfoByUid(uid);
        }
        return null;
    }

    //验证用户名和密码是否正确
    @Override
    public Users login(String name, String pwd) {
        if (name==null||"".equals(name)||pwd==null||"".equals(pwd)){
//            System.out.println("账号或密码格式有误");
            return null;
        }else {
            return dao.login(name,pwd);
        }
    }

    @Override
    public boolean addUser(Users users) {
        if (users==null){
//            System.out.println("请输入相关内容");
            return false;
        }
        return dao.addUser(users);
    }

    @Override
    public boolean updatePwd(int userid, String newpwd) {
        if (newpwd==null||"".equals(newpwd)){
//            System.out.println("新密码不能为空");
            return false;
        }else {
            return dao.updatePwd(userid,newpwd);
        }
    }

    @Override
    public boolean updateInfoById(int id, String name, int city, String address, String school, String phone) {
        if (name==null||"".equals(name)||
                address==null||"".equals(address)||
                school==null||"".equals(school)||
                phone==null||"".equals(phone)){
//            System.out.println("新内容不能为空");
            return false;
        }else {
            return dao.updateInfoById(id,name,city,address,school,phone);
        }
    }

    @Override
    public boolean nameExist(String name) {
        Users users = dao.queryAllByName(name);
        if (users==null)
            return false;
        else return true;
    }

}
