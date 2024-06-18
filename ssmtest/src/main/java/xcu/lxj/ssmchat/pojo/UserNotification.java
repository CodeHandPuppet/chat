package xcu.lxj.ssmchat.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserNotification {

    private String id;

    private String type;

    private String uid;

    private String gid;

    private String content;

    private Boolean agree;

    private Boolean isFinish;

    private User user;

    private GroupsInfo group;;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date notTime;
}
