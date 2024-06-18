import { App } from "vue";
import { setupPinia } from "./pinia";
import { setupAntDesignVue } from "./antDesignVue";
import { setupElementPlus } from "./elementPlus";




export function setupPulgins(app: App) {

    // 注册pinia
    setupPinia(app);
    // ant-design-vue
    // setupAntDesignVue(app);
    // elementPlus
    setupElementPlus(app);
}