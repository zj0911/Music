package com.musiconline.model;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Users implements Serializable, RowMapper<Users> {

    @Serial
    private static final long serialVersionUID = -5725120286244447042L;
    //用户id
    private Long id;
    //用户名称
    private String userName;
    //用户密码
    private String password;
    //邮箱
    private  String email;
    //收件地址
    private String address;
    //用户类型
    private Integer userType;
    //用户状态
    private Integer userStatus;


    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users user =  new Users();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setUserType(rs.getInt("user_type"));
        user.setUserStatus(rs.getInt("user_status"));
        return user;
    }

    public Object[] getObjects(){
        Object[] objects = new Object[7];
        objects[0] = this.id;
        objects[1] = this.userName;
        objects[2] = this.password;
        objects[3] = this.email;
        objects[4] = this.address;
        objects[5] = this.userType;
        objects[6] = this.userStatus;
        return objects;
    }
}




