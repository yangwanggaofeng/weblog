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

create table if not EXISTS  t_user_role(
    id int UNSIGNED not null auto_increment comment 'id',
    username varchar(60) not null comment '用户名',
    role varchar(60) comment '角色',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    primary key (id),
    key idx_username (username) using btree
)engine=innodb default charset=utf8mb4 row_format=dynamic comment='用户角色表';

-- 文章分类列表
-- drop table if exists weblog.t_category ;
create table weblog.t_category(
    id int auto_increment not null comment '分类id',
    name varchar(60) not null default '' comment '分类名称',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp comment '最后一次更新时间',
    is_deleted tinyint(2) default 0 comment '逻辑删除标志： 0:未删除; 1:已删除',
    primary key(id) using btree,
    unique index uk_name(name) using btree,
    index idx_create_time(create_time) using btree
)engine=innodb default charset=utf8mb4 comment='文章分类列表';

create table weblog.t_blog_settings(
    id bigint(20)  not null auto_increment comment 'id',
    logo varchar(120) not null default '' comment '博客Logo',
    name varchar(100) not null default '' comment '作者名',
    author varchar(100) not null default '' comment '博客名称',
    introduction varchar(200) not null default '' comment '介绍语',
    avatar varchar(200) not null default '' comment '作者头像',
    github_homepage varchar(100) not null default '' comment 'Github 主页访问地址',
    csdn_homepage varchar(100) not null default '' comment 'CSDN 主页访问地址',
    gitee_homepage varchar(100) not null default '' comment 'Gitee 主页访问地址',
    zhihu_homepage varchar(100) not null default '' comment '知乎 主页访问地址',
    primary key (id)
)engine=innodb default charset=utf8mb4 comment='博客设置表';