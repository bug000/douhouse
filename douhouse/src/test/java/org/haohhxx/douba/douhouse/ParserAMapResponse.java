package org.haohhxx.douba.douhouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ParserAMapResponse {

    @Test
    public void ptest() throws IOException {
        String resp = "{\"status\":\"1\",\"count\":\"896\",\"info\":\"OK\",\"infocode\":\"10000\",\"suggestion\":{\"keywords\":[],\"cities\":[]},\"pois\":[{\"id\":\"BV10032803\",\"parent\":[],\"name\":\"泗泾(地铁站)\",\"tag\":[],\"type\":\"交通设施服务;地铁站;地铁站\",\"typecode\":\"150500\",\"biz_type\":[],\"address\":\"9号线\",\"location\":\"121.260247,31.118272\",\"tel\":[],\"postcode\":[],\"website\":[],\"email\":[],\"pcode\":\"310000\",\"pname\":\"上海市\",\"citycode\":\"021\",\"cityname\":\"上海市\",\"adcode\":\"310117\",\"adname\":\"松江区\",\"importance\":[],\"shopid\":[],\"shopinfo\":\"2\",\"poiweight\":[],\"gridcode\":\"4621524002\",\"distance\":[],\"navi_poiid\":[],\"entr_location\":[],\"business_area\":\"泗泾\",\"exit_location\":[],\"match\":\"0\",\"recommend\":\"0\",\"timestamp\":[],\"alias\":[],\"indoor_map\":\"0\",\"indoor_data\":{\"cpid\":[],\"floor\":[],\"truefloor\":[],\"cmsid\":[]},\"groupbuy_num\":\"0\",\"discount_num\":\"0\",\"biz_ext\":{\"rating\":[],\"cost\":[]},\"event\":[],\"children\":[{\"id\":\" BX10003397 \",\"name\":\" 泗泾地铁站4号出口 \",\"sname\":\" 4号出口 \",\"location\":\"121.259996,31.118460\",\"address\":\" 9号线 \",\"distance\":\" 0 \",\"subtype\":[]},{\"id\":\" BX10016905 \",\"name\":\" 泗泾地铁站1号口 \",\"sname\":\" 1号口 \",\"location\":\"121.260610,31.118506\",\"address\":\" 9号线 \",\"distance\":\" 0 \",\"subtype\":[]},{\"id\":\" BX10016904 \",\"name\":\" 泗泾地铁站3号出口 \",\"sname\":\" 3号出口 \",\"location\":\"121.260018,31.118188\",\"address\":\" 9号线 \",\"distance\":\" 0 \",\"subtype\":[]},{\"id\":\" BX10016903 \",\"name\":\" 泗泾地铁站2号口 \",\"sname\":\" 2号口 \",\"location\":\"121.260637,31.118245\",\"address\":\" 9号线 \",\"distance\":\" 0 \",\"subtype\":[]}],\"photos\":[]}]}\n";
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.
        Map rspMap = objectMapper.readValue(resp,Map.class);
        List poiList = (List) rspMap.get("pois");
        if(poiList.size()>0){
            Map locMap = (Map)poiList.get(0);
            System.out.println(locMap.get("location"));
        }
    }


}
