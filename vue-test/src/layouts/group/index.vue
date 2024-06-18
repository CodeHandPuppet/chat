<script setup lang="ts">
import { onUnmounted, ref } from "vue";

import { onWebSocketService } from "@/utils/socket";
import axios from "axios";
import { useUserStore } from "@/store/userStore";
import { useSocketStore } from "@/store/socketStore";

const onMessage = (ev: MessageEvent) => {
  console.log(ev.data);
  message.value = ev.data;
};

const userStore = useUserStore();

let socket: WebSocket;

const cc = () => {
  socket = onWebSocketService("/websocket?userId=" + userStore.uid, onMessage, null);
};
const ssss = () => {
  socket.send("Hello, world!");
};
const message = ref("你好啊");

const coo = () => {
  const socketStore = useSocketStore();
  socketStore.closeWebSocketService();
};
</script>

<template>
  <div class="text-red-400">
    {{ message }}
    <el-button @click="cc">连接</el-button>
    <el-button type="primary" @click="ssss">send</el-button>
    <el-button type="primary" @click="coo">close</el-button>
  </div>
</template>

<style scoped></style>
