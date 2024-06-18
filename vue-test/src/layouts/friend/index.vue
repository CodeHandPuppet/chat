<script setup lang="ts">
import ShowFOGVue from "@/views/friend/showFriendOrGroup.vue";
import searchOptionVue from "@/views/friend/searchOption.vue";
import UserShowVue from "@/views/friend/show/userShow.vue";
import GroupShowVue from "@/views/friend/show/groupShow.vue";
import UserNotificationShowVue from "@/views/friend/show/userNotificationShow.vue";
import GroupNotificationShowVue from "@/views/friend/show/groupNotificationShow.vue";
import { userFriendGetAllRequest } from "@/api/userFriend";
import { userGroupGetAllRequest } from "@/api/userGroup";
import { userNotificationByTypeRequest } from "@/api/userNotification";
import {
  UserEntity,
  UserFriendEntity,
  UserGroupEntity,
  UserNotificationEntity,
} from "@/type/entity";
import { onMounted, ref, watch } from "vue";

const userFriend = ref<UserFriendEntity[]>();
const userGroup = ref<UserGroupEntity[]>();

// 设置两个减少请求的次数
const groupNotificationShowData = ref<UserNotificationEntity[]>([]);
const userNotificationShowData = ref<UserNotificationEntity[]>([]);
// 1:好友 2:群聊
const radio = ref(1);
const friendShowData = ref<UserFriendEntity | null>();
const groupShowData = ref<UserGroupEntity | null>();
// 切换展示
const showToggle = ref(0);
const input2 = ref();

// 获取群聊
const getUsergroup = async () => {
  const result = await userGroupGetAllRequest();
  if (result.code == 200) {
    userGroup.value = result.data;
  }
};
// 获取好友
const getUserFriend = async () => {
  const result = await userFriendGetAllRequest();
  if (result.code == 200) {
    userFriend.value = result.data;
  }
};

onMounted(async () => {
  await getUserFriend();
  await getUsergroup();
});
//

const clickUser = (user: UserFriendEntity) => {
  friendShowData.value = user;
  showToggle.value = 1;
};

const clickGroup = (group: UserGroupEntity) => {
  showToggle.value = 2;
  groupShowData.value = group;
};

const clickUserOrGrouupNotification = async (item: "user" | "group") => {
  if (item == "user") {
    showToggle.value = 3;
  } else showToggle.value = 4;
  if (item == "user" && userNotificationShowData.value!.length == 0) {
    const result = await userNotificationByTypeRequest("user");
    if (result.code == 200) {
      userNotificationShowData.value = result.data;
    }
  }
  if (item == "group" && groupNotificationShowData.value!.length == 0) {
    const result = await userNotificationByTypeRequest("group");
    if (result.code == 200) {
      groupNotificationShowData.value = result.data;
    }
  }
};
//  监听器
watch(radio, (value, oldValue) => {
  if (value == 1) {
    groupShowData.value = null;
  } else {
    friendShowData.value = null;
  }
});

watch(
  userNotificationShowData,
  async () => {
    await getUserFriend();
  },
  { deep: true }
);
watch(
  groupNotificationShowData,
  async () => {
    await getUsergroup();
  },
  { deep: true }
);
</script>

<template>
  <div class="friend_container">
    <div class="friend_left">
      <!-- 搜索框 -->
      <div class="friend_left_search">
        <searchOptionVue :user-friend="userFriend" />
        <!-- <searchOptionVue /> -->
      </div>
      <!-- 通知 -->
      <div class="friend_left_option">
        <el-menu background-color="#E6E8EB">
          <el-menu-item
            v-for="item of (['user', 'group'] as ('user'|'group')[])"
            :index="item"
            @click="clickUserOrGrouupNotification(item)"
          >
            <div class="w-full flex items-center justify-between">
              <span> {{ item == "user" ? "好友通知" : "群通知" }} </span>
              <el-icon><ArrowRight /></el-icon>
            </div>
          </el-menu-item>
        </el-menu>
        <!-- 切换好友还是群聊 -->
        <div class="w-full h-full flex justify-center items-center">
          <el-radio-group v-model="radio">
            <el-radio-button label="好友" value="1" />
            <el-radio-button label="群聊" value="2" />
          </el-radio-group>
        </div>
      </div>
      <!-- 好友或群列表 -->
      <div class="friend_left_menu">
        <el-menu background-color="#E6E8EB">
          <!-- 好友 -->
          <template v-if="radio == 1">
            <el-menu-item v-for="c in userFriend" :index="c.id!" @click="clickUser(c)">
              <ShowFOGVue :user-friend="c" />
            </el-menu-item>
          </template>
          <!-- 群聊  -->
          <template v-if="radio == 2">
            <el-menu-item v-for="c in userGroup" :index="c.id!" @click="clickGroup(c)">
              <ShowFOGVue :user-group="c" />
            </el-menu-item>
          </template>
        </el-menu>
      </div>
    </div>

    <!-- <div class="friend_right">
      <div>groupShowData:{{ groupShowData }}</div>
      <br />
      <div>friendShowData:{{ friendShowData }}</div>
      <br />
      <div>userNotificationShowData: {{ userNotificationShowData }}</div>
      <br />
      <div>groupNotificationShowData:{{ groupNotificationShowData }}</div>
    </div> -->

    <div class="friend_right">
      <UserShowVue v-model="friendShowData" v-if="showToggle == 1" />
      <GroupShowVue v-model="groupShowData" v-if="showToggle == 2" />

      <UserNotificationShowVue
        v-model="userNotificationShowData"
        v-if="showToggle == 3"
      />
      <GroupNotificationShowVue
        v-model="groupNotificationShowData"
        v-if="showToggle == 4"
      />
    </div>
  </div>
</template>

<style scoped lang="scss">
.el-popper {
  padding: 0px;
}
.el-popover.el-popper {
  padding: 0px;
}
.el-menu-item {
  padding: 0px;
}
.friend_container {
  @apply w-full h-full bg-sky-300 flex flex-row;

  .friend_left {
    @apply h-full flex flex-col;
    width: $chat-left-width;
    .friend_left_search {
      @apply h-[56px] w-full;
    }
    .friend_left_option {
      @apply w-full h-[150px] flex flex-col;
    }
    .friend_left_menu {
      @apply overflow-auto;
      height: calc(100% - (150px + 56px));
    }
  }
  .friend_right {
    @apply h-full  bg-white flex justify-center;
    width: calc(100% - $chat-left-width);
  }
}
</style>
