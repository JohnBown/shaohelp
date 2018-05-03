package com.etc.jump.dao;

import com.etc.jump.entity.Users;

public interface UsersDao {

    /**
     * 根据用户id查询所有个人信息
     * @param uid 传入一个用户id
     * @return users 返回该用户的个人信息
     * @author 王兴伟
     */
    Users queryInfoByUid(int uid);

    /**
     *  根据用户名与用户名密码进行登陆
     *  @author wxj
     * @param name,pwd 传入用户名和密码
     * @return  返回用户对象
     */
    Users login(String name, String pwd);

    /**
     *  添加用户的方法
     *  @author wxj
     * @param users 传入一个用户对象
     * @return 返回boolean值，判断是否添加成功
     */
    boolean addUser(Users users);

    /**
     * 根据用户id修改密码
     * @param userid 传入当前用户id
     * @param newpwd 传入新密码
     * @return boolean 返回是否修改成功
     * @author 王兴伟
     */
    boolean updatePwd(int userid, String newpwd);

    /**
     * 根据用户名查询用户
     * @author wxj
     * @param name 传入一个用户名
     * @return 返回Boolean值，判断是否存在
     */
    Users queryAllByName(String name);

    /**
     * 根据id修改个人信息，包括：用户名、所在省/市/区、
     * 详细地址、所在学校、电话号码
     * @param id 传入当前用户id
     * @param name,school,address,phone 传入修改后的相关信息
     * @return boolean 返回是否修改成功
     * @author 王兴伟
     */
    boolean updateInfoById(
            int id,
            String name,
            int city,
            String address,
            String school,
            String phone
    );

}
