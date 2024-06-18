import { UserFriendEntity, UserNotificationEntity } from "@/type/entity";
import { ResponseData, UserFriendResponseData } from "@/type/responseData";
import request from "@/utils/request"

enum API {

    USERFRIEND_GETALL = '/getUserFriend',

    USERFRIEND_ADD = '/addUserFriend',

    USERFRIEND_UPDATEAPPELLATION = '/updateUserFriend',

    USERFRIEND_DELETE = '/deleteUserFriend',
}

/**
 * 获取好友列表
 * 
 * @returns 
 */

export const userFriendGetAllRequest = () => {
    return request.get<any, UserFriendResponseData>(API.USERFRIEND_GETALL);
}

/**
 *  添加好友
 * 
 * @param userNotification 
 * @returns 
 */
export const userFriendAddRequest = (userNotification: UserNotificationEntity) => {

    return request.post<any, ResponseData>(API.USERFRIEND_ADD, userNotification);

}
/**
 * 更新昵称
 * 
 * @param userFriend 
 * @returns 
 */
export const userFriendUpdateRequest = (userFriend: UserFriendEntity) => {

    return request.post<any, ResponseData>(API.USERFRIEND_UPDATEAPPELLATION, userFriend);
}
/**
 * 删除好友
 * 
 * @param fid 
 * @returns 
 */
export const userFriendDeleteRequest = (fid: string) => {
    return request.get<any, ResponseData>(API.USERFRIEND_DELETE + `?fid=${fid}`);
}