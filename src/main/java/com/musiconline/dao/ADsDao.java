package com.musiconline.dao;

import cn.hutool.core.util.IdUtil;
import com.musiconline.model.ADs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ADsDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //插入广告
    public boolean insertAD(ADs ad){
        Long id = IdUtil.getSnowflake(1l,1l).nextId();
        ad.setId(id);
        String sql = "insert into ad(id, user_id, order_date, order_status, total_price, image) values (?,?,?,?,?,?);";
        int count = jdbcTemplate.update(sql, ad.getObject());
        return count == 1;
    }

    //删除广告
    public boolean deleteAD(Long id){
        String sql = "update ad set order_status = 0 where id = ?";
        int count = jdbcTemplate.update(sql, id);
        return count == 1;
    }

    //搜寻广告根据id
    public ADs searchById(Long id){
        String sql = "select * from ad where id = ?";
        Object[] obj = new Object[1];
        obj[0] = id;
        ADs ad = (ADs) jdbcTemplate.queryForObject(sql, new ADs(), id);
        return ad;
    }

    //编辑广告信息
    public boolean updateAD(ADs ad){
        ADs originalAD = searchById(ad.getId());
        if(ad.getOrderDate() == null){
            ad.setOrderDate(originalAD.getOrderDate());
        }
        if(ad.getTotalPrice() == 0.0){
            ad.setTotalPrice(originalAD.getTotalPrice());
        }
        if(ad.getImage() == null){
            ad.setImage(originalAD.getImage());
        }
        Object[] obj = new Object[]{
                ad.getOrderDate(),
                ad.getTotalPrice(),
                ad.getImage(),
                ad.getId()
        };
        String sql = "update ad set order_date = ?, total_price = ?, image = ? where id = ?";
        int count = jdbcTemplate.update(sql, obj);
        return count == 1;
    }

    //查询所有
    public List<ADs> searchAll(){
        String sql = "select * from ad";
        List<ADs> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ADs.class));
        return list;
    }
}
