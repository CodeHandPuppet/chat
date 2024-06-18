package xcu.lxj.ssmchat.service;

import xcu.lxj.ssmchat.pojo.GroupMember;
import xcu.lxj.ssmchat.pojo.GroupsInfo;

public interface GroupService {

    Boolean addGroup(GroupsInfo groupsInfo);

//  创建群需要群的信息 和 群成员
    int createGroup(GroupsInfo groupsInfo);

}
