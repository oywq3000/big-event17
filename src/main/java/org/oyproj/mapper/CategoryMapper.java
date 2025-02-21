package org.oyproj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.oyproj.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //add new category
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);
}
