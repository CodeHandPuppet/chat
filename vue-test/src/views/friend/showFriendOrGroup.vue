<script setup lang="ts">
import {
  GroupEntity,
  UserEntity,
  UserFriendEntity,
  UserGroupEntity,
} from "@/type/entity";
import { ref, watch } from "vue";

const props = defineProps({
  c: {
    type: Object as () => UserFriendEntity | UserGroupEntity,
  },

  userFriend: {
    type: Object as () => UserFriendEntity,
  },
  userGroup: {
    type: Object as () => UserGroupEntity,
  },
  user: {
    type: Object as () => UserEntity,
  },
  group: {
    type: Object as () => GroupEntity,
  },
});

const showName = ref("");
const showAvatar = ref("");

watch(
  props,
  () => {
    if (props.userFriend != null) {
      showName.value = (props.userFriend.appellation != null
        ? props.userFriend.appellation
        : props.userFriend.user.username) as string;
    } else if (props.user != null) {
      showName.value = props.user.username as string;
    } else if (props.group != null) {
      showName.value = props.group.groupName as string;
    } else if (props.userGroup != null) {
      showName.value = (props.userGroup.appellation
        ? props.userGroup.appellation
        : props.userGroup.group!.groupName) as string;
    }
  },
  { immediate: true }
);
</script>

<template>
  <div class="contact_container">
    <div class="avatar">
      <el-avatar
        src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
      />
      <!-- <img src="/src/assets/vue.svg" alt="" /> -->
    </div>
    <div class="main">
      <span class="name">{{ showName }}</span>
      <!-- <span class="time">{{ c.addTime }}</span> -->
    </div>
  </div>
</template>

<style scoped lang="scss">
.contact_container {
  @apply w-full h-full flex items-center;
  .avatar {
    @apply w-14 h-full flex items-center justify-center mr-2 rounded-full;
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
</style>
