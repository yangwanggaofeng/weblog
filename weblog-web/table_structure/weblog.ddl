-- 创建数据库，指定字符集为utf8mb4 排序规则为utf8mb4_bin
-- 在mysql中， 若一张表里面不存在varchar、text以及其变形、blob以及其变形的字段的话，那么张这个表其实也叫静态表，即该表的row_format是fixed，就是说每条记录所占用的字节一样。其优点读取快，缺点浪费额外一部分空间。
--
-- 若一张表里面存在varchar、text以及其变形、blob以及其变形的字段的话，那么张这个表其实也叫动态表，即该表的row_format是dynamic，就是说每条记录所占用的字节是动态的。其优点节省空间，缺点增加读取的时间开销。

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


create table if not exists weblog.t_article(
    id bigint(20) unsigned not null auto_increment comment '文章id',
    title varchar(120) not null default '' comment '文章标题',
    cover varchar(120) not null default '' comment '文章封面',
    summary varchar(160) default '' comment '文章摘要',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp comment '更新时间',
    is_delete tinyint(2) not null default '0' comment '删除标志： 0：未删除; 1： 已删除',
    read_num int(11) unsigned not null default '1' comment '被阅读次数',
    primary key (id) using BTREE,
    key idx_create_time (create_time) using btree
)engine=innodb default charset=utf8mb4 row_format=dynamic comment='文章表';

create table if not exists weblog.t_article_content(
    id bigint(20) unsigned not null auto_increment comment '文章内容id',
    article_id bigint(20) not null comment '文章id',
    content text comment '教程正文',
    primary key (id) using btree,
    key idx_article_id (article_id) using btree
)engine=innodb default charset=utf8mb4 row_format=dynamic comment '文章内容正文';
-- weblog.t_article_categroy_rel definition

CREATE TABLE `t_article_categroy_rel` (
                                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                          `article_id` bigint unsigned NOT NULL COMMENT '文章id',
                                          `category_id` bigint unsigned NOT NULL COMMENT '文章分类id',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          UNIQUE KEY `uni_article_id` (`article_id`) USING BTREE,
                                          KEY `idx_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章所属分类关联表';
-- weblog.t_article_tag_rel definition

CREATE TABLE `t_article_tag_rel` (
     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
     `article_id` bigint unsigned NOT NULL COMMENT '文章id',
     `tag_id` bigint unsigned NOT NULL COMMENT '文章标签id',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `idx_article_id` (`article_id`) USING BTREE,
     KEY `idx_tag_id` (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章所属标签关联表';