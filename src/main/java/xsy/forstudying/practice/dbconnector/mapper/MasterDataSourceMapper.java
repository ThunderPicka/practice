package xsy.forstudying.practice.dbconnector.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;

import java.util.List;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-26 14:46
 **/

@Mapper
public interface MasterDataSourceMapper {
    @Select("select id,url,drive_class_name as driverClassName,user_name as userName,password " +
            "from datasource " +
            "where id=#{id}")
    DataSourceInfo queryById(Long id);

    @Select("select statement from sql_mapper where db_id=#{id}")
    String getSql(Long id);

    @Select("select id,url,drive_class_name as driverClassName,user_name as userName,password " +
            "from datasource")
    List<DataSourceInfo> queryAll();
}
