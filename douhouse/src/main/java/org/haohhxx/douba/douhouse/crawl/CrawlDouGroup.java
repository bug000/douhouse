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
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时访问豆瓣小组静态页面。
 * 不使用框架获取数据。
 */
public class CrawlDouGroup {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private String getHouseIndex(String groupId,String startPage) throws IOException {
        String baseUrl = "https://www.douban.com/group/%s/discussion?start=%d";
        String url = String.format(baseUrl, groupId, startPage);
        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader(entity);
        HttpResponse resp = httpclient.execute(httpGet);
        String respContentHtml = EntityUtils.toString(resp.getEntity(),"UTF-8");
        return respContentHtml;
    }

    private List<HouseMess> parserIndexPage(String respContentHtml){
        List<HouseMess> hous = new ArrayList<>();
        Document document = Jsoup.parse(respContentHtml);
        int start = 2;
        int stop = 26;
        for (int i = start; i <=stop ; i++) {
//            todo 修改xpath
            String id = Xsoup.compile("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a").evaluate(document).get();
            String title = Xsoup.compile("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a").evaluate(document).get();
            String author = Xsoup.compile("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/a").evaluate(document).get();
            String replyTime = Xsoup.compile("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[2]/td[4]").evaluate(document).get();
            String replyNub = Xsoup.compile("//*[@id=\"content\"]/div/div[1]/div[2]/table/tbody/tr[2]/td[3]").evaluate(document).get();

            String place = TitlePlaceParser.find(title);

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
