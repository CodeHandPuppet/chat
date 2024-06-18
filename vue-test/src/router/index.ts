import { RouteRecordRaw, createRouter, createWebHistory, createWebHashHistory } from 'vue-router';

import { useUserStore } from "@/store/userStore";
import { pinia } from "@/plugins/pinia";
import { getLocalStorage } from '@/utils/localStore';
import { COMMON } from '@/utils/common';
import { constantRoutes } from './router';


const userStore = useUserStore(pinia);

// const routes = userStore.type == 'admin' ? [...constantRoutes, ...asyncRoutes] : [...constantRoutes];


const routes: RouteRecordRaw[] = [
    {
        path: "/login",
        name: 'Login',
        component: () => import("@/views/login/index.vue")
    }
]





const router = createRouter({
    history: createWebHistory(),
    routes: [...constantRoutes, ...routes]
    // routes
})

router.beforeEach(async (to, from, next) => {

    const token = getLocalStorage(COMMON.TOKEN);

    // 用户登录   不能访问 login ,其他路由都可以访问
    if (token) {
        if (to.path == '/login') {
            next({ path: from.path })
        }

        else {
            if (userStore.username) {
                next()
            }
            else {
                const result = await userStore.getUserInfo();
                if (!result) {
                    next({ path: '/login', query: { redirect: to.path } })
                }
                next()
            }
        }
    }
    // 用户未登录  只能访问 login
    else {
        if (to.path == '/login') {
            next()
        }
        else {
            next({ path: '/login', query: { redirect: to.path } })
        }
    }
})

export default router