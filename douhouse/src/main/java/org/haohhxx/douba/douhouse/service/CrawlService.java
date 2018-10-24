package org.haohhxx.douba.douhouse.service;

import org.haohhxx.douba.douhouse.crawl.CrawlDouGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CrawlService {

        @Autowired
        CrawlDouGroup crawlDouGroup;

        /**
         * 定时
         * 翻页
         */
        @Scheduled(cron = "0 0/2 * * * ?")
        public void groupx(){
            String groupId="";
            String startPage="";
            for (int i = 0; i < 125; i+=25) {
                try {
                    crawlDouGroup.getHouseIndex(groupId,startPage+i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        /**
         * 定时
         * 翻页
         */
        @Scheduled(cron = "0 0/2 * * * ?")
        public void groupx2(){
            String groupId="";
            String startPage="";
            for (int i = 0; i < 125; i+=25) {
                try {
                    crawlDouGroup.getHouseIndex(groupId,startPage+i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}
