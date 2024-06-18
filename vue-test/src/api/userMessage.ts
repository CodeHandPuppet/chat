import { MessageEntity } from "@/type/entity";
import { MessageResponseData, ResponseData } from "@/type/responseData";
import request from "@/utils/request"

enum API {

    USERMESSAGE_GETONE = '/getUserMessage',

    USERMESSAGE_ADD = '/addUserMessage'

}

/**
 * 获取和好友的聊天消息
 * 
 * @param fid 好友的 uid
 * @returns 
 */

export const userMessageGetRequest = (fid: string) => {

    return request.get<any, MessageResponseData>(API.USERMESSAGE_GETONE + `?fid=${fid}`);
}


/**
 * 向 你的聊天数据库里面添加这条消息
 * 
 * @param message  
 * @returns 
 */
export const userMessageAddRequest = (message: MessageEntity) => {
    //  content type uid
    return request.post<any, ResponseData>(API.USERMESSAGE_ADD, message);
}







