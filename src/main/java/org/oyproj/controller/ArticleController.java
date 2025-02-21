package org.oyproj.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Mapper;
import org.oyproj.pojo.Result;
import org.oyproj.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
        //verify token
        /*try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("article data");
        } catch (Exception e) {
            response.setStatus(401);
            return Result.error("not login");
        }*/
        return Result.error("article data");
    }
}
