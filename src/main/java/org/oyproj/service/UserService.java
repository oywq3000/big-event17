package org.oyproj.service;

import org.oyproj.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    //update avatar
    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
