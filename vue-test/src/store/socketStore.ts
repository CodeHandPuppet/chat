import { MessageEntity, SocketMessageEntity, UserContactEntity, UserEntity } from "@/type/entity";
import { getLocalStorage, removeLocalStorage, setLocalStorage } from "@/utils/localStore";
import { defineStore } from "pinia";
import { pinia } from "@/plugins/pinia";
import { useUserStore } from "./userStore";
import { onWebSocketService } from "@/utils/socket";


export const useSocketStore = defineStore("SocketStore", {
    state: () => ({
        socket: 0 as any,
        socketMessage: {} as SocketMessageEntity<Object>,
        type: "user" as "user" | "group" | "other",
        receiverId: "" as string,
        senderId: "" as string,
        socketType: "" as "open" | "close" | "common" | "other" | "chat" | "userContact"
    }),
    getters: {

        chatMessage: (state) => {
            if (state.socketMessage.type == "chat") {
                return state.socketMessage.data as MessageEntity
            }
            else
                null;
        },


    },
    actions: {
        /**
         * 开启 WebSocket 连接
         * 
         * @param onMessage 
         */
        openWebSocketService(onMessage: ((this: WebSocket, ev: MessageEvent<any>) => void) | null) {
            const userStore = useUserStore(pinia);
            this.socket = onWebSocketService("/websocket?userId=" + userStore.uid, onMessage, null)
            if (onMessage == null) {
                this.socket.onmessage = (ev: MessageEvent) => {

                    this.socketMessage = JSON.parse(ev.data);
                    this.type = this.socketMessage.receiverType!;
                    this.receiverId = this.socketMessage.receiverId!
                    this.senderId = this.socketMessage.senderId!;
                    console.log("onmessage : ", this.socketMessage);

                }
            }
        },
        /**
         * 关闭webSocket连接
         * 
         */
        closeWebSocketService() {
            this.socket.send(JSON.stringify({
                type: "close"
            } as SocketMessageEntity<Object>));
        },
        /**
         * 发送 socket 消息 
         * 
         * @param message 
         */
        sendMessage(message: SocketMessageEntity<Object>) {
            this.socket.send(JSON.stringify(message));
            console.log(message);
        },
        /**
         * 发送聊天消息
         * 
         * @param chatMessage 
         */
        sendChatMessage(chatMessage: SocketMessageEntity<Object>) {
            chatMessage.type = "chat";
            chatMessage.data = null;
            console.log("send : ", chatMessage);
            this.socket.send(JSON.stringify(chatMessage));
        },
        /**
         * 获得聊条消息
         * 
         * @param userContact 
         * @returns 
         */
        getChatMessage(userContact: UserContactEntity) {
            if (this.socketMessage.type != "chat") {
                return null;
            }
            if (userContact.type == 'user') {
                if (userContact.fid == this.senderId) {

                    return this.socketMessage.data as MessageEntity;
                }
            }
            if (userContact.type == 'group') {
                if (userContact.gid == this.receiverId) {
                    return this.socketMessage.data as MessageEntity;
                }
            }
            return null;
        }



    },
});
