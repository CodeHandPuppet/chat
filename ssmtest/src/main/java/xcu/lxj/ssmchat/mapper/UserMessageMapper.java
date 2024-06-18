package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.Message;

import java.util.List;

public interface UserMessageMapper {


    void insertOne(@Param("uid") String uid,@Param("message") Message message);

    List<Message> selectOne(@Param("uid") String uid,@Param("fid") String fid);

    void deleteByFid(@Param("uid") String uid,@Param("fid") String fid);

    void updateOne(@Param("uid") String uid,@Param("message") Message message);




}
