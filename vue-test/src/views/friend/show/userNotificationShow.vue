<script setup lang="ts">
import { userFriendAddRequest } from "@/api/userFriend";
import { UserNotificationEntity } from "@/type/entity";
import { ElMessage } from "element-plus";

const userNotificationShowData = defineModel({
  type: Object as () => UserNotificationEntity[],
});

const cancleBtn = async (not: UserNotificationEntity) => {
  not.isFinish = true;
  not.agree = false;
};

const confirmBtn = async (not: UserNotificationEntity) => {
  const result = await userFriendAddRequest(not);
  if (result.code == 200) {
    ElMessage.success("已同意");
    not.isFinish = true;
    not.agree = true;
  }
};
</script>

<template>
  <div class="user_notification_container">
    <div class="mb-4">好友通知</div>
    <div class="user_notification_show">
      <template
        v-for="(item, index) in userNotificationShowData"
        v-if="userNotificationShowData?.length != 0"
      >
        <div class="main">
          <div class="main_image">
            <el-avatar
              src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
            />
          </div>
          <div class="main_message">
            <div class="user_info">
              <span class="text-sky-300">{{ item.user?.username }}</span>
              <span class="ml-2 text-xs">请求添加好友</span>
            </div>
            <div class="message_content">{{ item.content }}</div>
            <div></div>
          </div>
          <div class="main_agree" v-if="!item.isFinish">
            <el-button type="danger" @click="cancleBtn(item)">拒绝</el-button>
            <el-button type="primary" @click="confirmBtn(item)">同意</el-button>
          </div>
          <div class="flex text-sm justify-end items-center w-[100px]" v-else>
            {{ item.agree == true ? "已同意" : "已拒绝" }}
          </div>
        </div>
      </template>
      <div v-if="userNotificationShowData?.length == 0">
        <el-empty :image-size="200" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.user_notification_container {
  @apply w-full h-full flex-col justify-center items-center py-2 px-3;
  .user_notification_show {
    @apply w-full h-[500px]  flex flex-col items-center gap-3;
    .main {
      @apply h-16 w-[600px] flex items-center px-3 py-2  bg-gray-200;
      .main_image {
        @apply h-full flex items-center;
      }
      .main_message {
        @apply flex text-sm flex-col justify-between py-1 w-2/3 h-full ml-2;
      }
      .main_agree {
        @apply flex justify-end;
      }
    }
  }
}
</style>
