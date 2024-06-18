<script setup lang="ts">
import menuVue from "@/views/home/menu.vue";
import { useUserStore } from "@/store/userStore";

const userStore = useUserStore();
</script>

<template>
  <div class="home_container">
    <div class="home_menu">
      <div class="home_menu_logo">
        <el-avatar
          src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
          size="large"
        ></el-avatar>
        {{ userStore.username }}
      </div>
      <div class="menu">
        <menuVue />
      </div>
    </div>

    <div class="home_screen">
      <router-view />
    </div>
  </div>
</template>

<style scoped lang="scss">
.home_container {
  @apply flex  flex-row w-screen h-screen;

  .home_menu {
    @apply bg-slate-300 w-[60px];
    .home_menu_logo {
      @apply bg-orange-200 flex items-center justify-center flex-col;
      height: $home-menu-logo-height;
    }

    .menu {
      @apply h-[calc(100%-80px)];
      height: calc(100% - $home-menu-logo-height);
    }
  }

  .home_screen {
    @apply bg-white w-full h-full;
  }
}
</style>
