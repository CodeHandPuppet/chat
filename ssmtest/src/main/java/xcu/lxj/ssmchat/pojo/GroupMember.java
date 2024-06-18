package xcu.lxj.ssmchat.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GroupMember {

    private String id;

    private String type;

    private String uid;

    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinTime;


}
