package com.musiconline.service;

import cn.hutool.crypto.digest.MD5;
import com.musiconline.dao.UsersDao;
import com.musiconline.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    //注册用户信息
    public boolean insert(Users user){
        return usersDao.insert(user);
    }

    //用户登录
    public Users login(String email, String password) {
        try {
            Users checkUser = usersDao.queryUser(email);
            String pwd = MD5.create().digestHex16(password.getBytes());
            if (pwd.equals(checkUser.getPassword())) {
                return checkUser;
            }
            return null;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //删除用户
    public boolean delete(Long id){
        return usersDao.delete(id);
    }

    //更新密码
    public boolean update(String email, String password){
        return usersDao.update(email, password);
    }

    //查询所有
    public Users queryUser(String email){
        return usersDao.queryUser(email);
    }
}
