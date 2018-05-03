package com.etc.jump.dao;

import com.etc.jump.entity.ShaoMonads;

import java.util.List;

public interface ShaoMonadsDao {

    boolean addshao(ShaoMonads shaoMonads);

    ShaoMonads infoByUidAId(int uid,int id);

    List<ShaoMonads> infoByUid(int uid);
}
