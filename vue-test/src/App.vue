<script setup lang="ts">
import { onMounted } from "vue";
import { onWebSocketService } from "./utils/socket";
import { useUserStore } from "./store/userStore";

// let socket;
// const userStore = useUserStore();

// onMounted(() => {
//   const onMessage = (ev: MessageEvent) => {
//     console.log(ev.data);
//   };
//   socket = onWebSocketService("/websocket?userId=" + userStore.uid, onMessage, null);
// });
</script>

<template>
  <router-view></router-view>
</template>

<style scoped></style>
