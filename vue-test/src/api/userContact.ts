import { UserContactEntity } from "@/type/entity"
import { ResponseData, UserContactOneResponseData, UserContactResponseData } from "@/type/responseData"
import request from "@/utils/request"


enum API {

    USERCONTACT_GET = '/getContact',

    USERCONTACT_ADD = '/addContact',


}
/**
 * 获取用户的 经常联系人
 * 
 * @param uid 用户id
 * @returns 
 */

export const contactGetRequest = (uid: string) => {
    return request.get<any, UserContactResponseData>(API.USERCONTACT_GET + `?uid=${uid}`)
}

/**
 * 添加一个 常联系人
 * 
 * @param userContact 
 * @returns 
 */

export const contactAddRequest = (userContact: UserContactEntity) => {

    // fid  gid  type
    return request.post<any, UserContactOneResponseData>(API.USERCONTACT_ADD, userContact);


}