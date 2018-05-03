package com.etc.jump.bizimpl;

import com.etc.jump.biz.MonadsBiz;
import com.etc.jump.dao.MonadsDao;
import com.etc.jump.daoimpl.MonadsDaoImpl;
import com.etc.jump.entity.Monads;

import java.util.List;

public class MonadsBizImpl implements MonadsBiz{
    MonadsDao dao = new MonadsDaoImpl();

    @Override
    public List<Monads> queryInfoByIdStatus(int uid) {
        if (uid<0){
            return null;
        }
        return dao.queryInfoByIdStatus(uid);
    }

    @Override
    public Monads queryMaxUndoneId() {
        return dao.queryMaxUndoneId();
    }

    @Override
    public List<Monads> queryNewUndone() {
        return dao.queryNewUndone();
    }

    @Override
    public List<Monads> queryAllUndone() {
        return dao.queryAllUndone();
    }

    @Override
    public boolean addOrd(Monads monads) {
        if (monads!=null){
            return dao.addOrd(monads);
        }
        return false;
    }

    @Override
    public boolean updateStatusById(int id, int status) {
        if (status<0||status>4||id<0){
            return false;
        }
        return dao.updateStatusById(id,status);
    }

    @Override
    public boolean deleteById(int id) {
        if (id<0){
            return false;
        }
        return dao.deleteById(id);
    }

    @Override
    public Monads queryInfoById(int id) {
        if (id<0){
            return null;
        }

        return dao.queryInfoById(id);
    }
}
