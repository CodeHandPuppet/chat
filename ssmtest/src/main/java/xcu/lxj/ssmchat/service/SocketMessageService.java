package xcu.lxj.ssmchat.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.pojo.SocketMessage;

import java.io.IOException;

public interface SocketMessageService {

    void userMessageHandle(SocketMessage socketMessage) throws IOException;

    void GroupMessageHandle(SocketMessage socketMessage) throws IOException;



}
