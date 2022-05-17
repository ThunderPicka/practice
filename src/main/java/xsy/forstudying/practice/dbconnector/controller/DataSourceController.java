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
     * @param dbId
     * @return
     * @throws Exception
     */
    @GetMapping("/connect-query")
    public List<Map<String, Object>> connectDbToQueryAll(@RequestParam Long dbId) throws Exception {
        List<Map<String, Object>> result = dataSourceService.connectDbToQueryAll(dbId);
        return result;
    }

    /**
     * 查询所有数据库配置信息
     * @return
     */
    @GetMapping("/datasource/query")
    public List<DataSourceInfo> queryAllDataSourceInfo() {
        return dataSourceService.queryAllDataSourceInfo();
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
    @PostMapping("/configuration/sql/add")
    public String addSql(){
        return null;
    }

    /**
     * 修改sql
     * @return
     */
    @PostMapping("/configuration/sql/edit")
    public String editSql(){
        return null;
    }

    /**
     * 测试插入语句,并返回插入结果
     */
    @GetMapping("/sql/execute-insert")
    public Integer executeInsertSQL(@RequestParam("sqlId") Long sqlId) throws Exception {
        return dataSourceService.executeInsertSQL(sqlId);
    }
    @GetMapping("/sql/execute-delete")
    public Integer executeDeleteSQL(@RequestParam("sqlId") Long sqlId) throws Exception {
        return dataSourceService.executeDeleteSQL(sqlId);
    }
    @GetMapping("/sql/execute-update")
    public Integer executeUpdateSQL(@RequestParam("sqlId") Long sqlId) throws Exception {
        return dataSourceService.executeUpdateSQL(sqlId);
    }
    @GetMapping("/sql/execute-query")
    public List<Map<String,Object>> executeQuerySQL(@RequestParam("sqlId") Long sqlId) throws Exception {
        return dataSourceService.executeQuerySQL(sqlId);
    }
}
