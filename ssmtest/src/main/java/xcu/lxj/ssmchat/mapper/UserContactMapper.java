package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserContact;

import java.util.List;

public interface UserContactMapper {

    List<UserContact> selectContact(String uid);

    void insertOne(@Param("uid") String uid, @Param("userContact") UserContact userContact);

    void deleteById(@Param("uid") String uid, @Param("id") String id);

    int updateOne(@Param("uid") String uid, @Param("userContact") UserContact userContact);

    int updateByFid(@Param("uid") String uid, @Param("userContact") UserContact userContact);

    UserContact selectOneByFid(@Param("uid") String uid, @Param("fid") String fid);

    UserContact selectOneByGid(@Param("uid") String uid, @Param("gid") String gid);
}
