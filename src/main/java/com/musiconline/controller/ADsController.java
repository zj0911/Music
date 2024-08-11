package com.musiconline.controller;

import cn.hutool.core.util.IdUtil;
import com.musiconline.model.ADs;
import com.musiconline.model.ResponseData;
import com.musiconline.service.ADsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ad")
@CrossOrigin
public class ADsController {
    @Autowired
    private ADsService adService;

    //插入广告
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseData insertAD(ADs ad){
        Integer status = ad.getOrderStatus();
        if(null == status){
            ad.setOrderStatus(1);
        }
        boolean isSuccess = adService.insertAD(ad);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //删除广告
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseData deleteAD(Long id){
        boolean isSuccess = adService.deleteAD(id);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //编辑广告信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData updateAD(ADs ad){
        boolean isSuccess = adService.updateAD(ad);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //根据id搜索
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseData searchAD(Long id){
        ADs ad = adService.searchById(id);
        ResponseData responseData = new ResponseData(200, true, "success", ad);
        return responseData;
    }

    //查询所有
    @RequestMapping(value = "/searchAll", method = RequestMethod.GET)
    public ResponseData searchAll(){
        List<ADs> list = adService.searchAll();
        ResponseData responseData = new ResponseData(200, true, "success", list);
        return responseData;
    }
}
