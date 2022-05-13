package xsy.forstudying.practice.dbconnector.service.impl;

import javafx.beans.binding.ObjectBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import xsy.forstudying.practice.dbconnector.config.DataSourceConfig;
import xsy.forstudying.practice.dbconnector.mapper.MasterDataSourceMapper;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.model.SqlInfo;
import xsy.forstudying.practice.dbconnector.model.XsyTest;
import xsy.forstudying.practice.dbconnector.service.IDataSourceService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    @Override
    public String getSql(Long id) {
        return masterDataSourceMapper.getSql(id);
    }

    @Override
    public List<Map<String, Object>> getResult(Long id) throws Exception {
        String sql = this.getSql(id);
        log.info("获取到指定sql=" + sql);
        JdbcTemplate jdbcTemplate = this.connect2AnotherOne(this.getDataSourceInfo(id));
        log.info("从数据源已注入成功");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @Override
    public List<DataSourceInfo> queryAll() {
        return masterDataSourceMapper.queryAll();
    }

    @Override
    public List<Map<String, Object>> testInsert(Long sqlId) throws Exception {
        SqlInfo sql = masterDataSourceMapper.getFullSql(sqlId);
        DataSourceInfo dataSourceInfo = this.getDataSourceInfo(sql.getDbId());
        JdbcTemplate jdbcTemplate = this.connect2AnotherOne(dataSourceInfo);
//        Long id = new Long(Integer.valueOf((int) Math.random() * 1000));
        Integer id = Integer.valueOf((int) (Math.random() * 1000));
        XsyTest xsyTest = new XsyTest(id, "徐司宇", Integer.valueOf(25), "你猜", "诶嘿，我就不说");
        Object[] objects = attributes2Array(xsyTest);
        //此处注意,如果数据库字段名称为小写，就很有可能需要SQL对字段名用""
        jdbcTemplate.update("insert into JEECGBOOT.XSY_TEST(id,name,age,address,company) values(?,?,?,?,?)", objects);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from \"JEECGBOOT\".\"XSY_TEST\"");
        return maps;
    }

    public Object[] attributes2Array(Object o) throws IllegalAccessException {
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Object[] params = new Object[fields.length];
        int i=0;
        for (Field field : fields) {
            //为了可以访问到private修饰的成员变量
            field.setAccessible(true);
            params[i]=field.get(o);
            i++;
        }
        return params;
    }

}
