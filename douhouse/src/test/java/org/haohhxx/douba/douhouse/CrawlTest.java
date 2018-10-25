package org.haohhxx.douba.douhouse;

import org.haohhxx.douba.douhouse.crawl.CrawlDouGroup;
import org.haohhxx.douba.douhouse.mapper.HouseMapper;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.service.HouseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlTest {

    private final CrawlDouGroup crawlDouGroup = new CrawlDouGroup();

    @Autowired
    private  HouseService service;

    @Test
    public void contextLoads() throws IOException {
        String groupId = "shanghaizufang";
        String startPage = "0";
        String hi = crawlDouGroup.getHouseIndex(groupId, startPage);
        List<HouseMess> hms = crawlDouGroup.parserIndexPage(hi);

        // 记录数
        List<HouseMess> all = service.selectAll();
        int size = all.size();

        for(HouseMess hm:hms){
            HouseMess houseMess1 = service.select(hm.getId());
            if(houseMess1 == null){
                service.insert(hm);
            }else {
                service.update(hm);
            }
        }

    }


}
