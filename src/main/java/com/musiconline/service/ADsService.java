package com.musiconline.service;

import com.musiconline.dao.ADsDao;
import com.musiconline.model.ADs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ADsService {
    @Autowired
    private ADsDao adDao;

    //插入广告
    public boolean insertAD(ADs ad){
        return adDao.insertAD(ad);
    }

    //删除广告
    public boolean deleteAD(Long id){
        return adDao.deleteAD(id);
    }

    //编辑广告信息
    public boolean updateAD(ADs ad){
        return adDao.updateAD(ad);
    }

    //根据id搜索
    public ADs searchById(Long id){
        return adDao.searchById(id);
    }

    //查询所有
    public List<ADs> searchAll(){
        return adDao.searchAll();
    }
}
