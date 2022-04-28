package xsy.forstudying.practice.httptrial.controller;

import org.springframework.web.bind.annotation.*;
import xsy.forstudying.practice.common.model.FileDocPushUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-10-14-15:25
 **/
@RestController
@RequestMapping("/http")
public class HttpController {
    @PostMapping("/upload")
    public String upload(@RequestBody FileDocPushUploadRequest fileDocPushUploadRequest){
        byte[] bytes=fileDocPushUploadRequest.getFileData();
        String fileName=fileDocPushUploadRequest.getFileName().replaceAll("\\?*","");
        fileDocPushUploadRequest.setFileName(fileName);
        System.out.println(fileDocPushUploadRequest.toString());
        File file=new File("C:\\Users\\eicp\\Desktop\\"+fileName);
//        File file=new File("C:\\Users\\eicp\\Desktop\\高亚科技.docx");
        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getPath());
        return "测试上传文件成功";
    }

    @GetMapping("/query")
    public FileDocPushUploadRequest query(@RequestParam("nodeKey") String nodeKey){
        System.out.println("收到了请求,参数为"+nodeKey);
        FileDocPushUploadRequest result=new FileDocPushUploadRequest();
        result.setFileName("返回值");
        byte[] a="dasdasdasda".getBytes();
        result.setFileData(a);
//        return "这就把文件"+nodeKey+"信息反传";
        return result;
    }

}
