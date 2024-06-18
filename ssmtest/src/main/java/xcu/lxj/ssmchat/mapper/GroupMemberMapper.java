package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.GroupMember;

import java.util.List;

public interface GroupMemberMapper {

    List<GroupMember> selectAll(String gid);

    int insertOne(@Param("gid") String gid,@Param("member") GroupMember groupMember);

    int insertMany(@Param("gid")String gid,@Param("members") List<GroupMember> groupMember);

    int updateOneByUid(@Param("gid") String gid,@Param("member")GroupMember groupMember);

    int deleteOneByUid(@Param("gid") String gid,@Param("uid") String uid);

    int deleteOneById(@Param("gid") String gid,@Param("id") String id);

}
