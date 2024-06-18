import { UserEntity } from "@/type/entity"
import { ResponseData, UserInfoResponseData } from "@/type/responseData"
import request from "@/utils/request"


enum API {
    // 登录
    USER_LOGIN = '/login',
    // 用户信息
    USER_GETUSERINFO = '/user',

    USER_REGISTER = '/signin',

    USER_GETUSERNAME = '/getUserByUsername',

    USER_GETUSERLIKE = '/getLikeUser',

}


/**
 * 登录验证
 * 
 * @param username 用户名
 * @param password 密码
 * @returns ResponseData   为200代表成功
 */
export const userLoginRequest = (username: string, password: string) => {

    return request.post<any, ResponseData>(API.USER_LOGIN, { username, password })

}
/**
 * 获取用户数据 通过uid   或者   通过request.headers.token 
 * 
 * @param uid 用户 id 可为空
 * @returns 
 */

export const userGetUserInfoRequest = (uid: string | null) => {
    let path = uid ? `?uid=${uid}` : "";
    return request.get<any, UserInfoResponseData>(API.USER_GETUSERINFO + path);

}

/**
 * 用户注册
 * 
 * @param user 用户实体类
 * @returns ResponseData
 */

// 
export const userRegisterRequest = (user: UserEntity) => {

    return request.post<any, ResponseData>(API.USER_REGISTER, user);

}

/**
 * 通过用户名模糊匹配获得用户的信息
 * 
 * @param username 用户名
 * @param like 模糊匹配的字符串
 * @returns UserInfoResponseData
 */
export const userGetUserInfoByUserNameRequest = (username: string, like: string | null) => {
    if (like) {
        return request.get<any, UserInfoResponseData>(API.USER_GETUSERLIKE + `?username=${username}`)
    }
    return request.get<any, UserInfoResponseData>(API.USER_GETUSERNAME + `?username=${username}`);

}
