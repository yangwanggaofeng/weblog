package com.study.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName ArticleDO
 * @Description 文章bean
 * @Author zhang
 * @Date 2024/3/10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_article" ,schema = "weblog")
public class ArticleDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String cover;
    private String summary;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDelete;
    private Long redNum;
}
