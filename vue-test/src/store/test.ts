import { defineStore } from "pinia";

export const UseMainStore = defineStore("main", {
    state: () => {
        return {
            Buddy: 1,
            Home: 1,
            Chat: {
                chatType: "test",
                chatText: "11",
            },
            timeOut: 0 as any,
            timeInterval: 0 as any,
            webSocket: 0 as any,
            WebSocketMessageFlag: false,
            WebSocketMessageTitle: "网络断开",
        };
    },
    actions: {
        WebScoketSerive() {
            this.webSocket = new WebSocket(
                `ws:/localhost:8080/ssmchat/websocket`
            );
            this.webSocket.onopen = () => {
                this.WebSocketMessageTitle = "网络正常";

                this.timeOut = this.timeInterval = setInterval(() => {
                    this.webSocket.send(JSON.stringify(this.Chat));
                }, 10000);

            };

            this.webSocket.onerror = () => { };
            this.webSocket.onclose = () => {
                this.WebSocketMessageTitle = "网络连接关闭，请尝试刷新网页";
            };
            this.webSocket.onmessage = (e: any) => {
                this.WebSocketMessageTitle = "网络正常";
                // let data = JSON.parse(e.data);


                let data = e;

                //当类型时心跳检测的类型。清除定时器
                if (data.chatType == "test") {
                    clearTimeout(this.timeOut);
                } else if (data.chatType == "Info") {
                    // 系统消息不回复
                    clearTimeout(this.timeOut);
                } else if (data.chatType == "Delete") {
                    // 系统消息不回复
                    this.WebSocketMessageFlag = !this.WebSocketMessageFlag;
                    clearTimeout(this.timeOut);
                } else {

                    console.log(data);

                    this.WebSocketMessageFlag = !this.WebSocketMessageFlag;

                    clearTimeout(this.timeOut);
                }
                // 当心跳检测超时关闭websocket
                this.timeOut = setTimeout(() => {
                    clearInterval(this.timeInterval);
                    this.webSocket.close();
                }, 15000);
            };
        },
    },
});
