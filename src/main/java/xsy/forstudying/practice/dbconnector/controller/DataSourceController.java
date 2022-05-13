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

    /**
     * 根据数据库id查询配置信息连接数据库并执行与之匹配的sql
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/connect")
    public List<Map<String, Object>> dbConnector(@RequestParam Long id) throws Exception {
        List<Map<String, Object>> result = dataSourceService.getResult(id);
        return result;
    }

    /**
     * 查询所有数据库配置信息
     * @return
     */
    @GetMapping("/test/db/query")
    public List<DataSourceInfo> queryAllDataSourceInfo() {
        return dataSourceService.queryAll();
    }

    /**
     * 新增数据库连接配置
     * @param dataSourceConfiguration
     * @return
     */
    @PostMapping("/configuration/add")
    public String addConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        return null;
    }

    /**
     * 修改数据库配置信息
     * @param dataSourceConfiguration
     * @return
     */
    @PostMapping("/configuration/edit")
    public String editConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        return null;
    }

    /**
     * 新增sql
     * @return
     */
    @PostMapping("/sql/add")
    public String addSql(){
        return null;
    }

    /**
     * 修改sql
     * @return
     */
    @PostMapping("/sql/edit")
    public String editSql(){
        return null;
    }

    /**
     * 测试插入语句,并返回插入结果
     */
    @GetMapping("/test/insert")
    public List<Map<String,Object>> testInsert(@RequestParam("id") Long id) throws Exception {
        return dataSourceService.testInsert(id);
    }
}
