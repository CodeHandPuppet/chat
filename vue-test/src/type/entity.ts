
export interface UserEntity {

    uid?: string
    username?: string
    password?: string
    token?: string
    avatar?: string
    buildTime?: String

}


export interface GroupEntity {

    gid?: string
    ownerUid?: string
    groupName?: string
    groupIntro?: string
    avatar?: string
    build_time?: Date
    user?: UserEntity
    groupMembers?: GroupMemberEntity[]

}

export interface UserFriendEntity {
    id: string
    appellation: string | null
    fid: string
    user: UserEntity
    addTime: string
}

export interface UserGroupEntity {
    id: string
    appellation: string
    gid: string
    group: GroupEntity
    addTime: string
}

export interface UserContactEntity {
    id?: string
    fid?: string | null
    gid?: string | null
    type?: 'user' | 'group'
    friend?: UserFriendEntity | null
    group?: UserGroupEntity | null
    contactTime?: string
}

export interface UserNotificationEntity {
    id?: string
    uid?: string | null
    gid?: string | null
    type?: 'user' | 'group'
    user?: UserEntity | null
    group?: GroupEntity | null
    notTime?: string
    agree?: boolean
    isFinish?: boolean
    content?: string
}

export interface GroupMemberEntity {

    id?: string
    type?: 'user' | 'owner' | 'admin'
    uid?: string
    user?: UserEntity
    joinTime?: string

}


export interface MessageEntity {

    id?: string
    content?: string
    uid?: string
    user?: UserEntity
    sendTime?: string
}

export interface SocketMessageEntity<T> {

    type?: "open" | "close" | "common" | "other" | "chat" | "userContact"
    receiverType?: "user" | "group" | "other"
    receiverId?: string
    content?: string
    senderId?: string
    data?: T | null
}

