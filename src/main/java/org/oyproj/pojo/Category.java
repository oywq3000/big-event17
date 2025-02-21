package org.oyproj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;//主键ID
    private String categoryName;//分类名称
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    //assign format when database convert to object
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
