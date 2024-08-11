package com.musiconline.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class CDs implements Serializable, RowMapper<CDs> {
    @Serial
    private static final long serialVersionUID = -949023037845133915L;

    //唱片id
    private Long id;
    //专辑名称
    private String album;
    //专辑类别
    private String genre;
    //艺术家
    private String artist;
    //发行日期
    private Date releaseDate;
    //唱片价格
    private String price;
    //唱片描述
    private String description;
    //图片的url
    private String image;
    //唱片状态
    private Integer status;

    @Override
    public CDs mapRow(ResultSet rs, int rowNum) throws SQLException {
        CDs cd = new CDs();
        cd.setId(rs.getLong("id"));
        cd.setAlbum(rs.getString("album"));
        cd.setGenre(rs.getString("genre"));
        cd.setArtist(rs.getString("artist"));
        cd.setReleaseDate(rs.getDate("release_date"));
        cd.setPrice(rs.getString("price"));
        cd.setDescription(rs.getString("description"));
        cd.setImage(rs.getString("image"));
        cd.setStatus(rs.getInt("status"));
        return cd;
    }

    public Object[] getObjects(){
        Object[] objects = new Object[9];
        objects[0] = this.id;
        objects[1] = this.album;
        objects[2] = this.genre;
        objects[3] = this.artist;
        objects[4] = this.releaseDate;
        objects[5] = this.price;
        objects[6] = this.description;
        objects[7] = this.image;
        objects[8] = this.status;
        return objects;
    }
}
