package org.haohhxx.douba.douhouse.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.haohhxx.douba.douhouse.model.HouseMess;

/**
 * @author zhenyuan_hao@163.com
 * http://lbsyun.baidu.com/jsdemo.htm#c2_4
 *
 */
public class BaiduPositioning {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static HouseMess setPositioning(HouseMess houseMess){


        return houseMess;
    }


}
