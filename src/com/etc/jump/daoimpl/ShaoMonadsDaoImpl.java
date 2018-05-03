package com.etc.jump.daoimpl;

import com.etc.jump.dao.ShaoMonadsDao;
import com.etc.jump.entity.ShaoMonads;
import com.etc.jump.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShaoMonadsDaoImpl implements ShaoMonadsDao {
    JDBCUtil util = new JDBCUtil();

    /**
     * 添加一个捎送记录
     * @param shaoMonads
     * @return 返回是否添加成功
     */
    @Override
    public boolean addshao(ShaoMonads shaoMonads) {
        int i = util.update("INSERT INTO shaomonads(s_monads_id, s_user_id, s_order_datetime) VALUES (?,?,?)",
                shaoMonads.getS_monads_id(),
                shaoMonads.getS_user_id(),
                Timestamp.valueOf(shaoMonads.getS_order_datetime()));
        return i>0;
    }

    /**
     * 根据用户id和订单id查询捎单记录
     * @param uid
     * @param mid
     * @return
     */
    @Override
    public ShaoMonads infoByUidAId(int uid, int mid) {
        ResultSet rs = util.query("SELECT * FROM shaomonads WHERE s_user_id=? AND s_monads_id=?",uid,mid);
        ShaoMonads shaoMonads = null;
        try {
            if (rs.next()){
                shaoMonads = new ShaoMonads();
                shaoMonads.setS_id(rs.getInt("s_id"));
                shaoMonads.setS_user_id(rs.getInt("s_user_id"));
                shaoMonads.setS_monads_id(rs.getInt("s_monads_id"));
                shaoMonads.setS_order_datetime(rs.getTimestamp("s_order_datetime").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return shaoMonads;
    }

    @Override
    public List<ShaoMonads> infoByUid(int uid) {
        ResultSet rs = util.query("SELECT * FROM shaomonads WHERE s_user_id=?",uid);
        List<ShaoMonads> list = new ArrayList<>();
        ShaoMonads shaoMonads = null;
        try {
            if (rs.next()){
                shaoMonads = new ShaoMonads();
                shaoMonads.setS_id(rs.getInt("s_id"));
                shaoMonads.setS_user_id(rs.getInt("s_user_id"));
                shaoMonads.setS_monads_id(rs.getInt("s_monads_id"));
                shaoMonads.setS_order_datetime(rs.getTimestamp("s_order_datetime").toLocalDateTime());

                list.add(shaoMonads);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.closeAll();
        }
        return list;
    }


}
