package com.musiconline.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class ADs implements Serializable, RowMapper {
    @Serial
    private static final long serialVersionUID = 7139746839732769589L;

    //广告id
    private Long id;
    //用户id
    private Long userId;
    //订单日期
    private Date orderDate;
    //订单状态
    private Integer orderStatus;
    //总共价格
    private double totalPrice;
    //图片url
    private String image;

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ADs ad = new ADs();
        ad.setId(rs.getLong("id"));
        ad.setUserId(rs.getLong("user_id"));
        ad.setOrderDate(rs.getDate("order_date"));
        ad.setOrderStatus(rs.getInt("order_status"));
        ad.setTotalPrice(rs.getDouble("total_price"));
        ad.setImage(rs.getString("image"));
        return ad;
    }

    public Object[] getObject(){
        Object[] obj = new Object[6];
        obj[0] = this.id;
        obj[1] = this.userId;
        obj[2] = this.orderDate;
        obj[3] = this.orderStatus;
        obj[4] = this.totalPrice;
        obj[5] = this.image;
        return obj;
    }
}
