<script setup lang="ts">
import { ref, watch } from "vue";
import AddFriendPopVue from "./addFriendPop.vue";
import CreateGroupPopVue from "./createGroupPop.vue";
import { UserFriendEntity } from "@/type/entity";
import { userFriendGetAllRequest } from "@/api/userFriend";
import { tr } from "element-plus/es/locale/index.mjs";
// 传递给创建群聊
const props = defineProps({
  userFriend: { type: Object as () => UserFriendEntity[] | null },
});
const show1 = ref(false);
const show2 = ref(false);
const userFriend = ref<UserFriendEntity[]>();

watch(
  () => props.userFriend,
  async () => {
    if (props.userFriend) {
      userFriend.value = props.userFriend;
      // alert(2);
    } else {
      const result = await userFriendGetAllRequest();
      if (result.code == 200) {
        userFriend.value = result.data;
      }
      // alert(1);
    }
  },
  { immediate: true }
);

const input = ref();
const add = ref(false);
// 获取好友

const clickAddUser = () => {
  add.value = false;
  show1.value = true;
};

const clickAddGroup = () => {
  add.value = false;
  show2.value = true;
  if (!props.userFriend) {
  }
};
</script>

<template>
  <div class="search_option">
    <div class="mysearch_input">
      <el-input
        v-model="input"
        style="width: 195px"
        placeholder="搜索"
        prefix-icon="Search"
      />
    </div>
    <div class="search_add">
      <el-button icon="Plus" @click="add = !add"></el-button>

      <div class="myadd_box" v-if="add">
        <div class="mb-2">
          <el-button icon="User" @click="clickAddUser">添加好友</el-button>
        </div>
        <div>
          <el-button icon="ChatDotRound" @click="clickAddGroup">发起群聊</el-button>
        </div>
      </div>
      <!--  -->
    </div>

    <AddFriendPopVue v-if="show1" v-model="show1" />

    <CreateGroupPopVue v-if="show2" :user-friend="userFriend" v-model="show2" />
  </div>
</template>

<style scoped lang="scss">
.search_option {
  @apply h-full w-full flex items-center p-2;
  .mysearch_input {
    @apply mr-1;
  }
  .search_add {
    @apply relative;
    .myadd_box {
      @apply absolute  flex flex-col bg-purple-50 z-[2002] -right-[100px];
    }
  }
}
</style>
