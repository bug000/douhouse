package org.haohhxx.douba.douhouse.controller;


import org.haohhxx.douba.douhouse.model.HouseMess;
import org.haohhxx.douba.douhouse.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zhenyuan_hao@163.com
 */
@RestController
@EnableAutoConfiguration
public class MainPlaceController {
    private final Logger logger = LoggerFactory.getLogger(MainPlaceController.class);
    private final HouseService service;

    @Autowired
    public MainPlaceController(HouseService service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/v1/basic/place", method = RequestMethod.POST)
    @ResponseBody
    private List<HouseMess> getPlaces(@RequestBody Map ds) {
        String request_ip = (String) ds.getOrDefault("request_ip","0.0.0.0");
        logger.info(request_ip);
        Date currentda = new Date(System.currentTimeMillis()-(24*60*60*1000));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String curd = sdf.format(currentda);
        List<HouseMess> allll = service.selectAllLimitTime(curd);
        return allll;
    }




//    @GetMapping("/")
//    public String homePage(HttpServletRequest request, HttpServletResponse response){
//        return "/index";
//    }
//
//    @RequestMapping("/static")
//    public String navigatorToStatic() {
//        return "redirect:/gaode.html";
//    }


}
