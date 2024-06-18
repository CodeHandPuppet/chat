
import { MessageEntity, UserContactEntity, UserEntity, UserFriendEntity, UserGroupEntity, UserNotificationEntity } from "./entity";

// 
export interface ResponseData {

    code: number;

    message: string;

    data?: Object | null | string
}

// 
export interface UserInfoResponseData extends ResponseData {

    data: UserEntity | UserEntity[];

}


//  联系 人 或者 群
export interface UserContactResponseData extends ResponseData {
    data: UserContactEntity[];
}

export interface UserContactOneResponseData extends ResponseData {
    data: UserContactEntity;
}
// 
export interface UserFriendResponseData extends ResponseData {
    data: UserFriendEntity[]
}
// 

export interface UserGroupResponseData extends ResponseData {
    data: UserGroupEntity[]
}

export interface UserNotificationResponseData extends ResponseData {
    data: UserNotificationEntity[]
}

export interface MessageResponseData extends ResponseData {
    data: MessageEntity[]
}