package org.gitqh.nba.web.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.constants.enums.DbModeEnum;
import org.gitqh.nba.service.CrawlerService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by quhan on 2017/7/4.
 */
@RestController
@RequestMapping(value = "/api")
@Api("Crawler Manager Class")
@Validated
public class CrawlerController {


    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam @Range(min = 1, max = 9, message = "i只能为1-9") int i) {
        return "that is a test" + i;
    }

    @RequestMapping(value = "/{crawlerName}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "启停爬虫", notes = "输入crawlerName,state,mode")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "提示信息")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "crawlerName", value = "爬虫名称", required = true, dataType = "string", defaultValue = ""),
            @ApiImplicitParam(name = "state", value = "start or stop", required = true, dataType = "string", defaultValue = ""),
            @ApiImplicitParam(name = "mode", value = "insert or update", required = true, dataType = "string", defaultValue = "")
    })
    public String startCrawler(@PathVariable String crawlerName, @RequestParam(value = "state") String state,
                               @RequestParam(value = "mode") String mode) {
        String res;
        if (StringUtils.isBlank(crawlerName)) {
            res = "crawlerName is not null!";
        } else if (StringUtils.isBlank(state)) {
            res = "state is not null";
        } else {
            switch (state.toLowerCase()) {
                case "start":
                    if (crawlerService.startCrawler(crawlerName, DbModeEnum.fromString(mode.toUpperCase()))) {
                        res = String.format("%s started success", crawlerName);
                    } else {
                        res = String.format("%s started failed", crawlerName);
                    }
                    break;
                case "stop":
                    if (crawlerService.stopCrawler(crawlerName)) {
                        res = String.format("%s stopped success", crawlerName);
                    } else {
                        res = String.format("%s stopped failed", crawlerName);
                    }
                    break;
                default:
                    res = "state should be start or stop";
                    break;
            }
        }
        return res;
    }

    @RequestMapping(value = "/{crawlerName}/test", method = RequestMethod.GET)
    @ResponseBody
    public void testCrawler(@PathVariable String crawlerName, @RequestParam String url) {
        crawlerService.testCrawler(crawlerName, url);
    }

    @RequestMapping(value = "/{crawlerName}/status", method = RequestMethod.GET)
    @ResponseBody
    public void getStateOfCrawler(@PathVariable String crawlerName) {

    }
}
