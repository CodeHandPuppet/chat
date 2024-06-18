package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.Message;

import java.util.List;

public interface GroupMessageMapper {

    void insertOne(@Param("gid") String gid,@Param("message") Message message);

    List<Message> selectAll(@Param("gid") String gid);

    void deleteByUid(@Param("gid") String gid,@Param("uid") String uid);

//    void updateOne(@Param("gid") String gid,@Param("message") Message message);

}
