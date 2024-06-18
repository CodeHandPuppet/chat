
import axios from "axios";
import { socketCommon } from "./common";



export function onWebSocketService(

    path: string,

    onmessage: ((this: WebSocket, ev: MessageEvent) => void) | null,

    onopen: ((this: WebSocket, ev: Event) => void) | null

): WebSocket {


    const socket = new WebSocket(`${socketCommon.baseURL}${path}`);


    if (onopen) {
        socket.onopen = onopen;
    }
    else {
        socket.onopen = (e: any) => {
            // socket.send("Hello")

            console.log("opening socket" + socket.url);

        }
    }
    // 开启监听

    // 接收信息进行自己配置
    socket.onmessage = onmessage;

    // 异常监听
    socket.onerror = () => {

        console.log("socket error");

    };

    //关闭监听 
    socket.onclose = () => {
        console.log("socket close");

    };

    // 返回socket
    return socket;
}


