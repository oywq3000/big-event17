package org.oyproj.controller;



import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.URL;
import org.oyproj.pojo.Result;
import org.oyproj.pojo.User;
import org.oyproj.service.UserService;
import org.oyproj.utils.JwtUtil;
import org.oyproj.utils.Md5Util;
import org.oyproj.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        //search user
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            //the username has been occupied
            return Result.error("the username has been occupied");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        //search user
        User user = userService.findByUserName(username);
        if (user == null) {
            return Result.error("username is not exist");
        }

        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("password error");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map =  ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.validate params
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("lack of necessary parameters");
        };
        //oldPwd is correct ?
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("old password error");
        }

        //newPwd is equal to rePwd?
        if(!rePwd.equals(newPwd)){
            return Result.error("rePwd password and new password are different");
        }

        //2.call service
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
