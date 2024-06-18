package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.UserFriend;

import java.util.List;

public interface UserFriendMapper {
    //  找到他添加的朋友
    List<UserFriend> selectFriends(String uid);

    UserFriend selectOne(@Param("uid") String uid,@Param("fid") String fid);

    void  insertOne(@Param("uid") String uid,@Param("friend") UserFriend friend);

    void insertOneByFid(@Param("uid") String uid,@Param("fid") String fid);

    void updateOne(@Param("uid") String uid,@Param("friend")UserFriend friend);

    void deleteOneById(@Param("uid") String uid,@Param("fid")String fid);
}
