package com.etc.jump.biz;

import com.etc.jump.entity.Monads;

import java.util.List;

public interface MonadsBiz {

    //根据状态查询排列我的订单
    List<Monads> queryInfoByIdStatus(int uid);

    //查找订单未完成的订单号最大值
    Monads queryMaxUndoneId();

    //查找未完成的订单号最大值所对应的所有值列表
    List<Monads> queryNewUndone();

    //查询未完成的订单的所有记录
    List<Monads> queryAllUndone();

    //添加订单，用于下单
    boolean addOrd(Monads monads);

    //根据订单id更改订单状态
    boolean updateStatusById(int id, int status);

    //更具id删除订单
    boolean deleteById(int id);

    Monads queryInfoById(int id);
}
