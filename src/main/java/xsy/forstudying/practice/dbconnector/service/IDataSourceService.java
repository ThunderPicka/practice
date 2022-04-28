package xsy.forstudying.practice.dbconnector.service;

import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;

import java.util.List;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-27 10:18
 **/
public interface IDataSourceService {
    DataSourceInfo getDataSourceInfo(Long id);
    String getSql(Long id);
    List<Map<String, Object>> getResult(Long id) throws Exception;

    List<DataSourceInfo> queryAll();
}
