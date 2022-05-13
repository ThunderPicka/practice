package xsy.forstudying.practice.dbconnector.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author XuSiyu
 * @version v1.0
 * @date 2022-05-13-10:36
 **/
@Getter
@Setter
public class SqlInfo {
    private Long id;
    private String statement;
    private Long dbId;
}
