import { createApp } from 'vue'
import '@/assets/css/style.css'
import '@/assets/css/tailwind.css'

import App from './App.vue'
import { setupPulgins } from '@/plugins'
import router from './router'




const app = createApp(App)


app.use(router)

// 注册全部插件
setupPulgins(app);


app.mount('#app')
