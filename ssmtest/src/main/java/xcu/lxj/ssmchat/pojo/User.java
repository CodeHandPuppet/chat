package xcu.lxj.ssmchat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data

public class User {

    private String uid;

    private String username;

    private String password;

    private String token;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buildTime;

}
