package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserGroup;

import java.util.List;

public interface UserGroupMapper {


    //  找到他添加的组
    List<UserGroup> selectGroups(String uid);

    //
    UserGroup selectOne(@Param("uid") String uid, @Param("gid") String gid);

    //
    int insertOne(@Param("uid") String uid,@Param("userGroup") UserGroup userGroup);

    //
    int deleteOneByGid(@Param("uid") String uid, @Param("gid") String gid);

    //
    int updateOneByGid(@Param("uid") String uid, @Param("userGroup")UserGroup userGroup);
}
