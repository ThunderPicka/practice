package xsy.forstudying.practice.dbconnector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xsy.forstudying.practice.dbconnector.model.DataSourceConfiguration;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.service.IDataSourceService;

import java.util.List;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-25 11:42
 **/

@RestController
@RequestMapping("/db")
public class DataSourceController {

    @Autowired
    IDataSourceService dataSourceService;

    @GetMapping("/connect")
    public List<Map<String, Object>> dbConnector(@RequestParam Long id) throws Exception {
        List<Map<String, Object>> result = dataSourceService.getResult(id);
        return result;
    }

    @GetMapping("/test")
    public List<DataSourceInfo> queryAllDataSourceInfo() {
        return dataSourceService.queryAll();
    }

    @PostMapping("/configuration/add")
    public String addConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        return null;
    }

    @PostMapping("/configuration/edit")
    public String editConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        return null;
    }

    @PostMapping("/sql/add")
    public String addSql(){
        return null;
    }

    @PostMapping("/sql/edit")
    public String editSql(){
        return null;
    }
}
