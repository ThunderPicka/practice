package xsy.forsutying.test;

import xsy.forstudying.practice.common.model.User;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-12-20 9:37
 **/
public class TestNullPointException {
    public static void main(String[] args){
        User user=new User();
        if (user.getId()==null){
            System.out.println("id为空值");
        }

    }


}
