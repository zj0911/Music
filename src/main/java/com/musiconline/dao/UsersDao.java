package com.musiconline.dao;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import com.musiconline.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;


@Repository
public class UsersDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //注册用户信息
    public boolean insert(Users user) {
        if (user.getPassword() == null) {
            return false;
        }
        Long id = IdUtil.getSnowflake(1l, 1l).nextId();
        String sql = "insert into users(id, user_name, password, email, address, user_type,user_status) values (?,?,?,?,?,?,?)";
        String password = user.getPassword();
        user.setPassword(MD5.create().digestHex16(password.getBytes()));
        user.setId(id);
        int count = jdbcTemplate.update(sql, user.getObjects());
        return count == 1; //当count为1，返回为true
    }

    //删除用户
    public boolean delete(Long id){
        String sql = "update users set user_status = 0 where id = ? ";
        int count = jdbcTemplate.update(sql, id);
        return count == 1;
    }

    //更新密码
    public boolean update(String email, String password){
        String sql = "update users set password = ? where email = ?";
        password = MD5.create().digestHex16(password.getBytes());
        int count = jdbcTemplate.update(sql, password, email);
        return count == 1;
    }

    //查询用户通过邮件
    public Users queryUser(String email){
        String sql = "select * from users where user_status = 1 and email = ?";
        Users user = jdbcTemplate.queryForObject(sql, new Users(), email);
        return user;
    }
}
