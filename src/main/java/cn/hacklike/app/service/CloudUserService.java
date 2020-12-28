package cn.hacklike.app.service;

import cn.hacklike.app.entity.CloudUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CloudUserService {

    CloudUser addUser(CloudUser cloudUser);

}
