<script setup lang="ts">
import { useUserContactStore } from "@/store/userContactStore";
import { UserContactEntity, UserFriendEntity, UserGroupEntity } from "@/type/entity";
import { useRouter } from "vue-router";
const friendShowData = defineModel({
  type: Object as () => UserFriendEntity,
  required: true,
});

const userContactStore = useUserContactStore();
const router = useRouter();
/**
 * 去发送消息
 *
 */
const toSendMessage = async () => {
  let tempUserContact: UserContactEntity = {
    type: "user",
    fid: friendShowData.value?.fid,
    friend: friendShowData.value,
  };
  userContactStore.push(tempUserContact);
  router.push({ name: "Chat" });
};
</script>

<template>
  <div class="show_user">
    <!--  -->
    <div class="show_user_top">
      <div class="show_user_top_image">
        <el-avatar
          :size="95"
          src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        />
      </div>
      <div class="py-1 flex flex-col">
        <span>{{
          friendShowData?.appellation
            ? friendShowData.appellation
            : friendShowData?.user.username
        }}</span>
        <span class="text-xs">account{{ "暂不支持" }}</span>
      </div>
    </div>
    <!--  -->
    <div class="show_user_mid">
      <div><span>备注</span></div>
      <div>签名:{{ "暂不支持" }}</div>
      <div>分组:{{ "暂不支持" }}</div>
    </div>
    <!--  -->
    <div class="show_user_bottom">
      <el-button type="primary" @click="toSendMessage">发消息</el-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.show_user {
  @apply h-2/3 mt-3 max-h-[700px] bg-slate-400 max-w-[800px] w-2/3 flex flex-col;
  .show_user_top {
    @apply h-[100px] w-full bg-white flex flex-row py-2;
    .show_user_top_image {
      @apply h-full flex items-center justify-center w-28 border-r-2;
    }
  }
  .show_user_mid {
    @apply h-[350px] w-full flex flex-col gap-2  bg-sky-300;
  }
  .show_user_bottom {
    @apply flex justify-end pt-2 w-full;
  }
}
</style>
