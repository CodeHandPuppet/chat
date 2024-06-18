<script setup lang="ts">
import { ref } from "vue";
import ShowFOGVue from "@/views/friend/showFriendOrGroup.vue";
import { UserEntity, UserGroupEntity, UserNotificationEntity } from "@/type/entity";
import { userFriendGetAllRequest } from "@/api/userFriend";
import { userGetUserInfoByUserNameRequest } from "@/api/user";
import { useUserStore } from "@/store/userStore";
import { userNotificationAddRequest } from "@/api/userNotification";
import { ElMessage } from "element-plus";

const dialogVisible = defineModel();

const dataList = ref<UserEntity[]>();

const input = ref();

const clickSearch = async () => {
  const result = await userGetUserInfoByUserNameRequest(input.value, "like");

  if (result.code == 200) {
    dataList.value = result.data as UserEntity[];
  }
};
const userStore = useUserStore();
const sendNotification = async (uid: string) => {
  const receiverId = uid as string;
  const userNotification: UserNotificationEntity = {
    uid: userStore.uid,
    type: "user",
    content: "请求添加好友",
    gid: null,
  };
  const result = await userNotificationAddRequest(receiverId, userNotification);
  if (result.code == 200) {
    ElMessage.success("请求发送成功");
  }
};
</script>

<template>
  <el-dialog v-model="dialogVisible" title="添加好友" width="420" draggable>
    <div class="add_friend">
      <div class="add_friend_top">
        <el-input
          v-model="input"
          style="width: 300px"
          placeholder="输入待搜索用户名"
          prefix-icon="Search"
        />
        <el-button type="primary" class="ml-2" @click="clickSearch">搜索</el-button>
      </div>

      <div class="add_friend_bottom">
        <!-- <div class="h-4 bg-sky-200 mb-1" v-for="i in 40"></div> -->
        <div v-if="!dataList">
          <el-empty :image-size="200" />
        </div>
        <div v-else>
          <template v-for="item in dataList">
            <div
              class="flex w-full flex-row justify-between px-2 mb-2"
              v-if="item.uid != userStore.uid"
            >
              <div class="mb-2 w-2/3 hover:bg-slate-300 duration-300">
                <ShowFOGVue :user="item" />
              </div>
              <div class="pr-1">
                <el-button type="primary" @click="sendNotification(item.uid as string)"
                  >添加</el-button
                >
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<style scoped lang="scss">
.add_friend {
  @apply flex flex-col;
  .add_friend_top {
    @apply w-full mb-2 flex  justify-center;
  }
  .add_friend_bottom {
    @apply h-[400px] overflow-auto;
  }
}
</style>
