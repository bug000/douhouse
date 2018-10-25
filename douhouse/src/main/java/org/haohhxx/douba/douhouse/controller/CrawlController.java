package org.haohhxx.douba.douhouse.controller;

import org.haohhxx.douba.douhouse.crawl.CrawlDouGroup;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author zhenyuan_hao@163.com
 */
@Component
public class CrawlController {

    private final CrawlDouGroup crawlDouGroup = new CrawlDouGroup();
    private final HouseService service;

    @Autowired
    public CrawlController(HouseService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void pullDataScheduled(){
        String groupId="shanghaizufang";
        int groupTotal = 125;
        int groupStep = 25;
        for (int i = 0; i < groupTotal; i+=groupStep) {
            try {
                String houseIndexPage = crawlDouGroup.getHouseIndex(groupId,""+i);
                List<HouseMess> hous = crawlDouGroup.parserIndexPage(houseIndexPage);
                for(HouseMess houseMess:hous){
                    HouseMess houseMess1 = service.select(houseMess.getId());
                    if(houseMess1 == null){
                        service.insert(houseMess);
                    }else {
                        service.update(houseMess);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
