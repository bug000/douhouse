package org.haohhxx.douba.douhouse.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author zhenyuan_hao@163.com
 * todo 未实现方法
 */
@RestController
@EnableAutoConfiguration
public class MainPlaceController {

    @RequestMapping(value = "/api/v1/basic/parser/place", method = RequestMethod.POST)
    @ResponseBody
    private void segText(@RequestParam("file") Map ds) {
        return;
    }


    @GetMapping("/")
    public String homePage(HttpServletRequest request, HttpServletResponse response){
        return "/index";
    }

    @RequestMapping("/static")
    public String navigatorToStatic() {
        return "redirect:/gaode.html";
    }


}
