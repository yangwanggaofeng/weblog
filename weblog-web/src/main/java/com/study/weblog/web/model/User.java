package com.study.weblog.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName User
 * @Description TODO
 * @Author zhang
 * @Date 2023/11/2
 * @Version 1.0
 **/
@Data
@ApiModel("用户实体类")
public class User {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空") // 注解确保用户名不为空
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Max(value = 100, message = "年龄必须小于等于100")
    @Min(value = 18, message = "年龄必须大于等于18")
    @ApiModelProperty("用户年龄")
    private Integer age;

    // 创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    // 更新日期
    @ApiModelProperty("更新日期")
    private LocalDate updateDate;
    // 时间
    @ApiModelProperty("时间")
    private LocalTime time;
}

