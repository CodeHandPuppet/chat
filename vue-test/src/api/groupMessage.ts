import { MessageEntity } from "@/type/entity";
import { MessageResponseData, ResponseData } from "@/type/responseData";
import request from "@/utils/request"
import { message } from "ant-design-vue";

enum API {

    GROUPMESSAGE_GET = '/getGroupMessage',

    GROUPMESSAGE_ADD = '/addGroupMessage'
}

/**
 * 获取群消息
 * 
 * @param gid 群id
 * @returns 
 */

export const groupMessageGetRequest = (gid: string) => {

    return request.get<any, MessageResponseData>(API.GROUPMESSAGE_GET + `?gid=${gid}`);
}


/**
 * 向某群添加消息
 * 
 * @param gid 群id
 * @param Message = { content , uid  } 
 * @returns 
 */

export const groupMessageAddRequest = (gid: string, Message: MessageEntity) => {

    return request.post<any, ResponseData>(API.GROUPMESSAGE_ADD + `?gid=${gid}`, message);

}









