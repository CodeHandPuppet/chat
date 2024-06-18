<script setup lang="ts">
import { useUserStore } from "@/store/userStore";
import { MessageEntity, UserFriendEntity, UserGroupEntity } from "@/type/entity";
import { MdPreview } from "md-editor-v3";
import { nextTick, ref, watch } from "vue";
//  获取过来的基本信息
const props = defineProps({
  type: {
    type: String,
  },
  userFriend: {
    type: Object as () => UserFriendEntity | null,
  },
  userGroup: {
    type: Object as () => UserGroupEntity | null,
  },
});
//
const userStore = useUserStore();
const messageList = defineModel({ type: Object as () => MessageEntity[] });

const input = ref("sssss");
const scrollableDiv = ref<any>();
watch(
  messageList,
  () => {
    console.log("messageList" + "scrollableDiv");

    nextTick(() => {
      scrollableDiv.value?.scrollTo(0, scrollableDiv.value?.scrollHeight);
    });
  },
  { deep: true }
);
</script>

<template>
  <div class="messageShow_container" ref="scrollableDiv">
    <div
      class="message_box_main"
      v-for="(item, index) in messageList"
      :class="{
        'flex-row-reverse': item.uid == userStore.uid,
        'flex-row': item.uid != userStore.uid,
      }"
    >
      <div class="left">
        <el-avatar
          src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        ></el-avatar>
      </div>
      <div class="right">
        <div class="right_top" :class="{ 'justify-end': item.uid == userStore.uid }">
          {{ item.user?.username }}
        </div>
        <div class="right_bottom" v-if="item.content" :key="index">
          <MdPreview :model-value="item.content" :editor-id="`x_${index}`" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
:deep(.md-editor-preview-wrapper) {
  padding: 0px;
  background: skyblue;
  width: fit-content;
  padding-left: 10px;
  padding-right: 10px;
  @apply rounded-sm;
}
:deep(.md-editor-preview-wrapper) {
}
.messageShow_container {
  @apply w-full h-full overflow-auto flex flex-col;
  .message_box_main {
    @apply flex   mb-3;
    .left {
      @apply p-1;
    }
    .right {
      .right_top {
        @apply flex;
      }
      .right_bottom {
        @apply rounded-md w-auto;
      }
    }
  }
}
</style>
