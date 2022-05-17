package xsy.forstudying.practice.dbconnector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author XuSiyu
 * @version v1.0
 * @date 2022-05-13-10:36
 **/
@Getter
@Setter
@ToString
public class SQLInfo {
    private Long id;
    private String statement;
    private Long dbId;
    private Integer sqlType;
}
