-- 创建数据库，指定字符集为utf8mb4 排序规则为utf8mb4_bin
CREATE DATABASE IF NOT EXISTS weblog DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;


create table  if not exists weblog.t_user(
    id bigint(20) UNSIGNED  auto_increment comment 'id',
    username varchar(60) not null comment '用户名',
    password varchar(60) not null comment '密码',
    create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP comment '更新时间',
    is_deleted tinyint(2) DEFAULT '0' comment '逻辑删除 0：未删除；1：已删除',
    primary key (id),
    unique key uk_username (username)
)engine=Innodb default charset=utf8mb4 comment='用户表';

