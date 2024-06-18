package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.GroupMessageMapper;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.service.GroupMessageService;

import java.util.List;


@Transactional
@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Resource
    GroupMessageMapper groupMessageMapper;
    @Override
    public List<Message> getMessages(String gid) {
//  直接获取聊天消息
        List<Message> messages = groupMessageMapper.selectAll(gid);

        return messages;
    }

    @Override
    public void saveMessage(String gid, Message message) {
//     直接存入gid
        groupMessageMapper.insertOne(gid, message);

    }

    @Override
    public void deleteMessageByUid(String gid, String uid) {
//
        groupMessageMapper.deleteByUid(gid, uid);
    }
}
