package org.haohhxx.douba.douhouse.service;

import org.haohhxx.douba.douhouse.mapper.HouseMapper;
import org.haohhxx.douba.douhouse.model.HouseMess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenyuan_hao@163.com
 */
@Service
public class HouseService {

    private final HouseMapper dao;

    @Autowired
    public HouseService(HouseMapper dao) {
        this.dao = dao;
    }

    public boolean insert(HouseMess model) {
        return dao.insert(model) > 0;
    }

    public HouseMess select(String id) {
        return dao.select(id);
    }

    public List<HouseMess> selectAll() {
        return dao.selectAll();
    }

    public List<HouseMess> selectAllLimitTime(String timeLinit) {
        return dao.selectAllLimitTime(timeLinit);
    }

    public boolean updateTitle(HouseMess model) {
        return dao.updateTitle(model) > 0;
    }

    public boolean update(HouseMess model) {
        return dao.update(model) > 0;
    }

    public boolean delete(String id) {
        return dao.delete(id) > 0;
    }


}
