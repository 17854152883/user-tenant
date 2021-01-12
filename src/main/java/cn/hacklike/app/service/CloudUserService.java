package cn.hacklike.app.service;

import cn.hacklike.app.entity.CloudUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.multipart.MultipartFile;

public interface CloudUserService {

    CloudUser addUser(CloudUser cloudUser);

    String testUpload(MultipartFile file);

}
