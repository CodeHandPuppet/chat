package xcu.lxj.ssmchat.mapper;

import xcu.lxj.ssmchat.pojo.GroupsInfo;

public interface GroupMapper {

    int insertOne(GroupsInfo group);
//建表
    int createGroupMember(String gid);
//建表
    int createGroupMessage(String gid);

    GroupsInfo selectOne(String gid);




}
