package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.UserNotification;

import java.util.List;

public interface UserNotificationMapper {


    List<UserNotification> selectAll(String uid);

    List<UserNotification> selectByType(@Param("uid") String uid,@Param("type") String type);

    void insertOne(@Param("receiverId") String receiverId,@Param("notification") UserNotification notification);

    int update(@Param("uid") String uid,@Param("userNotification") UserNotification userNotification);

    int deleteById(@Param("uid") String uid,@Param("id") String id);

    int deleteByUid(@Param("uid") String uid,@Param("delUid")String delUid);
}
