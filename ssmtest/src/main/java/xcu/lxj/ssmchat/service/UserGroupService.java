package xcu.lxj.ssmchat.service;


import xcu.lxj.ssmchat.pojo.UserGroup;

import java.util.List;

public interface UserGroupService {

    List<UserGroup> getUserAllGroup(String token);
}
