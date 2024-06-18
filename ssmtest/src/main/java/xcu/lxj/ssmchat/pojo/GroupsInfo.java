package xcu.lxj.ssmchat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class GroupsInfo {

    private String gid;

    private String ownerUid;

    private String groupName;

    private String avatar;

    private String groupIntro;

    private User user;

    private List<GroupMember> groupMembers;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String buildTime;

}
