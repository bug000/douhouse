package org.haohhxx.douba.douhouse.crawl;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.util.BaiduPositioning;
import org.haohhxx.douba.douhouse.util.TitlePlaceParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时访问豆瓣小组静态页面。
 * 不使用框架获取数据。
 * @author zhenyuan_hao@163.com
 */
public class CrawlDouGroup {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String replyTimeFormat(String douDateStr) throws ParseException {
        int year = calendar.get(Calendar.YEAR);
//        Date date = sdf.parse(year+"-"+douDateStr);
//        return date.toString();
        return year+"-"+douDateStr;
    }

    public String getHouseIndex(String groupId,String startPage) throws IOException {
        String baseUrl = "https://www.douban.com/group/%s/discussion?start=%s";
        String url = String.format(baseUrl, groupId, startPage);
        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader(entity);
        HttpResponse resp = httpclient.execute(httpGet);
        String respContentHtml = EntityUtils.toString(resp.getEntity(),"UTF-8");
        return respContentHtml;
    }

    public List<HouseMess> parserIndexPage(String respContentHtml) throws ParseException {
        List<HouseMess> hous = new ArrayList<>();
        Document document = Jsoup.parse(respContentHtml);
        int start = 2;
        int stop = 26;
        for (int i = start; i <=stop ; i++) {
            String id = Xsoup.compile(String.format("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[%d]/td[1]/a/@href", i)).evaluate(document).get();
            String title = Xsoup.compile(String.format("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[%d]/td[1]/a/@title", i)).evaluate(document).get();
            String author = Xsoup.compile(String.format("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[%d]/td[2]/a/@href", i)).evaluate(document).get();
            String replyTime = Xsoup.compile(String.format("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[%d]/td[4]/text()", i)).evaluate(document).get();
            String replyNub = Xsoup.compile(String.format("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[%d]/td[3]/text()", i)).evaluate(document).get();

            String place = TitlePlaceParser.find(title);

            if("".equals(replyNub)){
                replyNub = "0";
            }
            replyTime = replyTimeFormat(replyTime);
            id = TitlePlaceParser.findStr("\\d{1,15}",id);
            author = TitlePlaceParser.findStr("\\d{1,15}",author);

            HouseMess houseMess = new HouseMess();
            houseMess.setAuthor(author);
            houseMess.setId(id);
            houseMess.setTitle(title);
            houseMess.setPlaceStr(place);
            houseMess.setLastReply(replyTime);
            houseMess.setReplyNub(replyNub);

            houseMess = BaiduPositioning.setPositioning(houseMess);
            hous.add(houseMess);
        }
        return hous;
    }



}
