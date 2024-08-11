package com.musiconline.controller;

import com.musiconline.model.ResponseData;
import com.musiconline.model.Users;
import com.musiconline.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    //注册用户信息
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData insert(Users user){
        //获取用户类型
        Integer userType = user.getUserType();
        Integer userStatus = user.getUserStatus();
        if(null == userType){
            user.setUserType(1);
        }
        if(null == userStatus){
            user.setUserStatus(1);
        }
        boolean isSuccess = usersService.insert(user);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return responseData;
    }

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(String email, String password) {
        Users user = usersService.login(email, password);
        ResponseData responseData = null;
        if (user != null) {
            responseData = new ResponseData(200, true, "success", user);
        } else {
            responseData = new ResponseData(404, false, "failed", "");
        }
        return responseData;
    }

    //删除用户
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseData delete(Long id){
        boolean isSuccess = usersService.delete(id);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return  responseData;
    }

    //更新密码
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(String email, String password){
        boolean isSuccess = usersService.update(email, password);
        ResponseData responseData = new ResponseData(200, isSuccess, "success", "");
        return  responseData;
    }

    //查询账户
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public ResponseData queryUser(String email){
        Users user= usersService.queryUser(email);
        ResponseData responseData = new ResponseData(200, true, "success", user);
        return responseData;
    }
}
