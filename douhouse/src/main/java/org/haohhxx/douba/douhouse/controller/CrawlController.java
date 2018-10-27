package org.haohhxx.douba.douhouse.controller;

import org.haohhxx.douba.douhouse.crawl.CrawlDouGroup;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.service.HouseService;
import org.haohhxx.douba.douhouse.util.AMapPointofInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author zhenyuan_hao@163.com
 *
 *  http://cron.qqe2.com/
 */
@Component
public class CrawlController {

    private final CrawlDouGroup crawlDouGroup = new CrawlDouGroup();
    private final AMapPointofInterest aMapPointofInterest = new AMapPointofInterest();
    private final HouseService service;

    @Autowired
    public CrawlController(HouseService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 53/30 * * * ?")
    public void pullDataScheduled(){
        String groupId="shanghaizufang";
        int groupTotal = 125;
        int groupStep = 25;
        for (int i = 0; i < groupTotal; i+=groupStep) {
            try {
                Thread.sleep(1000);
                String houseIndexPage = crawlDouGroup.getHouseIndex(groupId,""+i);
                List<HouseMess> hous = crawlDouGroup.parserIndexPage(houseIndexPage);
                for(HouseMess houseMess:hous){
                    HouseMess houseMess1 = service.select(houseMess.getId());
                    if(houseMess1 == null){
                        houseMess = aMapPointofInterest.setPositioning(houseMess);
                        service.insert(houseMess);
                    }else {
                        service.update(houseMess);
                    }
                }
            } catch (IOException | ParseException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
