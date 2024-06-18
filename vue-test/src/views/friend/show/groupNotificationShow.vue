<script setup lang="ts">
import { UserNotificationEntity } from "@/type/entity";

const groupNotificationShowData = defineModel({
  type: Object as () => UserNotificationEntity[],
});
</script>

<template>
  <div class="group_notification_container">
    <div class="mb-4">群聊通知</div>
    <div class="group_notification_show">
      <template
        v-for="(item, index) in groupNotificationShowData"
        v-if="groupNotificationShowData?.length != 0"
      >
        <div class="main">
          <div class="main_image">
            <el-avatar
              src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
            />
          </div>
          <div class="main_message">
            <div class="group_info">
              <span class="text-sky-300">{{ item.group?.user?.username }}</span>
              <span class="ml-2 text-xs">邀请你加入群聊</span>
            </div>
            <div class="message_content">{{ item.content }}</div>
            <div></div>
          </div>
          <div class="main_agree" v-if="!item.isFinish">
            <el-button type="danger">拒绝</el-button>
            <el-button type="primary">同意</el-button>
          </div>
          <div class="flex text-sm justify-end items-center w-[100px]" v-else>
            {{ item.agree == true ? "已同意" : "已拒绝" }}
          </div>
        </div>
      </template>

      <div v-if="groupNotificationShowData?.length == 0">
        <el-empty :image-size="200" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.group_notification_container {
  @apply w-full h-full flex-col justify-center items-center py-2 px-3;
  .group_notification_show {
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
