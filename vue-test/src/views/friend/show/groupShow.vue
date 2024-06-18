<script setup lang="ts">
import { useUserContactStore } from "@/store/userContactStore";
import { UserContactEntity, UserFriendEntity, UserGroupEntity } from "@/type/entity";
import { useRouter } from "vue-router";

const groupShowData = defineModel({ type: Object as () => UserGroupEntity });
const userContactStore = useUserContactStore();
const router = useRouter();
const toSendMessage = async () => {
  let tempUserContact: UserContactEntity = {
    type: "group",
    gid: groupShowData.value?.gid,
    group: groupShowData.value,
  };
  userContactStore.push(tempUserContact);
  router.push({ name: "Chat" });
};
</script>

<template>
  <div class="show_group">
    <!--  -->
    <div class="show_group_top">
      <div class="show_group_top_image">
        <el-avatar
          :size="95"
          src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        />
      </div>
      <div class="py-1 flex flex-col">
        <span>{{ groupShowData?.appellation }}</span>
        <span class="text-xs">群account{{ "暂不支持" }}</span>
      </div>
    </div>
    <!--  -->
    <div class="show_group_mid">
      <div><span>备注</span></div>
      <div>签名:{{ "暂不支持" }}</div>
      <div>分组:{{ "暂不支持" }}</div>
    </div>
    <!--  -->
    <div class="show_group_bottom">
      <el-button type="primary" @click="toSendMessage">发消息</el-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.show_group {
  @apply h-2/3 max-h-[700px] bg-slate-400 max-w-[800px] w-2/3 flex flex-col;
  .show_group_top {
    @apply h-[100px] w-full bg-white flex flex-row py-2;
    .show_group_top_image {
      @apply h-full flex items-center justify-center w-28 border-r-2;
    }
  }
  .show_group_mid {
    @apply h-[350px] w-full flex flex-col gap-2 bg-sky-300;
  }
  .show_group_bottom {
    @apply flex justify-end pt-2 w-full;
  }
}
</style>
