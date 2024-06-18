package xcu.lxj.ssmchat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserGroup  {

    private String id;

    private String appellation;

    private String gid;

//  group 实体类
    GroupsInfo group;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String addTime;
}
