package org.oyproj.service.impl;

import org.oyproj.mapper.UserMapper;
import org.oyproj.pojo.User;
import org.oyproj.service.UserService;
import org.oyproj.utils.Md5Util;
import org.oyproj.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user =  userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //encoding password
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }
}
