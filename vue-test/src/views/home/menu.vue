<script lang="ts" setup>
import { useUserStore } from "@/store/userStore";
import { onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
const defaultActive = ref<string>();
const userStore = useUserStore();
const logout = () => {
  userStore.loginOut();
};

const route = useRoute();

onMounted(() => {
  defaultActive.value = route.path;
  // console.log(route.path);
});
//  路径改变 左边菜单栏的光标跟着过来
watch(
  () => route.path,
  () => {
    defaultActive.value = route.path;
  }
);
</script>

<template>
  <div class="home_menu">
    <!--  -->
    <div class="menu-top">
      <el-menu
        :default-active="defaultActive"
        class="el-menu-vertical-demo"
        :router="true"
      >
        <!--  -->
        <el-menu-item index="/chat">
          <el-icon><ChatDotRound /></el-icon>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="/friend">
          <el-icon><User /></el-icon>
        </el-menu-item>
        <!--  -->
        <!-- <el-menu-item index="/group">
          <el-icon><Grid /></el-icon>
        </el-menu-item> -->
        <!--  -->
        <!-- <el-menu-item index="/notification">
          <el-icon><Bell /></el-icon>
        </el-menu-item> -->
      </el-menu>
    </div>
    <!--  -->
    <div class="menu_bottom">
      <el-button
        style="width: 100%"
        icon="setting"
        size="large"
        @click="logout"
      ></el-button>
    </div>
  </div>
</template>

<style scoped lang="scss">
// menu 菜单边线注释
.el-menu {
  border: 0px;
}
.el-button {
  border: 0px;
}
.home_menu {
  @apply w-full h-full flex flex-col justify-between;

  .menu-top {
  }
  .menu_bottom {
    @apply w-full pb-2;
  }
}
</style>
