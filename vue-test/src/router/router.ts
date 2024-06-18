import { RouteRecordRaw } from 'vue-router';



export const constantRoutes: RouteRecordRaw[] = [

    // 首页布局
    {
        path: "/",
        name: "Home",
        component: () => import("@/layouts/home/index.vue"),
        redirect: "/chat",
        children: [
            // 朋友布局
            {
                path: "/friend",
                name: "Friend",
                component: () => import("@/layouts/friend/index.vue")
            },

            // 群聊布局
            {
                path: '/group',
                name: "Group",
                component: () => import("@/layouts/group/index.vue")
            },

            // 通知布局
            {
                path: '/notification',
                name: "Notification",
                component: () => import("@/layouts/notification/index.vue")
            },
            // 
            {
                path: "/chat",
                name: "Chat",
                component: () => import("@/layouts/chat/index.vue"),
            }
        ]
    },


]
