
create database IF NOT EXISTS ssmchat;

use ssmchat;



CREATE TABLE IF NOT EXISTS user(
                                   uid int auto_increment PRIMARY KEY,
                                   username VARCHAR(255) not null unique ,
                                   password VARCHAR(255) not null ,
                                   token VARCHAR(36) default (UUID()) not null unique ,
                                   avatar VARCHAR(255),
                                   build_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS groups_info(
                                           gid int auto_increment PRIMARY KEY ,
                                           owner_uid int  not null,
                                           group_name VARCHAR(255),
                                           group_intro VARCHAR(255),
                                           avatar VARCHAR(255) not null,
                                           build_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           FOREIGN KEY (owner_uid) REFERENCES user(uid)
);


# insert into
#     user(username,password,avatar)
# values
#     ('user001','123456','ssss'),            ##小雷
#     ('user002','123456','ssss'),            ##小飞
#     ('user003','123456','ssss');            ##小张
#
# insert into
#     groups_info(owner_uid, group_name, group_intro, avatar)
# values
#     (1,'group001','group001_intro','ssss'), ## 小雷的群
#     (2,'group002','group002_intro','ssss'); ##  小飞的群


insert into
    group_1_member(type, uid)
values
    ('owner',1),
    ('admin',2),
    ('user',3);

insert into
    group_2_member(type,uid)
values
    ('owner',2),
    ('admin',3),
    ('user',1);


# 小雷
insert  into
    user_1_friends(appellation, fid)
values
    ('小飞坤',2),
    ('小张张',3);

insert into
    user_1_groups(appellation, gid)
values
    ('我的群',1),
    ('小飞的群',2);

insert into
    user_1_contact(fid, gid, type)
values
    (2,null,'user'),
    (3,null,'user'),
    (null,1,'group');

# 小飞
insert  into
    user_2_friends(appellation, fid)
values
    ('小雷',1),
    ('小张张',3);

insert into
    user_2_groups(appellation, gid)
values
    ('我的群',2),
    ('小雷的群',1);

insert into
    user_1_contact(fid, gid, type)
values
    (1,null,'user');

# 小张
insert  into
    user_3_friends(appellation, fid)
values
    ('小雷',1),
    ('小飞',2);

insert into
    user_3_groups(appellation, gid)
values
    ('小雷的群',1),
    ('小飞的群',2);

insert into
    user_3_contact(fid, gid, type)
values
    (1,null,'user');