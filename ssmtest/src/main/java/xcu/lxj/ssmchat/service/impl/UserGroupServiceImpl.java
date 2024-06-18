package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserGroupMapper;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserGroup;
import xcu.lxj.ssmchat.service.UserGroupService;

import java.util.List;


@Transactional
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserGroupMapper userGroupMapper;


    @Override
    public List<UserGroup> getUserAllGroup(String token) {

        User user = userMapper.selectOneByToken(token);
        String uid = user.getUid();

        List<UserGroup> userGroups = userGroupMapper.selectGroups(uid);

        return userGroups;
    }
}
