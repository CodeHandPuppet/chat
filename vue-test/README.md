


1. 通过用户名去搜索用户  
    
    
    后端：
    1. 做两个 接口  模糊匹配 和 全部匹配
    2. 删除通知的接口






```
<script setup lang="ts">
import { userGroupGetAllRequest } from "@/api/userGroup";
import { UserGroupEntity } from "@/type/entity";
import { onMounted, ref } from "vue";

const userGroup = ref<UserGroupEntity[]>();

const getUsergroup = async () => {
  const result = await userGroupGetAllRequest();
  if (result.code == 200) {
    userGroup.value = result.data;
  }
};

onMounted(async () => {
  await getUsergroup();
});
</script>

<template>
  <div class="group_container">
    <div class="group_left">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        background-color="#E6E8EB"
      >
        <!--  -->
        <el-menu-item v-for="c in userGroup" :index="c.id!">
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

    <div class="group_right"></div>
  </div>
</template>

<style scoped lang="scss">
.el-menu-item {
  padding: 0px;
}
.group_container {
  @apply w-full h-full bg-sky-300 flex flex-row;

  .group_left {
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
  .group_right {
    @apply h-full  bg-white;
    width: calc(100% - $chat-left-width);
  }
}
</style>


```