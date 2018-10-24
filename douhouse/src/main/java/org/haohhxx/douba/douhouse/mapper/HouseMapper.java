package org.haohhxx.douba.douhouse.mapper;


import org.apache.ibatis.annotations.*;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhenyuan_hao@163.com
 */
@Mapper
@Component
public interface HouseMapper {

    @Insert("INSERT INTO house(title,replyNub,lastReply,author,placeStr,longitude,latitude,id) VALUES" +
            "(#{title}, #{replyNub}, #{lastReply}, #{author}, #{placeStr}, #{longitude}, #{latitude}, #{id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'house')", before = false, keyProperty = "id", resultType = int.class)
    int insert(HouseMess houseMess);

    @Select("SELECT * FROM house WHERE id=#{id}")
    HouseMess select(int id);

    @Select("SELECT * FROM house")
    List<HouseMess> selectAll();

    @Update("UPDATE house SET value=#{value} WHERE id=#{id}")
    int updateValue(HouseMess model);

    @Delete("DELETE FROM house WHERE id=#{id}")
    int delete(Integer id);

}
