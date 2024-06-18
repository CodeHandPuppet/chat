import { userGetUserInfoRequest, userLoginRequest } from "@/api/user";
import { UserEntity } from "@/type/entity";
import { COMMON } from "@/utils/common";
import { getLocalStorage, removeLocalStorage, setLocalStorage } from "@/utils/localStore";

import { defineStore } from "pinia";
import { pinia } from "@/plugins/pinia";
import { useSocketStore } from "./socketStore";



export const useUserStore = defineStore("UserStore", {
    state: (): UserEntity => ({
        token: getLocalStorage(COMMON.TOKEN),
    }),
    getters: {},
    actions: {
        async login(username: string, password: string) {
            const result = await userLoginRequest(username, password);

            if (result.code == 200) {

                // 
                this.token = result.data as string;
                // 浏览器存入token
                setLocalStorage(COMMON.TOKEN, this.token);
                // 登录成功录入信息
                const result2 = await this.getUserInfo();


                return true;
            } else {

                return false;
            }
        },
        async getUserInfo() {
            const result = await userGetUserInfoRequest(null);
            if (result.code != 200) {
                removeLocalStorage(COMMON.TOKEN);
                return false;
            }
            else {
                let user = result.data as UserEntity;
                this.uid = user.uid;
                this.token = user.token;
                this.username = user.username;
                this.buildTime = user.buildTime;
                this.avatar = user.avatar;
                this.password = user.password;

                // 获取 socket连接
                const socketStore = useSocketStore()
                socketStore.openWebSocketService(null);
                return true;
            }

        },
        loginOut() {
            removeLocalStorage(COMMON.TOKEN);
            this.$reset;
            const socketStore = useSocketStore()
            socketStore.closeWebSocketService();
            window.location.reload();
        },
    },
});
