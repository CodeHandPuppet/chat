package xcu.lxj.ssmchat.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserContact {
    private String id;
    private String fid;
    private String gid;
    private String type;

    private UserFriend friend;

    private UserGroup group;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contactTime;
}
