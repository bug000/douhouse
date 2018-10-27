package org.haohhxx.douba.douhouse.mapper;


import org.apache.ibatis.annotations.*;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zhenyuan_hao@163.com
 */
@Mapper
@Component
public interface HouseMapper {

    /**
     * 插入一条新租房信息
     * @param houseMess houseMess
     * @return
     */
    @Insert("INSERT INTO house(title,replyNub,lastReply,author,placeStr,longitude,latitude,id) VALUES" +
            "(#{title}, #{replyNub}, #{lastReply}, #{author}, #{placeStr}, #{longitude}, #{latitude}, #{id})")
//    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'house')", before = false, keyProperty = "id", resultType = int.class)
    int insert(HouseMess houseMess);

    @Update("UPDATE house SET title=#{title}" +
            ",replyNub=#{replyNub}" +
            ",lastReply=#{lastReply}" +
            ",author=#{author}" +
            ",placeStr=#{placeStr}" +
            ",longitude=#{longitude}" +
            ",latitude=#{latitude}" +
            "WHERE id=#{id}")
    int update(HouseMess model);

    @Select("SELECT * FROM house WHERE id=#{id}")
    HouseMess select(String id);

    @Select("SELECT * FROM house")
    List<HouseMess> selectAll();

    @Select("SELECT * FROM house WHERE lastReply >#{currentTime}")
    List<HouseMess> selectAllLimitTime(String currentTime);

    @Update("UPDATE house SET title=#{title} WHERE id=#{id}")
    int updateTitle(HouseMess model);

    @Delete("DELETE FROM house WHERE id=#{id}")
    int delete(String id);

}
