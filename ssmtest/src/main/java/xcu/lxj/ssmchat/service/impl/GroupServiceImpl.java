package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.GroupMapper;
import xcu.lxj.ssmchat.mapper.GroupMemberMapper;
import xcu.lxj.ssmchat.mapper.UserGroupMapper;
import xcu.lxj.ssmchat.pojo.GroupMember;
import xcu.lxj.ssmchat.pojo.GroupsInfo;
import xcu.lxj.ssmchat.pojo.UserGroup;
import xcu.lxj.ssmchat.service.GroupService;

import java.util.List;

@Transactional
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    GroupMapper  groupMapper;
    @Resource
    GroupMemberMapper groupMemberMapper;
    @Resource
    UserGroupMapper userGroupMapper;
//  用于调试遗弃了
    @Override
    public Boolean addGroup(GroupsInfo group) {


        groupMapper.insertOne(group);
        String gid = group.getGid();
        groupMapper.createGroupMember(gid);
        groupMapper.createGroupMessage(gid);

        return true;
    }

    @Override
    public int createGroup(GroupsInfo groupsInfo) {

//       获取gid后建表
        groupMapper.insertOne(groupsInfo);
        String gid = groupsInfo.getGid();
        groupMapper.createGroupMember(gid);
        groupMapper.createGroupMessage(gid);

//      添加群成员信息
        List<GroupMember> groupMembers=groupsInfo.getGroupMembers();
        groupMemberMapper.insertMany(gid,groupMembers);
//      给成员的group表插入 加入的信息
        UserGroup userGroup = new UserGroup();
        userGroup.setGid(gid);
        userGroup.setAppellation(groupsInfo.getGroupName());
//      将所有成员的对应表中添加该 群信息
        for(GroupMember g :groupMembers){
            userGroupMapper.insertOne(g.getUid(),userGroup);
        }

        return 0;
    }


}
