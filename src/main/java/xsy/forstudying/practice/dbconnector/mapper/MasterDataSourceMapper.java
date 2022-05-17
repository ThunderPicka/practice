package xsy.forstudying.practice.dbconnector.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xsy.forstudying.practice.dbconnector.model.DataSourceInfo;
import xsy.forstudying.practice.dbconnector.model.SQLInfo;

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
            "where id=#{dbId}")
    DataSourceInfo queryById(Long dbId);

    @Select("select id,statement,db_id as dbId,source_code as sourceCode,sql_type as sqlType from sql_mapper where id=#{sqlId}")
    SQLInfo getSQLBySqlId(Long sqlId);

    @Select("select id,statement,db_id as dbId,source_code as sourceCode,sql_type as sqlType from sql_mapper where db_id=#{dbId} and sql_type=#{sqlType} limit 1")
    SQLInfo getSQLByCondition(Long dbId, Integer sqlType);

    @Select("select id,url,drive_class_name as driverClassName,user_name as userName,password " +
            "from datasource limit 10")
    List<DataSourceInfo> queryAll();
}
