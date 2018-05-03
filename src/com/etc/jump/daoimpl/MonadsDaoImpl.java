package com.etc.jump.daoimpl;

import com.etc.jump.dao.MonadsDao;
import com.etc.jump.entity.Monads;
import com.etc.jump.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MonadsDaoImpl implements MonadsDao{
    JDBCUtil util = new JDBCUtil();

    /**
     * 根据单子id查询并返回该单子信息
     * @param id
     * @return
     */
    @Override
    public Monads queryInfoById(int id) {
        ResultSet rs = util.query("SELECT * FROM monads WHERE m_id=?",id);
        Monads monads = null;
        try {
            if (rs.next()){
                monads = new Monads();
                monads.setM_status(rs.getInt("m_status"));
                monads.setM_id(rs.getInt("m_id"));
                monads.setM_order_datetime(rs.getTimestamp("m_order_datetime").toLocalDateTime());
                monads.setM_address_from(rs.getString("m_address_from"));
                monads.setM_goodinfo(rs.getString("m_goodsinfo"));
                monads.setM_uid(rs.getInt("m_uid"));
                monads.setM_address_to(rs.getString("m_address_to"));
                monads.setM_failure_time(rs.getTimestamp("m_failure_time").toLocalDateTime());
                monads.setM_goods(rs.getInt("m_goods"));
                monads.setM_time_at(rs.getTimestamp("m_time_at").toLocalDateTime());
                monads.setM_time_from(rs.getTimestamp("m_time_from").toLocalDateTime());
                monads.setM_time_to(rs.getTimestamp("m_time_to").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return monads;
    }

    /**
     * 查找未完成订单的最大值
     * @return 该订单基本信息
     */
    @Override
    public Monads queryMaxUndoneId() {
        ResultSet rs = util.query("SELECT max(m_id) FROM monads WHERE m_status=0");
        Monads monads = null;
        try {
            if (rs.next()){
                monads = new Monads();
                monads.setM_id(rs.getInt("max(m_id)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return monads;
    }

    /**
     * 查找未完成的订单号最大值所对应的所有值列表
     * @return
     * @deprecated 该方法无效
     */
    @Override
    public List<Monads> queryNewUndone() {
        return null;
    }


    /**
     * 添加一条订单信息
     * @param monads
     * @return
     */
    @Override
    public boolean addOrd(Monads monads) {
        int i = util.update("INSERT INTO monads(m_id,m_uid,m_goods,m_goodsinfo,m_order_datetime,m_time_at,m_address_from,m_time_from,m_time_to,m_address_to,m_status,m_failure_time) " +
                        "VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?)",
                monads.getM_uid(),
                monads.getM_goods(),
                monads.getM_goodinfo(),
                Timestamp.valueOf(monads.getM_order_datetime()),
                Timestamp.valueOf(monads.getM_time_at()),
                monads.getM_address_from(),
                Timestamp.valueOf(monads.getM_time_from()),
                Timestamp.valueOf(monads.getM_time_to()),
                monads.getM_address_to(),
                monads.getM_status(),
                Timestamp.valueOf(monads.getM_failure_time()));
//        "    VALUES (1000000000,1000000001,0,'欢乐儿童套餐','2018-05-02 10:13:00','2018-05-02 11:30:00','宏路街道18号店',\n" +
//                "           '2018-05-02 12:00:00','2018-05-02 13:00:00','师大5号楼210',0,'2018-05-02 10:30:00')");
        return i>0;
    }

    /**
     * 根据用户id查询用户所有订单，并根据下单时间排序
     * @param uid
     * @return 用户所有订单，以order_datetime
     */
    @Override
    public List<Monads> queryInfoByIdStatus(int uid) {
        ResultSet rs = util.query("SELECT * FROM monads WHERE m_uid=? ORDER BY m_order_datetime",uid);
        Monads monads = null;
        List<Monads> list = new ArrayList<Monads>();
        try {
            while (rs.next()){
                monads = new Monads();
                monads.setM_id(rs.getInt("m_id"));
                monads.setM_uid(rs.getInt("m_uid"));
                monads.setM_goods(rs.getInt("m_goods"));
                monads.setM_goodinfo(rs.getString("m_goodsinfo"));
                monads.setM_order_datetime(rs.getTimestamp("m_order_datetime").toLocalDateTime());
                monads.setM_time_at(rs.getTimestamp("m_time_at").toLocalDateTime());
                monads.setM_address_from(rs.getString("m_address_from"));
                monads.setM_address_to(rs.getString("m_address_to"));
                monads.setM_time_from(rs.getTimestamp("m_time_from").toLocalDateTime());
                monads.setM_time_to(rs.getTimestamp("m_time_to").toLocalDateTime());
                monads.setM_failure_time(rs.getTimestamp("m_failure_time").toLocalDateTime());
                monads.setM_status(rs.getInt("m_status"));
                list.add(monads);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return list;
    }

    /**
     * 查询所有未被接单的单子信息
     * @return 返回单子List并以time_at排序
     */
    @Override
    public List<Monads> queryAllUndone() {
        ResultSet rs = util.query("SELECT * FROM monads WHERE m_status=0 ORDER BY m_time_at");
        Monads monads;
        List<Monads> list = new ArrayList<>();
        try {
            while (rs.next()){
                monads = new Monads();
                monads.setM_id(rs.getInt("m_id"));
                monads.setM_uid(rs.getInt("m_uid"));
                monads.setM_goods(rs.getInt("m_goods"));
                monads.setM_goodinfo(rs.getString("m_goodsinfo"));
                monads.setM_order_datetime(rs.getTimestamp("m_order_datetime").toLocalDateTime());
                monads.setM_time_at(rs.getTimestamp("m_time_at").toLocalDateTime());
                monads.setM_address_from(rs.getString("m_address_from"));
                monads.setM_address_to(rs.getString("m_address_to"));
                monads.setM_time_from(rs.getTimestamp("m_time_from").toLocalDateTime());
                monads.setM_time_to(rs.getTimestamp("m_time_to").toLocalDateTime());
                monads.setM_failure_time(rs.getTimestamp("m_failure_time").toLocalDateTime());
                monads.setM_status(rs.getInt("m_status"));
                list.add(monads);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return list;
    }

    /**
     * 根据起手id查询他的所有捎单对应的订单信息，并按time_at排序
     * @param uid
     * @return
     * TODO------结合shao表去做
     */
    @Override
    public List<Monads> queryInfoByMonadsid(int uid) {
        ResultSet rs = util.query("SELECT * FROM monads WHERE m_id IN  (SELECT s_monads_id FROM shaomonads WHERE s_user_id=?)",uid);
        Monads monads;
        List<Monads> list = new ArrayList<>();
        try {
            while (rs.next()){
                monads = new Monads();
                monads.setM_id(rs.getInt("m_id"));
                monads.setM_uid(rs.getInt("m_uid"));
                monads.setM_goods(rs.getInt("m_goods"));
                monads.setM_goodinfo(rs.getString("m_goodsinfo"));
                monads.setM_order_datetime(rs.getTimestamp("m_order_datetime").toLocalDateTime());
                monads.setM_time_at(rs.getTimestamp("m_time_at").toLocalDateTime());
                monads.setM_address_from(rs.getString("m_address_from"));
                monads.setM_address_to(rs.getString("m_address_to"));
                monads.setM_time_from(rs.getTimestamp("m_time_from").toLocalDateTime());
                monads.setM_time_to(rs.getTimestamp("m_time_to").toLocalDateTime());
                monads.setM_failure_time(rs.getTimestamp("m_failure_time").toLocalDateTime());
                monads.setM_status(rs.getInt("m_status"));
                list.add(monads);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return list;
    }

    @Override
    public boolean updateStatusById(int id, int status) {
        int i = util.update("UPDATE monads SET m_status=? WHERE m_id=?",status,id);
        return i>0;
    }

    @Override
    public boolean deleteById(int id) {
        int i = util.update("DELETE FROM monads WHERE m_id=?",id);
        return i>0;
    }

    /**
     * 根据订单id查询状态
     * @param uid
     * @return
     */
    @Override
    public Monads queryByidUpdateStatus(int uid) {
        ResultSet rs = util.query("SELECT m_status FROM monads WHERE m_id=?",uid);
        Monads monads =null;
        try {
            if (rs.next()){
                monads=new Monads();
                monads.setM_status(rs.getInt("m_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return monads;
    }
}
