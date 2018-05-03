package com.etc.jump.dao;

import com.etc.jump.entity.Monads;

import java.util.List;

public interface MonadsDao {

    //根据单子id查询并返回该单子信息
    Monads queryInfoById(int id);

    /**
     * 根据用户id查询并根据状态排列我的订单
     * @param uid 传入一个用户id
     * @return list 返回该用户的所有订单
     * @author 丘金英
     */
    List<Monads> queryInfoByIdStatus(int uid);

    //查找订单未完成的订单号最大值
    Monads queryMaxUndoneId();

    //查找未完成的订单号最大值所对应的所有值列表
    List<Monads> queryNewUndone();

    /**
     * 查询未完成订单列表
     * @param m_status=0状态为0，状态为未完成
     * @return list 返回该数据库中所有未完成的订单
     * @author 黄毅鹏
     */
    List<Monads> queryAllUndone();

    /**
     * 添加订单的方法
     * @author wxj
     * @param 传入一个订单monads对象
     * @return 返回Boolean值，判断是否添加成功
     */
    boolean addOrd(Monads monads);

    /**
     * 根据骑手id查询他的所有捎单对应的订单信息,并按状态排序
     * @param uid 传入一个用户id
     * @return 返回该用户的捎单对应的订单信息
     * @author 丘金英
     */
    List<Monads> queryInfoByMonadsid(int uid);

    /**
     * 根据订单id更改订单状态
     * @param id 传入当前订单id
     * @param status 更改后的状态
     * @return boolean 返回是否更改成功
     * @author 王兴伟
     */
    boolean updateStatusById(int id, int status);

    /**
     * 根据订单id更改删除订单
     * @param id 传入当前订单id
     * @return boolean 返回是否删除成功
     * @author 王兴伟
     */
    boolean deleteById(int id);

    /**
     * 根据用户的id查找，更新订单状态值
     * @param uid 传入一个用户id
     * @return 返回该用户的订单对应的订单状态
     * @author 黄毅鹏
     */
    Monads queryByidUpdateStatus(int uid);

}
