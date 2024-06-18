import { userGetUserInfoRequest, userLoginRequest } from "@/api/user";
import { UserContactEntity, UserEntity } from "@/type/entity";
import { COMMON } from "@/utils/common";
import { getLocalStorage, removeLocalStorage, setLocalStorage } from "@/utils/localStore";

import { defineStore } from "pinia";
import { pinia } from "@/plugins/pinia";
import { it } from "node:test";



export const useUserContactStore = defineStore("CommonStore", {
    state: () => ({
        // 每次都要进行验证添加的 
        userContact: [] as UserContactEntity[],
        // 选中的
        selectUserContact: null as UserContactEntity | null,
        // 展示的
        showUserContact: [] as UserContactEntity[]
    }),
    getters: {},
    actions: {
        push(value: UserContactEntity) {

            let index = this.userContact.findIndex(item => {
                return (item.type == value.type) && (item.fid == value.fid) && (item.gid == value.gid)
            })
            if (index == -1) {
                this.userContact.push(value)

            }
            this.selectUserContact = value;
        },
        // 用于合并 返回回来的常用联系人
        combineUserContact(combineUserContact: UserContactEntity[]): UserContactEntity[] {
            {

                let tempUserContact = [...combineUserContact];
                // 可优化就是删除 添加不进去的
                this.userContact.forEach(contact => {

                    let index = combineUserContact.findIndex(item => {
                        return (item.type == contact.type) && (item.fid == contact.fid) && (item.gid == contact.gid);
                    })
                    if (index == -1) {
                        tempUserContact.push(contact);
                    }

                })

                this.showUserContact = [...tempUserContact];
                return tempUserContact;
            }
        }
    }
})