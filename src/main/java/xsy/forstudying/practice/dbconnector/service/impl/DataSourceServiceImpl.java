package xsy.forstudying.practice.dbconnector.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xsy.forstudying.practice.dbconnector.config.DataSourceConfig;
import xsy.forstudying.practice.dbconnector.constant.SQLTypeConstant;
import xsy.forstudying.practice.dbconnector.mapper.MasterDataSourceMapper;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.model.SQLInfo;
import xsy.forstudying.practice.dbconnector.service.IDataSourceService;

import java.lang.reflect.Field;
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

    public DataSourceInfo getDataSourceInfo(Long dbId) {
        return masterDataSourceMapper.queryById(dbId);
    }

    public JdbcTemplate connect2AnotherOne(DataSourceInfo dataSourceInfo) throws Exception {
        log.info("开始创建新的DataSource");
        return DataSourceConfig.connectDataSource(dataSourceInfo);
    }

    public JdbcTemplate establishConnectionBySql(SQLInfo sqlInfo) throws Exception {
        DataSourceInfo dataSourceInfo = getDataSourceInfo(sqlInfo.getDbId());
        log.info("获取到数据源dataSource=" + dataSourceInfo.toString());
        JdbcTemplate jdbcTemplate = connect2AnotherOne(dataSourceInfo);
        log.info("从数据源已注入成功");
        return jdbcTemplate;
    }

    @Override
    public SQLInfo getSQLBySQLId(Long sqlId) {
        return masterDataSourceMapper.getSQLBySqlId(sqlId);
    }

    @Override
    public List<Map<String, Object>> executeQuerySQL(Long sqlId) throws Exception {
        SQLInfo sql = masterDataSourceMapper.getSQLBySqlId(sqlId);
        log.info("获取到sql=" + sql.getStatement());
        JdbcTemplate jdbcTemplate = establishConnectionBySql(sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.getStatement());
        return maps;
    }

    @Override
    public List<Map<String, Object>> connectDbToQueryAll(Long dbId) throws Exception {
        log.info("根据dbId={}查询数据源配置参数", dbId);
        DataSourceInfo dataSourceInfo = getDataSourceInfo(dbId);
        log.info("根据dbId={}查询数据源配置参数为DataSourceInfo={}", dbId, dataSourceInfo.toString());
        JdbcTemplate jdbcTemplate = connect2AnotherOne(dataSourceInfo);
        SQLInfo sql = getSQLByCondition(dbId, SQLTypeConstant.QUERY_CODE);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.getStatement());
        log.info("查询结果为", maps.toString());
        return maps;
    }

    private SQLInfo getSQLByCondition(Long dbId, Integer sqlType) {
        log.info("根据dbId={},sqlType={}查询SQL语句", dbId, sqlType);
        SQLInfo sqlByCondition = masterDataSourceMapper.getSQLByCondition(dbId, sqlType);
        log.info("根据dbId={},sqlType={}查询出SQL语句={}", dbId, sqlType, sqlByCondition.toString());

        return sqlByCondition;
    }

    @Override
    public List<DataSourceInfo> queryAllDataSourceInfo() {
        return masterDataSourceMapper.queryAll();
    }

    @Override
    @Transactional
    public Integer executeInsertSQL(Long sqlId) throws Exception {
        SQLInfo sql = masterDataSourceMapper.getSQLBySqlId(sqlId);
        log.info("获取到sql="+sql.getStatement());
        JdbcTemplate jdbcTemplate =establishConnectionBySql(sql);
        int result = jdbcTemplate.update(sql.getStatement(), 1, "XSY测试JDBCTemplate", "109");
        int a=1/0;
        return result;
    }

    @Override
    public Integer executeDeleteSQL(Long sqlId) throws Exception {
        SQLInfo sql = masterDataSourceMapper.getSQLBySqlId(sqlId);
        log.info("获取到sql="+sql.getStatement());
        JdbcTemplate jdbcTemplate =establishConnectionBySql(sql);
        int update = jdbcTemplate.update(sql.getStatement(), 100);
        int a=1/0;
        return update;
    }

    @Override
    public Integer executeUpdateSQL(Long sqlId) throws Exception {
        SQLInfo sql = masterDataSourceMapper.getSQLBySqlId(sqlId);
        log.info("获取到sql="+sql.getStatement());
        JdbcTemplate jdbcTemplate =establishConnectionBySql(sql);
        return jdbcTemplate.update(sql.getStatement(),"蓬莱大道","移动",25);
    }

    public Object[] attributes2Array(Object o) throws IllegalAccessException {
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Object[] params = new Object[fields.length];
        int i = 0;
        for (Field field : fields) {
            //为了可以访问到private修饰的成员变量
            field.setAccessible(true);
            params[i] = field.get(o);
            i++;
        }
        return params;
    }

}
