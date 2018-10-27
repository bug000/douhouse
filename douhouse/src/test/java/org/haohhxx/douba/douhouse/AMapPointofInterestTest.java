package org.haohhxx.douba.douhouse;

import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.util.AMapPointofInterest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AMapPointofInterestTest {


    private AMapPointofInterest aMapPointofInterest;

    @Before
    public void init(){
        aMapPointofInterest = new AMapPointofInterest();
    }

    @Test
    public void poiTest() throws IOException {
        HouseMess houseMess = new HouseMess();
        aMapPointofInterest.setPositioning(houseMess);
    }


}
