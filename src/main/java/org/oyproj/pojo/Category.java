package org.oyproj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty //default group
    private String categoryName;//分类名称
    @NotEmpty //default group
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    //assign format when database convert to object
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间




    //validation classification
    //if validation group is not assigned,it follows the default
    //group also can be extended
    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
