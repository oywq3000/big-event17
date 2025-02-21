package org.oyproj.controller;


import org.apache.ibatis.annotations.Param;
import org.oyproj.pojo.Category;
import org.oyproj.pojo.Result;
import org.oyproj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated Category category){
        categoryService.update(category);
        return Result.success();
    }
}
