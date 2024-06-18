package xcu.lxj.ssmchat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class UserFriend{

    private String id;

    private String appellation;

    private String fid ;

//  User实体类
    User user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
}
