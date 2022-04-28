package xsy.forstudying.practice.dbconnector.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;

import javax.sql.DataSource;


/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-25 13:50
 **/
@Configuration
@MapperScan(basePackages = "MasterDataSourceMapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
    public static JdbcTemplate connectDataSource(DataSourceInfo dataSourceInfo) throws Exception {
        //创建dataSource
        DataSource build = DataSourceBuilder.create().driverClassName(dataSourceInfo.getDriverClassName())
                .url(dataSourceInfo.getUrl())
                .username(dataSourceInfo.getUserName())
                .password(dataSourceInfo.getPassword()).build();
        //设置mapper加载位置
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setDataSource(build);

        //
        DataSourceTransactionManager manager=new DataSourceTransactionManager();
        manager.setDataSource(build);

        JdbcTemplate jdbcTemplate=new JdbcTemplate(build);
        return jdbcTemplate;
    }
}

