package xsy.forstudying.practice.dbconnector.service;

import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.model.SQLInfo;

import java.util.List;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-27 10:18
 **/
public interface IDataSourceService {
    /**
     * 根据Id查询数据库配置参数
     * @param id
     * @return
     */
    DataSourceInfo getDataSourceInfo(Long id);

    /**
     * 根据id查询sql
     *
     * @param id
     * @return
     */
    SQLInfo getSQLBySQLId(Long id);

    /**
     *返回所有的数据库配置信息
     * @return
     */
    List<DataSourceInfo> queryAllDataSourceInfo();

    /**
     * 执行insert
     *
     * @param id
     * @return
     * @throws Exception
     */
    Integer executeInsertSQL(Long id) throws Exception;

    /**
     * 执行delete
     *
     * @param id
     * @return
     */
    Integer executeDeleteSQL(Long id) throws Exception;

    /**
     * 执行update
     *
     * @param id
     * @return
     */
    Integer executeUpdateSQL(Long id) throws Exception;

    /**
     *执行 select
     * @param id
     * @return
     */
    List<Map<String, Object>> executeQuerySQL(Long id) throws Exception;

    /**
     * 连接数据库并执行绑定的select
     * @param dbId
     * @return
     */
    List<Map<String, Object>> connectDbToQueryAll(Long dbId) throws Exception;
}
