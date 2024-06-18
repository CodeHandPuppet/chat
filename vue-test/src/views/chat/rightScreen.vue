<script setup lang="ts">
import { UserContactEntity, MessageEntity } from "@/type/entity";
import ChatInputVue from "./chatInput.vue";
import { onBeforeUnmount, onMounted, ref, watch } from "vue";
import MessageShowVue from "./messageShow.vue";
import { useUserStore } from "@/store/userStore";
import { useSocketStore } from "@/store/socketStore";
import { userMessageGetRequest } from "@/api/userMessage";
import { contactAddRequest } from "@/api/userContact";
import { groupMessageGetRequest } from "@/api/groupMessage";

const userContact = defineModel({
  type: Object as () => UserContactEntity,
  required: true,
});
// 输入框的实例
const chatInputVueRef = ref();
// 输入框双向绑定的数据内容
const inputText = ref();
const socketStore = useSocketStore();
const userStore = useUserStore();
const messageList = ref<MessageEntity[]>([]);

const getMessageList = async () => {
  if (userContact.value!.type == "user") {
    const result = await userMessageGetRequest(userContact.value!.fid as string);
    messageList.value = result.data;
  } else {
    // group情况
    const result = await groupMessageGetRequest(userContact.value!.gid as string);
    messageList.value = result.data;
  }
};
onMounted(async () => {
  await getMessageList();
  window.addEventListener("keydown", async (e) => {
    // 判断同时按下了Alt、Ctrl和Enter键
    if (e.altKey && e.keyCode === 13) {
      await sendMessage();
    }
  });
});
//
watch(userContact, async () => {
  inputText.value = "";
  chatInputVueRef.value.focus();
  await getMessageList();
});
// 监听 socket发送过来的消息
watch(
  () => socketStore.socketMessage,
  (value, oldValue) => {
    let tempMessage = socketStore.getChatMessage(userContact.value!);
    if (tempMessage != null) {
      messageList.value.push(tempMessage);
    }
  }
);

const sendMessage = async () => {
  // 判断这个usercontact有没有 id  没有id说明是后来添加的需要发送请求
  if (userContact.value!.id == null) {
    const result = await contactAddRequest(userContact.value!);
    userContact.value = result.data;
  }

  messageList.value.push({
    uid: userStore.uid,
    content: inputText.value,
    user: userStore,
  });
  //  发送消息转化给  socket发送
  const receiverId =
    userContact.value.type == "user" ? userContact.value.fid : userContact.value.gid;
  socketStore.sendChatMessage({
    content: inputText.value,
    receiverId: receiverId as string,
    receiverType: userContact.value!.type,
    senderId: userStore.uid as string,
  });
  // 清空 输入框
  inputText.value = "";
};
</script>

<template>
  <div class="right_screen">
    <div class="head">
      <div class="head_show_name">
        {{
          userContact!.type == "user"
            ? userContact!.friend?.appellation
              ? userContact!.friend.appellation
              : userContact!.friend?.user.username
            : userContact!.group?.appellation
        }}
      </div>
      <div class="head_show_btn">
        <el-icon style="width: 40px"><More /></el-icon>
      </div>
    </div>
    <div class="body">
      <MessageShowVue
        :type="userContact!.type"
        :user-friend="userContact?.friend"
        :user-group="userContact?.group"
        v-model="messageList"
      />
    </div>
    <div class="foot">
      <ChatInputVue
        ref="chatInputVueRef"
        @sendMessage="sendMessage"
        v-model="inputText"
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.right_screen {
  @apply w-full h-full  flex flex-col justify-between;
  .head {
    @apply border-b h-12 flex justify-between pr-6;
    .head_show_name {
      @apply flex items-center pl-5;
    }
    .head_show_btn {
      @apply flex justify-center items-center;
    }
  }
  .body {
    height: calc(100% - 288px - 48px);
  }
  .foot {
    @apply h-72  bg-gray-400;
  }
}
</style>
