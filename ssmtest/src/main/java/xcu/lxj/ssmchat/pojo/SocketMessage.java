package xcu.lxj.ssmchat.pojo;


import lombok.Data;

@Data
public class SocketMessage<T> {

    private String type;          // open |  close | common | other | chat
    private String receiverType;  // user |  group | other
    private String receiverId;    // uid  |  gid
    private String senderId;      // 发送人的id
    private String content;       // 发送携带的内容
    private T data;               // data 扩展携带的东西
}
