package cn.hacklike.app.controller;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.service.CloudUserService;
import cn.hacklike.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private CloudUserService cloudUserService;

    @RequestMapping("hello")
    public String helloWorld(){
        System.out.println("你好，我的世界！！hello，my world！！");
        return "你好，我的世界！！hello，my world！！";
    }

    @RequestMapping("uploadFile")
    public String testUpload(@RequestParam("file") MultipartFile file){

        return cloudUserService.testUpload(file);

    }

    @RequestMapping("add")
    public Response<CloudUser> addCloudUser(@RequestBody CloudUser cloudUser){
        try {
            CloudUser user = this.cloudUserService.addUser(cloudUser);
            return Response.newInstance(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.newErrorInstance("添加用户失败",e);
        }
    }


}
