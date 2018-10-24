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

    public HouseMess select(int id) {
        return dao.select(id);
    }

    public List<HouseMess> selectAll() {
        return dao.selectAll();
    }

    public boolean updateValue(HouseMess model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }


}
