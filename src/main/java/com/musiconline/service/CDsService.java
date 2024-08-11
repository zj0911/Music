package com.musiconline.service;

import com.musiconline.dao.CDsDao;
import com.musiconline.model.CDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CDsService {
    @Autowired
    private CDsDao cdDao;

    //添加专辑信息
    public boolean insertCD(CDs cd){
        return cdDao.insertCD(cd);
    }

    //删除专辑信息
    public boolean deleteCD(Long id){
        return cdDao.deleteCD(id);
    }

    //编辑专辑信息
    public boolean updateCD(CDs cd) {
        return cdDao.updateCD(cd);
    }

    //根据id搜寻信息
    public CDs searchById(Long id){
        return cdDao.searchById(id);
    }

    //根据专辑,分类，艺术家搜寻信息
    public List<CDs> searchCD(String keyword){
        return cdDao.searchCD(keyword);
    }

    //展示所有信息
    public List<CDs> searchAll(){
        return cdDao.searchAll();
    }
}
