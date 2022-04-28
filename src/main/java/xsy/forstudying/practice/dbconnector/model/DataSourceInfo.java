package xsy.forstudying.practice.dbconnector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-25 16:23
 **/
@Getter
@Setter
@ToString
public class DataSourceInfo {
    private Long id;
    //数据库连接url
    private String url;
    //数据库用户名
    private String userName;
    //数据库密码
    private String password;
    //数据库驱动
    private String driverClassName;
}