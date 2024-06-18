<script setup lang="ts">
import { userFriendGetAllRequest } from "@/api/userFriend";
import { UserFriendEntity } from "@/type/entity";
import { onMounted, ref } from "vue";

const userFriend = ref<UserFriendEntity[]>();

const getUserFriend = async () => {
  const result = await userFriendGetAllRequest();
  if (result.code == 200) {
    userFriend.value = result.data;
  }
};

onMounted(async () => {
  await getUserFriend();
});
</script>

<template>
  <div class="friend_container">
    <div class="friend_left">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        background-color="#E6E8EB"
      >
        <!--  -->
        <el-menu-item v-for="c in userFriend" :index="c.id!">
          <div class="contact_container">
            <div class="avatar">
              <img src="/src/assets/vue.svg" alt="" />
            </div>
            <div class="main">
              <span class="name">{{ c.appellation }}</span>
              <!-- <span class="time">{{ c.addTime }}</span> -->
            </div>
          </div>
        </el-menu-item>
      </el-menu>
    </div>

    <div class="friend_right">
      {{ userFriend }}
    </div>
  </div>
</template>

<style scoped lang="scss">
.el-menu-item {
  padding: 0px;
}
.friend_container {
  @apply w-full h-full bg-sky-300 flex flex-row;

  .friend_left {
    @apply h-full flex flex-col;
    width: $chat-left-width;
    .contact_container {
      @apply w-full h-full flex;
      .avatar {
        @apply bg-purple-300 w-14 h-full flex items-center justify-center mr-2 rounded-full;
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
  .friend_right {
    @apply h-full  bg-white;
    width: calc(100% - $chat-left-width);
  }
}
</style>
