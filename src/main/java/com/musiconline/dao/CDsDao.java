package com.musiconline.dao;

import cn.hutool.core.util.IdUtil;
import com.musiconline.model.CDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class CDsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加专辑信息
    public boolean insertCD(CDs cd) {
        Long id = IdUtil.getSnowflake(1l,1l).nextId();
        cd.setId(id);
        String sql = "insert into cd(id, album, genre, artist,release_date, price, description, image, status) values (?,?,?,?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sql, cd.getObjects());
        return count == 1; //当count为1，返回为true
    }

    //删除专辑信息
    public boolean deleteCD(Long id){
        String sql = "update cd set status = 0 where id = ?";
        int count = jdbcTemplate.update(sql, id);
        return count == 1;
    }

    //编辑专辑信息
    public boolean updateCD(CDs cd){
        // 查询数据库获取原始数据
        CDs originalCD = searchById(cd.getId());
        // 检查每个字段，如果为 null，则使用原始数据填充
        if (cd.getAlbum() == null) {
            cd.setAlbum(originalCD.getAlbum());
        }
        if (cd.getGenre() == null) {
            cd.setGenre(originalCD.getGenre());
        }
        if (cd.getArtist() == null) {
            cd.setArtist(originalCD.getArtist());
        }
        if (cd.getReleaseDate() == null) {
            cd.setReleaseDate(originalCD.getReleaseDate());
        }
        if (cd.getPrice() == null) {
            cd.setPrice(originalCD.getPrice());
        }
        if (cd.getDescription() == null) {
            cd.setDescription(originalCD.getDescription());
        }
        if (cd.getImage() == null) {
            cd.setImage(originalCD.getImage());
        }
        Object[] objects = new Object[]{
                cd.getAlbum(),
                cd.getGenre(),
                cd.getArtist(),
                cd.getReleaseDate(),
                cd.getPrice(),
                cd.getDescription(),
                cd.getImage(),
                cd.getId()
        };
        String sql = "update cd set album = ?, genre = ?, artist = ?, release_date=?, price = ?, description = ?," +
                "image = ? where id = ?";
        int count = jdbcTemplate.update(sql, objects);
        return count == 1;
    }

    //根据id查询
    public CDs searchById(Long id){
        try {
            String sql = "select * from cd where id = ? and status = 1";
            Object[] objects = new Object[1];
            objects[0] = id;
            CDs cd = jdbcTemplate.queryForObject(sql, new CDs(), id);
            return cd;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    //根据专辑,分类，艺术家搜寻信息
    public List<CDs> searchCD(String keyword){
        String sql = "select * from cd where (artist like concat('%', ?, '%') or genre like concat('%', ?, '%') or album like concat('%', ?, '%')) and status = 1";
        List<CDs> list = jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"}, new BeanPropertyRowMapper<>(CDs.class));
        return list;
    }

    //展示所有信息
    public List<CDs> searchAll(){
        String sql = "select * from cd where status = 1";
        List<CDs> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CDs.class));
        return list;
    }
}
