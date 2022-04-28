package xsy.forstudying.practice.aspect.controller;

import org.springframework.web.bind.annotation.*;
import xsy.forstudying.practice.aspect.annotation.WebLog;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-10-14-15:25
 **/
@RestController
@RequestMapping("/aspect")
public class AspectTestController {

    @GetMapping("/web-log")
    @WebLog(description = "测试切面切点接口")
    public String doWebLog(String name){
        System.out.println("进入测试切面切点的Controller,接收到的参数为name="+name);
        return "切面增强之后，接收的参数变为name="+name;
    }


}
