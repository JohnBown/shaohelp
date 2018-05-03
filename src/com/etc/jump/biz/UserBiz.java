package com.etc.jump.biz;

import com.etc.jump.entity.Users;

public interface UserBiz {

    Users queryInfoByUid(int uid);

    Users login(String name, String pwd);

    boolean addUser(Users users);

    boolean updatePwd(int userid, String newpwd);

    boolean updateInfoById(
            int id,
            String name,
            int city,
            String address,
            String school,
            String phone
    );

    boolean nameExist(String name);
}
