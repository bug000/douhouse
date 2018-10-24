package org.haohhxx.douba.douhouse.wmg;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * @author zhenyuan_hao@163.com
 */
@Component("DouPlacePipeline")
public class DouPlacePipeline implements PageModelPipeline<HoseMessageBean> {

    @Resource
    private HouseMessDAO houseMessDAO;

    @Override
    public void process(HoseMessageBean hoseMessageBean, Task task) {
        houseMessDAO.save(hoseMessageBean);
    }
}
