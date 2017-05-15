package travel.controller;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.domain.*;
import travel.service.TravelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chenjie Xu on 2017/5/9.
 */
@RestController
public class TravelController {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private TravelService travelService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/travel/create", method= RequestMethod.POST)
    public String create(@RequestBody Information info){
        return travelService.create(info);
    }

    //只返回Trip，不会返回票数信息
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/travel/retrieve", method= RequestMethod.POST)
    public Trip retrieve(@RequestBody Information2 info){
        return travelService.retrieve(info);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/travel/update", method= RequestMethod.POST)
    public String update(@RequestBody Information info){
        return travelService.update(info);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/travel/delete", method= RequestMethod.POST)
    public String delete(@RequestBody Information2 info){
        return travelService.delete(info);
    }

    //返回Trip以及剩余票数
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/travel/query", method= RequestMethod.POST)
    public TripResponse query(@RequestBody QueryInfo info){
        return travelService.query(info);
    }
}