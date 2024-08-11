package com.musiconline.controller;

import com.musiconline.model.CDs;
import com.musiconline.model.ResponseData;
import com.musiconline.service.CDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/cd")
@CrossOrigin
public class CDsController {
    @Autowired
    private CDsService cdService;

    //添加专辑信息
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseData insertCD(CDs cd){
        Integer status = cd.getStatus();
        if(null == status){
            cd.setStatus(1);
        }
        boolean isSuccess = cdService.insertCD(cd);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //删除专辑信息
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseData deleteCD(Long id){
        boolean isSuccess = cdService.deleteCD(id);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //编辑专辑信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData updateCD(CDs cd){
        boolean isSuccess = cdService.updateCD(cd);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //根据id搜寻信息
    @RequestMapping(value = "/searchById", method = RequestMethod.GET)
    public ResponseData searchById(Long id){
        CDs cd = cdService.searchById(id);
        ResponseData responseData = new ResponseData(200, true, "success", cd);
        return responseData;
    }

    //根据专辑,分类，艺术家搜寻信息
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseData searchCD(String keyword){
        List<CDs> list = cdService.searchCD(keyword);
        ResponseData responseData = new ResponseData(200, true, "success", list);
        return responseData;
    }

    //展示所有
    @RequestMapping(value = "/searchAll", method = RequestMethod.GET)
    public ResponseData searchAll(){
        List<CDs> list = cdService.searchAll();
        ResponseData responseData = new ResponseData(200, true, "success", list);
        return responseData;
    }
}
