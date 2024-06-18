import { UserNotificationEntity } from "@/type/entity";
import { ResponseData, UserNotificationResponseData } from "@/type/responseData"
import request from "@/utils/request"

enum API {

    USERNOTIFICATION_GETALL = '/getUserNotification',

    USERNOTIFICATION_GETTYPE = '/getUserNotificationByType',

    USERNOTIFICATION_ADD = '/addUserNotification',

    USERNOTIFICATION_UPDATE = '/updateNotification',

    USERNOTIFICATION_DELETE = '/deleteNotification',
}

/**
 * 
 * 获取全部通知
 * 
 * @returns 
 */

export const userNotificationRequest = () => {

    return request.get<any, UserNotificationResponseData>(API.USERNOTIFICATION_GETALL);
}

/**
 * 通过类型获得对应的通知
 * 
 * @param type 通知的类型 user | group
 * @returns 
 */
export const userNotificationByTypeRequest = (type: 'user' | 'group') => {
    return request.get<any, UserNotificationResponseData>(API.USERNOTIFICATION_GETTYPE + `?type=${type}`);
}


/**
 * 给某个人（receiver）发送通知请求
 * 
 * @param receiverId 接收者的 uid
 * @param userNotification 通知实体类型
 * @returns 
 */

// 
export const userNotificationAddRequest = (receiverId: string, userNotification: UserNotificationEntity) => {

    return request.post<any, ResponseData>(API.USERNOTIFICATION_ADD, { receiverId, userNotification });
}

/**
 * 更新通知
 * 
 * @param id 待更新通知的id
 * @param content 需要更改为的内容
 * @returns 
 */

export const userNotificationUpdateRequest = (id: string, content: string) => {
    return request.post<any, ResponseData>(API.USERNOTIFICATION_UPDATE, { id, content });
}

/**
 * 根据通知的id删除对应的通知
 * 
 * @param id 
 * @returns 
 */

export const userNotificationDeleteRequest = (id: string) => {
    return request.delete<any, ResponseData>(API.USERNOTIFICATION_DELETE + `?id=${id}`);
}