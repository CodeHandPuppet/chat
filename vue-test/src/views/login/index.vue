<script setup lang="ts">
import { useUserStore } from "@/store/userStore";
import { ref, reactive } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { UserEntity } from "@/type/entity";
import { userRegisterRequest } from "@/api/user";

const loginParam = reactive({
  username: "",
  password: "",
});

const signParam = reactive<UserEntity>({
  username: "",
  password: "",
  avatar: "",
});

const verifyPassword = ref();

const router = useRouter();

const userStore = useUserStore();

const onLogin = async () => {
  const flag = await userStore.login(loginParam.username, loginParam.password);

  if (flag) {
    router.push({ name: "Home" });
  } else {
    ElMessage.error("登录失败，用户名或者密码错误！");
  }
};

const onSign = async () => {
  if ((signParam.password as string) != verifyPassword.value) {
    signParam.password = "";
    verifyPassword.value = "";
    return;
  }
  const result = await userRegisterRequest(signParam);
  if (result.code == 200) {
    loginParam.username = signParam.username as string;
    loginParam.password = signParam.password as string;
  } else {
    ElMessage.error("注册失败");
  }
};

//
</script>

<template>
  <div class="login">
    <div class="main_container">
      <div class="left">
        <div class="logo">登录</div>
        <div class="form">
          <div class="m-3">
            <el-input
              v-model="loginParam.username"
              style="width: 240px"
              placeholder="用户名"
            />
          </div>
          <div class="m-3">
            <el-input
              v-model="loginParam.password"
              style="width: 240px"
              placeholder="密码"
            />
          </div>
          <div><el-button size="large" @click="onLogin">登录</el-button></div>
        </div>
        <div class="foot">这是我的脚</div>
      </div>
      <div class="right">
        <div class="logo">注册</div>
        <!--  -->
        <div class="form">
          <!--  -->
          <div class="m-3">
            <el-input
              v-model="signParam.username"
              style="width: 240px"
              placeholder="用户名"
            />
          </div>
          <!--  -->
          <div class="m-3">
            <el-input
              v-model="signParam.password"
              style="width: 240px"
              placeholder="密码"
            />
          </div>
          <!--  -->
          <div class="m-3">
            <el-input
              v-model="verifyPassword"
              style="width: 240px"
              placeholder="确认密码"
            />
          </div>
          <div><el-button size="large" @click="onSign">注册</el-button></div>
        </div>
        <div class="foot">这是我的脚</div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.login {
  @apply w-full h-full bg-green-300 flex justify-center items-center;

  .logo {
    @apply w-full bg-zinc-300 py-10 flex justify-center items-center;
  }
  .form {
    @apply w-full flex flex-col items-center bg-green-200 py-3;
  }
  .foot {
    @apply w-full bg-orange-700 flex justify-center items-center;
  }
  .main_container {
    @apply bg-indigo-400 h-1/2 w-3/5 grid grid-cols-2 grid-rows-1;
    .left {
      @apply w-full border-r flex flex-col  justify-start pt-24 pb-60;
    }
    .right {
      @apply w-full border-l flex flex-col  justify-start pt-24 pb-60;
    }
  }
}
</style>
