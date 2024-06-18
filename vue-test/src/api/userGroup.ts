import { UserGroupResponseData } from "@/type/responseData";
import request from "@/utils/request"

enum API {

    USERGROUP = '/getUserGroup',
}


/**
 * 获取用户加入的全部群聊
 * 
 * @returns 
 */

export const userGroupGetAllRequest = () => {
    return request.get<any, UserGroupResponseData>(API.USERGROUP);
}




