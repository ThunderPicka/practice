package xsy.forstudying.practice.dbconnector.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import xsy.forstudying.practice.dbconnector.config.DataSourceConfig;
import xsy.forstudying.practice.dbconnector.mapper.MasterDataSourceMapper;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.service.IDataSourceService;

import java.util.List;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-25 16:16
 **/
@Service
@Slf4j
public class DataSourceServiceImpl implements IDataSourceService {

    @Autowired
    private MasterDataSourceMapper masterDataSourceMapper;

    public DataSourceInfo getDataSourceInfo(Long id) {
       return masterDataSourceMapper.queryById(id);
    }

    public JdbcTemplate connect2AnotherOne(DataSourceInfo dataSourceInfo) throws Exception {
        log.info("开始创建新的DataSource");
           return DataSourceConfig.connectDataSource(dataSourceInfo);
    }

    @Override
    public String getSql(Long id) {
        return masterDataSourceMapper.getSql(id);
    }

    @Override
    public List<Map<String, Object>> getResult(Long id) throws Exception {
        String sql=this.getSql(id);
        log.info("获取到指定sql="+sql);
        JdbcTemplate jdbcTemplate = this.connect2AnotherOne(this.getDataSourceInfo(id));
        log.info("从数据源已注入成功");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @Override
    public List<DataSourceInfo> queryAll() {
        return masterDataSourceMapper.queryAll();
    }


}
