<script setup lang="ts">
import { GroupEntity, GroupMemberEntity, UserFriendEntity } from "@/type/entity";
import { ref } from "vue";
import ShowFOGVue from "@/views/friend/showFriendOrGroup.vue";
import { useUserStore } from "@/store/userStore";
import { group } from "console";
import { GroupCreateRequest } from "@/api/group";
import { ElMessage } from "element-plus";
const dialogVisible = defineModel();
const userStore = useUserStore();
const props = defineProps({ userFriend: { type: Object as () => UserFriendEntity[] } });
const input = ref();
const friendList = ref<UserFriendEntity[]>([]);

const confirmBtn = async () => {
  let groupMembers: GroupMemberEntity[] = friendList.value.map((friend) => {
    return {
      type: "user",
      uid: friend.fid,
      user: friend.user, // 可传 可 不传
    } as GroupMemberEntity;
  });

  groupMembers.push({
    type: "owner",
    uid: userStore.uid,
    user: { username: userStore.username },
  });

  let groupIntro = "让我们愉快的聊天吧";
  let groupName = "";
  groupMembers.forEach((member, index) => {
    groupName = groupName + member.user?.username + " ";
    if (index == groupMembers.length - 1) {
      groupName = groupName + "的聊天组";
    }
  });
  let avatar = "111";
  const groupInfo: GroupEntity = {
    groupIntro,
    groupName,
    avatar,
    ownerUid: userStore.uid,
    groupMembers,
  };
  console.log(groupInfo);
  //
  const result = await GroupCreateRequest(groupInfo);
  if (result.code == 200) {
    ElMessage.success("创建成功");
    console.log(11);
  }
};
</script>

<template>
  <el-dialog
    v-model="dialogVisible"
    :destroy-on-close="true"
    title="创建群聊"
    width="700"
    draggable
  >
    <div class="create_group">
      <div class="create_group_left">
        <div>搜索框</div>
        <div class="create_group_left_select">
          <el-checkbox-group v-model="friendList" size="large" class="flex flex-col">
            <template v-for="item in userFriend">
              <el-checkbox :value="item">
                <template #default>
                  <div class="w-[300px] hover:bg-slate-400 duration-300">
                    <ShowFOGVue :user-friend="item" />
                  </div>
                </template>
              </el-checkbox>
            </template>
          </el-checkbox-group>
        </div>
      </div>
      <div class="create_group_right">
        <div class="flex justify-end">
          已选<span class="text-sky-300 mx-2"> {{ friendList.length }} </span>位好友
        </div>
        <div class="w-full h-full">
          <template v-for="(item, index) in friendList">
            <div class="flex justify-center items-center">
              <div class="h-[50px] w-[300px]" :key="index">
                <ShowFOGVue :user-friend="item" />
              </div>
              <!-- 叉按钮 -->
              <div>
                <el-button
                  circle
                  icon="Close"
                  @click="friendList.splice(index)"
                  link
                ></el-button>
              </div>
            </div>
          </template>
        </div>

        <div class="flex justify-end">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button @click="confirmBtn" type="primary">确定</el-button>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<style scoped lang="scss">
.el-checkbox.el-checkbox--large {
  height: 50px;
  width: 100%;
}
.create_group {
  @apply grid grid-rows-1 grid-cols-2 h-[600px];
  .create_group_left {
    @apply border-r-2 overflow-auto;
    .create_group_left_select {
    }
  }
  .create_group_right {
    @apply flex justify-between flex-col;
  }
}
</style>
