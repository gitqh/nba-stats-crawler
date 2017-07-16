package org.gitqh.nba.web.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.gitqh.nba.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by quhan on 2017/7/6.
 */
@RestController
@RequestMapping(value = "/db")
@Api("Database Manager Class")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/{tableName}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "清空表数据", notes = "输入tableName")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "提示信息")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名称", required = true, dataType = "string", defaultValue = "")
    })
    public String truncateTable(@PathVariable String tableName) {
        String res;
        if (StringUtils.isBlank(tableName)) {
            res = "param tableName is not null";
        } else {
            databaseService.truncateTable(tableName);
            res = String.format("%s truncate success", tableName);
        }
        return res;
    }
}
