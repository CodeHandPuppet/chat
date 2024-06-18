import { GroupEntity, GroupMemberEntity } from "@/type/entity"
import { ResponseData } from "@/type/responseData"
import request from "@/utils/request"

enum API {
    GROUP_CREATE = '/createGroup'
}



/**
 * 创建一个群聊
 * 
 * @param groupsInfo 群的基本信息
 * @returns 
 */

// 创建群聊
export const GroupCreateRequest = (groupsInfo: GroupEntity) => {

    return request.post<any, ResponseData>(API.GROUP_CREATE, groupsInfo)
}


