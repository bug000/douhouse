package org.haohhxx.douba.douhouse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.haohhxx.douba.douhouse.model.HouseMess;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class AMapPointofInterest {

    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private ObjectMapper objectMapper = new ObjectMapper();

    public HouseMess setPositioning(HouseMess houseMess) {

        String queryStr = houseMess.getPlaceStr();
        String types = "地名地址信息|交通设施服务|商务住宅";
        String city = "上海";
        String rootPath = "https://restapi.amap.com/v3/place/text?";
        String baseUrl = "key=398ead94587b740036e874a2ba6f4252&keywords=%s&types=%s&city=%s&children=1&offset=1&page=1&extensions=all";

        try {
            queryStr =  URLEncoder.encode(queryStr,"UTF-8");
            types =  URLEncoder.encode(types,"UTF-8");
            city =  URLEncoder.encode(city,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        baseUrl = rootPath + String.format(baseUrl,queryStr,types,city);

        HttpGet httpGet = new HttpGet(baseUrl);
        httpGet.setHeader("Vary","Accept-Encoding");
        httpGet.setHeader("Content-Encoding","gzip");

        String respString = null;
        try {
            HttpResponse resp = httpclient.execute(httpGet);
            respString = EntityUtils.toString(resp.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map rspMap = null;
        try {
            rspMap = objectMapper.readValue(respString,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List poiList = (List) rspMap.get("pois");
        if(poiList.size()>0){
            Map locMap = (Map)poiList.get(0);
            String locationStr = (String) locMap.get("location");
            String ls[] = locationStr.split(",");
            houseMess.setLatitude(ls[1]);
            houseMess.setLongitude(ls[0]);
        }
        return houseMess;
    }


}
