<script setup lang="ts">
import { contactGetRequest } from "@/api/userContact";
import { useUserStore } from "@/store/userStore";
import { UserContactEntity } from "@/type/entity";
import { onMounted, ref, watch } from "vue";
import RightScreenVue from "@/views/chat/rightScreen.vue";
import { appendFile } from "fs";
import { useUserContactStore } from "@/store/userContactStore";
import { selectGroupKey } from "element-plus";
import { time } from "console";
import { useSocketStore } from "@/store/socketStore";
const userStore = useUserStore();
const contact = ref<UserContactEntity[]>([]);
const socketStore = useSocketStore();
const defaultActive = ref<string>();
const userContactStore = useUserContactStore();
/**
 * 获取常用联系人
 *
 */
const getContact = async () => {
  const result = await contactGetRequest(userStore.uid as string);
  if (result.code == 200) {
    contact.value = result.data;
  }
};

/**
 * onMount
 */
onMounted(async () => {
  // 获取 常联系人
  await getContact();

  contact.value = userContactStore.combineUserContact(contact.value);

  if (userContactStore.selectUserContact != null) {
    params.value = userContactStore.selectUserContact;
    let { type, fid, gid } = params.value;
    let defaultIndex = contact.value.findIndex((item) => {
      return item.type == type && fid == item.fid && gid == item.gid;
    });
    defaultActive.value = defaultIndex.toString();
    userContactStore.selectUserContact = null;
  }
});

const params = ref<UserContactEntity | null>();
/**
 * 或群聊天对象 用于更新Params 然后展示右界面
 *
 * @param c UserContactEntity
 */
const getChat = async (c: UserContactEntity) => {
  params.value = null;
  params.value = c;
};

/**
 *  监听 是不是有没有出现的 成员的消息
 *
 *  @Object userContactStore
 *
 */
watch(
  () => socketStore.socketMessage,
  (value, oldValue) => {
    if (value.type == "userContact") {
      contact.value.push(value.data as UserContactEntity);
    }
  }
);
</script>

<template>
  <div class="chat_container">
    <!-- 左边选择页 -->
    <div class="chat_left">
      <el-menu
        :default-active="defaultActive"
        class="el-menu-vertical-demo"
        background-color="#E6E8EB"
      >
        <!--  -->
        <el-menu-item
          v-for="(c, index) in contact"
          :index="`${index}`"
          @click="getChat(c)"
        >
          <div class="contact_container">
            <div class="avatar">
              <el-avatar
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
              ></el-avatar>
            </div>
            <div class="main">
              <span class="name">{{
                c.type == "user"
                  ? c.friend?.appellation
                    ? c.friend.appellation
                    : c.friend?.user.username
                  : c.group?.appellation
              }}</span>
              <span class="time">{{ c.contactTime }}</span>
            </div>
          </div>
        </el-menu-item>
      </el-menu>
    </div>

    <!-- 右边聊天页 -->
    <div class="chat_right">
      <RightScreenVue v-if="params" v-model="params" />
    </div>
  </div>
</template>

<style scoped lang="scss">
.el-menu-item {
  padding: 0px;
}
.chat_container {
  @apply w-full h-full bg-sky-300 flex flex-row;

  .chat_left {
    @apply h-full flex flex-col;
    width: $chat-left-width;
    .contact_container {
      @apply w-full h-full flex text-ellipsis overflow-hidden;
      .avatar {
        // @apply bg-purple-300 w-14 h-full flex items-center justify-center mr-2 rounded-full;
        @apply flex justify-center items-center mr-2;
      }
      .main {
        @apply flex justify-between w-[calc(100%-56px)];
        .name {
          @apply text-nowrap mr-2;
        }
        .time {
          @apply text-xs flex justify-end items-center pr-1;
        }
      }
    }
  }
  .chat_right {
    @apply h-full  bg-white;
    width: calc(100% - $chat-left-width);
  }
}
</style>
